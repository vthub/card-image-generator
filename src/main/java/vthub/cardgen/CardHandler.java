/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen;

import com.google.common.primitives.Ints;
import com.google.inject.Inject;
import org.apache.commons.lang.StringUtils;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.util.MultiValueMap;
import vthub.cardgen.model.Card;
import vthub.cardgen.model.CardBuilder;
import vthub.cardgen.model.Specification;
import vthub.cardgen.model.SpecificationBuilder;

import java.time.LocalDate;
import java.util.Optional;

import static vthub.cardgen.App.CARD;
import static vthub.cardgen.CardGeneratorUtils.generateNumber;

public class CardHandler implements Handler
{
    private final CardService service;

    @Inject
    public CardHandler(CardService service)
    {
        this.service = service;
    }

    @Override
    public void handle(Context context) throws Exception
    {
        Card card = CardBuilder.aCard()
                .number(getNumber(context))
                .expiry(getExpiry(context))
                .cardHolder(getCardHolder(context))
                .build();

        Specification specification = SpecificationBuilder.aSpecification()
                .width(getWidth(context))
                .build();

        context.render(service.generate(card, specification));
    }

    String getNumber(Context context)
    {
        return Optional.ofNullable(context.getPathTokens().get(CARD))
                .filter(StringUtils::isNotEmpty)
                .filter(StringUtils::isNumeric)
                .orElse(generateNumber());
    }

    String getExpiry(Context context)
    {
        return Optional.ofNullable(context.getRequest().getQueryParams().get("expiry"))
                .filter(s -> s.matches("(0?\\d|1[012])/\\d{2}"))
                .orElseGet(() ->
                {
                    LocalDate now = LocalDate.now();
                    return String.format("%02d/%d", now.getMonthValue(), (now.getYear() + 2) % 100);
                });
    }

    String getCardHolder(Context context)
    {
        MultiValueMap<String, String> queryParams = context.getRequest().getQueryParams();
        String name = Optional.ofNullable(queryParams.get("name")).map(String::toUpperCase).orElse("NAME");
        String lastName = Optional.ofNullable(queryParams.get("lastname")).map(String::toUpperCase).orElse("LASTNAME");
        return String.format("%s %s", name, lastName);
    }

    int getWidth(Context context)
    {
        return Optional.ofNullable(context.getRequest().getQueryParams().get("width"))
                .map(Ints::tryParse)
                .orElse(500);
    }
}

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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        context.render(service.generate(createCard(context), createSpecification(context)));
    }

    Card createCard(Context context)
    {
        return CardBuilder.aCard()
                .number(getNumber(context))
                .expiry(getExpiry(context))
                .cardHolder(getCardHolder(context))
                .build();
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

    /**
     * Get name and last name from the context, from query parameters <code>name</code> and <code>lastname</code>.
     * If either one is not provided it will be replaced with default value (<code>NAME</code> for name and <code>LASTNAME</code> for last name).
     * If either one is empty, then empty string will be used to created full name
     *
     * @param context with query parameters
     * @return full name of the card holder
     */
    String getCardHolder(Context context)
    {
        MultiValueMap<String, String> queryParams = context.getRequest().getQueryParams();
        String name = Optional.ofNullable(queryParams.get("name")).map(String::toUpperCase).orElse("NAME");
        String lastName = Optional.ofNullable(queryParams.get("lastname")).map(String::toUpperCase).orElse("LASTNAME");
        return Stream.of(name, lastName).filter(StringUtils::isNotEmpty).collect(Collectors.joining(" "));
    }

    Specification createSpecification(Context context)
    {
        return SpecificationBuilder.aSpecification()
                .width(getWidth(context))
                .build();
    }

    int getWidth(Context context)
    {
        return Optional.ofNullable(context.getRequest().getQueryParams().get("width"))
                .map(Ints::tryParse)
                .orElse(500);
    }
}

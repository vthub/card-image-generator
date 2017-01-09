package vthub.cardgen;

import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import vthub.cardgen.model.Card;
import vthub.cardgen.model.CardBuilder;

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
                .build();
        context.render(service.generate(card));
    }

    String getNumber(Context context)
    {
        return Optional.ofNullable(context.getPathTokens().get(CARD)).orElse(generateNumber());
    }
}

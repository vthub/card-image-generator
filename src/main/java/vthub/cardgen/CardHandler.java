package vthub.cardgen;

import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static vthub.cardgen.App.CARD;

public class CardHandler implements Handler
{
    private final CardService service;

    @Inject
    public CardHandler(CardService service)
    {
        this.service = service;
    }

    @Override
    public void handle(Context ctx) throws Exception
    {
        System.out.println(ctx.getPathTokens().get(CARD));
        ctx.render(service.generate());
    }
}

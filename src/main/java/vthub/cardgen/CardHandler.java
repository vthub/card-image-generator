package vthub.cardgen;

import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;

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
        ctx.render(service.generate());
    }
}

package vthub.cardgen;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import vthub.cardgen.model.Card;

import java.awt.image.BufferedImage;

@Singleton
public class CardService
{

    private final Painter painter;

    @Inject
    public CardService(Painter painter)
    {
        this.painter = painter;
    }

    public BufferedImage generate(Card card)
    {
        return painter.draw(card);
    }

}

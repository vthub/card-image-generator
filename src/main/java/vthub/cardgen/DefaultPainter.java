package vthub.cardgen;

import vthub.cardgen.graphics.CardPainter;
import vthub.cardgen.model.Card;

import java.awt.image.BufferedImage;

public class DefaultPainter implements Painter
{
    @Override
    public BufferedImage draw(Card card)
    {
        return new CardPainter(card).draw();
    }
}

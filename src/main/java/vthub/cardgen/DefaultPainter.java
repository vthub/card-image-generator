/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen;

import com.google.inject.Inject;
import vthub.cardgen.graphics.DefaultCardPainterFactory;
import vthub.cardgen.model.Card;

import java.awt.image.BufferedImage;

public class DefaultPainter implements Painter
{
    @Inject
    private DefaultCardPainterFactory factory;

    @Override
    public BufferedImage draw(Card card)
    {
        return factory.create(card).draw();
    }
}

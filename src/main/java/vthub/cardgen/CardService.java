/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import vthub.cardgen.graphics.CardPainterFactory;
import vthub.cardgen.model.Card;
import vthub.cardgen.model.Specification;

import java.awt.image.BufferedImage;

@Singleton
public class CardService
{

    private final CardPainterFactory factory;

    @Inject
    public CardService(CardPainterFactory factory)
    {
        this.factory = factory;
    }

    public BufferedImage generate(Card card, Specification specification)
    {
        return factory.create(card, specification).draw();
    }

}

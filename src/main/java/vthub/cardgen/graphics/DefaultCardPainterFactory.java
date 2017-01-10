/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.graphics;

import com.google.inject.Inject;
import vthub.cardgen.model.Card;

public class DefaultCardPainterFactory implements CardPainterFactory{

    private final GraphicsService service;

    @Inject
    public DefaultCardPainterFactory(GraphicsService service) {
        this.service = service;
    }

    public DefaultCardPainter create(Card card) {
        return new DefaultCardPainter(card, service);
    }

}

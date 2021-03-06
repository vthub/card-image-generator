/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.graphics;

import vthub.cardgen.model.Card;
import vthub.cardgen.model.Specification;

public interface CardPainterFactory {

    CardPainter create(Card card, Specification specification);

}

package vthub.cardgen;

import vthub.cardgen.model.Card;

import java.awt.image.BufferedImage;

public interface Painter
{

    /**
     * Prepares an image using data from the card object
     *
     * @param card to be drawn
     * @return prepared image with card
     */
    BufferedImage draw(Card card);

}

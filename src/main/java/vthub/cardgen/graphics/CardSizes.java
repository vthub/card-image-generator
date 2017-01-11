/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.graphics;

class CardSizes
{
    static final int MIN_WIDTH_PX = 200;
    static final int MAX_WIDTH_PX = 1000;

    static final double OFFSET_MM = 1.00;
    static final double CARD_WIDTH_MM = 85.60;
    static final double CARD_HEIGHT_MM = 53.98;
    static final double CANVAS_WIDTH_MM = CARD_WIDTH_MM + 2 * OFFSET_MM;
    static final double CANVAS_HEIGHT_MM = CARD_HEIGHT_MM + 2 * OFFSET_MM;

    static final double CORNER_RADIUS_MM = 3.48;
    static final double NUMBER_Y_MM = 30.00;
    static final double EXP_X_MM = 38.00;
    static final double EXP_Y_MM = 40.00;
    static final double NAME_X_MM = 10.00;
    static final double NAME_Y_MM = 44.00;

    static final double NUMBER_FONT_MM = 6.00;
    static final double EXPIRY_FONT_MM = 5.00;
    static final double NAME_FONT_MM = EXPIRY_FONT_MM;

}

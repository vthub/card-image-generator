/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.graphics;

public class CardSizes
{
    public static final int MIN_WIDTH_PX = 200;
    public static final int MAX_WIDTH_PX = 1000;

    public static final double OFFSET_MM = 1.00;
    public static final double CARD_WIDTH_MM = 85.60;
    public static final double CARD_HEIGHT_MM = 53.98;
    public static final double CANVAS_WIDTH_MM = CARD_WIDTH_MM + 2 * OFFSET_MM;
    public static final double CANVAS_HEIGHT_MM = CARD_HEIGHT_MM + 2 * OFFSET_MM;

    public static final double CORNER_RADIUS_MM = 3.48;
    public static final double NUMBER_Y_MM = 30.00;
    public static final double EXP_X_MM = 38.00;
    public static final double EXP_Y_MM = 40.00;
    public static final double NAME_X_MM = 10.00;
    public static final double NAME_Y_MM = 44.00;

    public static final double NUMBER_FONT_MM = 6.00;
    public static final double EXPIRY_FONT_MM = 5.00;
    public static final double NAME_FONT_MM = EXPIRY_FONT_MM;

}

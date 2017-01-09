/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.graphics;

public class CardSizes
{

    private static final double WIDTH_MM = 85.60;

    private static final double HEIGHT_MM = 53.98;
    private static final double CORNER_RADIUS_MM = 3.48;
    private static final double NUMBER_Y_MM = 30.00;
    private static final double EXP_X_MM = 38.00;
    private static final double EXP_Y_MM = 40.00;
    private static final double NAME_X_MM = 10.00;
    private static final double NAME_Y_MM = 44.00;

    private static final double NUMBER_FONT_MM = 6.00;
    private static final double EXPIRY_FONT_MM = 5.00;
    private static final double NAME_FONT_MM = EXPIRY_FONT_MM;

    private static final int BASE_WIDTH_PX = 800;

    public static final int OFFSET_PX = 10;
    public static final double RATIO = (double) BASE_WIDTH_PX / WIDTH_MM;
    public static final int WIDTH_PX = toPx(WIDTH_MM);
    public static final int HEIGHT_PX = toPx(HEIGHT_MM);
    public static final int CANVAS_WIDTH_PX = WIDTH_PX + 2 * OFFSET_PX;
    public static final int CANVAS_HEIGHT_PX = HEIGHT_PX + 2 * OFFSET_PX;
    public static final int CORNER_RADIUS_PX = toPx(CORNER_RADIUS_MM);

    public static final int NUMBER_Y_PX = toPx(NUMBER_Y_MM);
    public static final int EXP_X_PX = toPx(EXP_X_MM);
    public static final int EXP_Y_PX = toPx(EXP_Y_MM);
    public static final int NAME_X_PX = toPx(NAME_X_MM);
    public static final int NAME_Y_PX = toPx(NAME_Y_MM);

    public static final int NUMBER_FONT_PX = toPx(NUMBER_FONT_MM);
    public static final int EXPIRY_FONT_PX = toPx(EXPIRY_FONT_MM);
    public static final int NAME_FONT_PX = toPx(NAME_FONT_MM);

    private static int toPx(double mm)
    {
        return (int) (RATIO * mm);
    }

}

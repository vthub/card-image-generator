package vthub.cardgen;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CardGenerationHandler implements Handler
{
    private static final int OFFSET = 10;

    private static final double WIDTH_MM = 85.60;
    private static final double HEIGHT_MM = 53.98;
    private static final double CORNER_RADIUS_MM = 3.48;
    private static final double NUMBER_X_MM = 10.00;
    private static final double NUMBER_Y_MM = 30.00;
    private static final double FONT_HEIGHT = 6.00;
    private static final double EXP_X_MM = 38.00;
    private static final double EXP_Y_MM = 40.00;
    private static final double NAME_X_MM = 10.00;
    private static final double NAME_Y_MM = 44.00;


    private static final int WIDTH_PX = 800;
    private static final double RATIO = (double) WIDTH_PX / WIDTH_MM;
    private static final int HEIGHT_PX = toPx(HEIGHT_MM);
    private static final int CORNER_RADIUS_PX = toPx(CORNER_RADIUS_MM);
    private static final int NUMBER_X_PX = toPx(NUMBER_X_MM);
    private static final int NUMBER_Y_PX = toPx(NUMBER_Y_MM);
    private static final int EXP_X_PX = toPx(EXP_X_MM);
    private static final int EXP_Y_PX = toPx(EXP_Y_MM);
    private static final int NAME_X_PX = toPx(NAME_X_MM);
    private static final int NAME_Y_PX = toPx(NAME_Y_MM);

    private BufferedImage image = new BufferedImage(WIDTH_PX + 2*OFFSET, HEIGHT_PX + 2*OFFSET, BufferedImage.TYPE_INT_RGB);

    @Override
    public void handle(Context ctx) throws Exception
    {
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH_PX + 2*OFFSET, HEIGHT_PX + 2*OFFSET);

        graphics.setColor(Color.RED);
        graphics.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        graphics.drawRoundRect(OFFSET, OFFSET, WIDTH_PX, HEIGHT_PX, CORNER_RADIUS_PX, CORNER_RADIUS_PX);

        Font f = new Font("Courier New", Font.BOLD, toPx(FONT_HEIGHT));
        graphics.setFont(f);
        graphics.setColor(Color.BLACK);
        graphics.drawString("1234 1234 1234 1234", NUMBER_X_PX, NUMBER_Y_PX);

        Font f2 = new Font("Courier New", Font.PLAIN, toPx(FONT_HEIGHT));
        graphics.setFont(f2);
        graphics.setColor(Color.GRAY);
        graphics.drawString("1234 1234 1234 1234", NUMBER_X_PX, NUMBER_Y_PX);

        graphics.drawString("12/20", EXP_X_PX, EXP_Y_PX);

        graphics.drawString("CARD HOLDER", NAME_X_PX, NAME_Y_PX);

        graphics.dispose();
        ctx.render(image);
    }

    public static int toPx(double mm) {
        return (int) (RATIO * mm);
    }
}

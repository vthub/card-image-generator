package vthub.cardgen.graphics;

import vthub.cardgen.model.Card;

import java.awt.*;
import java.awt.image.BufferedImage;

import static vthub.cardgen.CardGeneratorUtils.prepareNumberForPrint;
import static vthub.cardgen.graphics.CardSizes.*;

public class CardPainter
{

    private final Card card;
    private final BufferedImage image;
    private final Graphics2D graphics;

    public CardPainter(Card card)
    {
        this.card = card;
        image = new BufferedImage(CANVAS_WIDTH_PX, CANVAS_HEIGHT_PX, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
    }

    public void drawCard()
    {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, CANVAS_WIDTH_PX, CANVAS_HEIGHT_PX);

        graphics.setColor(Color.RED);
        graphics.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        graphics.drawRoundRect(OFFSET_PX, OFFSET_PX, WIDTH_PX, HEIGHT_PX, CORNER_RADIUS_PX, CORNER_RADIUS_PX);
    }

    public void drawNumber()
    {
        String text = prepareNumberForPrint(card.getNumber());

        black();
        Font font = font(NUMBER_FONT_PX);
        FontMetrics metrics = graphics.getFontMetrics(font);
        int x = (CANVAS_WIDTH_PX - metrics.stringWidth(text)) / 2;
        graphics.drawString(text, x, NUMBER_Y_PX);
    }

    public void drawExpiry()
    {
        black();
        font(EXPIRY_FONT_PX);
        graphics.drawString("12/20", EXP_X_PX, EXP_Y_PX);
    }

    public void drawCardHolder()
    {
        black();
        font(NAME_FONT_PX);
        graphics.drawString("CARD HOLDER", NAME_X_PX, NAME_Y_PX);
    }

    public BufferedImage draw()
    {
        drawCard();
        drawNumber();
        drawExpiry();
        drawCardHolder();
        graphics.dispose();
        return image;
    }

    public Font font(int size)
    {
        Font font = new Font("Courier New", Font.BOLD, size);
        graphics.setFont(font);
        return font;
    }

    public void black()
    {
        graphics.setColor(Color.BLACK);
    }

}

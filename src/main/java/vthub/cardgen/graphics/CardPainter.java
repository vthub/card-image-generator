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

    protected void drawBackground(Card card)
    {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, CANVAS_WIDTH_PX, CANVAS_HEIGHT_PX);
    }

    protected void drawCard(Card card)
    {
        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(0, 198, 255), WIDTH_PX, HEIGHT_PX, new Color(154, 255, 100));
        graphics.setPaint(gradientPaint);
        graphics.fillRoundRect(OFFSET_PX, OFFSET_PX, WIDTH_PX, HEIGHT_PX, CORNER_RADIUS_PX, CORNER_RADIUS_PX);

        black();
        graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        graphics.drawRoundRect(OFFSET_PX, OFFSET_PX, WIDTH_PX, HEIGHT_PX, CORNER_RADIUS_PX, CORNER_RADIUS_PX);
    }

    protected void drawNumber(String number)
    {
        black();
        Font font = font(NUMBER_FONT_PX);
        FontMetrics metrics = graphics.getFontMetrics(font);
        int x = (CANVAS_WIDTH_PX - metrics.stringWidth(number)) / 2;
        graphics.drawString(number, x, NUMBER_Y_PX);
    }

    protected void drawExpiry(String expiry)
    {
        black();
        font(EXPIRY_FONT_PX);
        graphics.drawString(expiry, EXP_X_PX, EXP_Y_PX);
    }

    protected void drawCardHolder(String cardHolder)
    {
        black();
        font(NAME_FONT_PX);
        graphics.drawString(cardHolder, NAME_X_PX, NAME_Y_PX);
    }

    protected Font font(int size)
    {
        Font font = new Font("Courier New", Font.BOLD, size);
        graphics.setFont(font);
        return font;
    }

    protected void black()
    {
        graphics.setColor(Color.BLACK);
    }

    public BufferedImage draw()
    {
        drawBackground(card);
        drawCard(card);
        drawNumber(prepareNumberForPrint(card.getNumber()));
        drawExpiry(card.getExpiry());
        drawCardHolder(card.getCardHolder());
        graphics.dispose();
        return image;
    }

}

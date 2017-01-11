/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.graphics;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import vthub.cardgen.model.Card;
import vthub.cardgen.model.Specification;

import java.awt.*;
import java.awt.image.BufferedImage;

import static vthub.cardgen.CardGeneratorUtils.prepareNumberForPrint;
import static vthub.cardgen.graphics.CardSizes.*;

public class DefaultCardPainter implements CardPainter
{

    private final Card card;
    private final BufferedImage image;
    private final Graphics2D graphics;
    private final GraphicsService graphicsService;
    private final Resizer resizer;

    @Inject
    public DefaultCardPainter(@Assisted Card card,
                              @Assisted Specification specification,
                              GraphicsService graphicsService,
                              ResizerFactory resizerFactory)
    {
        this.card = card;
        this.graphicsService = graphicsService;
        this.resizer = resizerFactory.create(specification);

        image = new BufferedImage(px(CANVAS_WIDTH_MM), px(CANVAS_HEIGHT_MM), BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
    }

    protected void drawBackground(Card card)
    {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, px(CANVAS_WIDTH_MM), px(CANVAS_HEIGHT_MM));
    }

    protected void drawCard(Card card)
    {
        GradientPaint gradientPaint = new GradientPaint(cardPx(0), cardPx(0), new Color(0, 198, 255), cardPx(CARD_WIDTH_MM), cardPx(CARD_HEIGHT_MM), new Color(154, 255, 100));
        graphics.setPaint(gradientPaint);
        graphics.fillRoundRect(cardPx(0), cardPx(0), px(CARD_WIDTH_MM), px(CARD_HEIGHT_MM), px(CORNER_RADIUS_MM), px(CORNER_RADIUS_MM));

        black();
        graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        graphics.drawRoundRect(cardPx(0), cardPx(0), px(CARD_WIDTH_MM), px(CARD_HEIGHT_MM), px(CORNER_RADIUS_MM), px(CORNER_RADIUS_MM));
    }

    protected void drawNumber(String number)
    {
        black();
        Font font = font(px(NUMBER_FONT_MM));
        FontMetrics metrics = graphics.getFontMetrics(font);
        int x = (px(CANVAS_WIDTH_MM) - metrics.stringWidth(number)) / 2;
        graphics.drawString(number, x, cardPx(NUMBER_Y_MM));
    }

    protected void drawExpiry(String expiry)
    {
        black();
        font(px(EXPIRY_FONT_MM));
        graphics.drawString(expiry, cardPx(EXP_X_MM), cardPx(EXP_Y_MM));
    }

    protected void drawCardHolder(String cardHolder)
    {
        black();
        font(px(NAME_FONT_MM));
        graphics.drawString(cardHolder, cardPx(NAME_X_MM), cardPx(NAME_Y_MM));
    }

    protected Font font(int size)
    {
        Font font = new Font(graphicsService.getFont(), Font.BOLD, size);
        graphics.setFont(font);
        return font;
    }

    protected void black()
    {
        graphics.setColor(Color.BLACK);
    }

    protected int px(double size)
    {
        return resizer.toPx(size);
    }

    protected int cardPx(double size)
    {
        return resizer.toPx(OFFSET_MM + size);
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

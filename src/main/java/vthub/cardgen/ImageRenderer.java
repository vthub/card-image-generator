package vthub.cardgen;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import ratpack.handling.Context;
import ratpack.render.RendererSupport;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

public class ImageRenderer extends RendererSupport<BufferedImage>
{
    @Override
    public void render(Context ctx, BufferedImage bufferedImage) throws Exception
    {
        ByteBuf buffer = ctx.get(ByteBufAllocator.class).buffer();
        OutputStream outputStream = new ByteBufOutputStream(buffer);
        ImageIO.write(bufferedImage, "png", outputStream);

        ctx.getResponse().getHeaders().set("Pragma", "No-cache");
        ctx.getResponse().getHeaders().set("Cache-Control", "no-cache,no-store,max-age=0");
        ctx.getResponse().getHeaders().setDate("Expires", new Date(LocalDate.ofEpochDay(0).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()));

        ctx.getResponse().getHeaders().add("Content-Disposition", "inline; filename=\"card.png\"");
        ctx.getResponse()
                .contentType("image/png")
                .send(buffer);
    }
}

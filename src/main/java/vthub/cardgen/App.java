/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen;

import ratpack.guice.Guice;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

import static java.lang.String.format;

public class App
{

    public static final String CARD = "card";

    public static void main(String[] args) throws Exception {
        RatpackServer.start(s -> s
                .serverConfig(c -> c.baseDir(BaseDir.find()))
                .registry(Guice.registry(b -> b.module(Module.class)))
                .handlers(chain -> chain
                        .register(r -> r.add(new ImageRenderer()))
                        .path(format("card/:%s?", CARD), CardHandler.class)
                        .all(ctx -> ctx.render(ctx.file("index.html")))
                )
        );
    }

}

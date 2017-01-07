package vthub.cardgen;

import ratpack.guice.Guice;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

public class App
{

    public static void main(String[] args) throws Exception {
        RatpackServer.start(s -> s
                .serverConfig(c -> c.baseDir(BaseDir.find()))
                .registry(Guice.registry(b -> b.module(Module.class)))
                .handlers(chain -> chain
                        .register(r -> r.add(new ImageRenderer()))
                        .path("card", CardHandler.class)
                        .all(ctx -> ctx.render(""))
                )
        );
    }

}

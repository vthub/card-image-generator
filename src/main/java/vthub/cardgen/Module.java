/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import vthub.cardgen.graphics.CardPainter;
import vthub.cardgen.graphics.CardPainterFactory;
import vthub.cardgen.graphics.DefaultCardPainter;

public class Module extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(CardHandler.class);
        bind(Painter.class).to(DefaultPainter.class);
        install(new FactoryModuleBuilder().implement(CardPainter.class, DefaultCardPainter.class).build(CardPainterFactory.class));
    }
}

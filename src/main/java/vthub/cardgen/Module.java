/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import vthub.cardgen.graphics.*;

public class Module extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(CardHandler.class);
        install(new FactoryModuleBuilder().implement(CardPainter.class, DefaultCardPainter.class).build(CardPainterFactory.class));
        install(new FactoryModuleBuilder().implement(Resizer.class, DefaultResizer.class).build(ResizerFactory.class));
    }
}

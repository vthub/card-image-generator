/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(CardHandler.class);
        bind(CardService.class);
        bind(Painter.class).to(DefaultPainter.class);
    }
}

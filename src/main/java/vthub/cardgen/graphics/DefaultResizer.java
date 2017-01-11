/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.graphics;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import vthub.cardgen.model.Specification;

import static vthub.cardgen.graphics.CardSizes.MAX_WIDTH_PX;
import static vthub.cardgen.graphics.CardSizes.MIN_WIDTH_PX;

public class DefaultResizer implements Resizer
{

    private final double ratio;

    @Inject
    public DefaultResizer(@Assisted Specification specification)
    {
        int width = Math.max(MIN_WIDTH_PX, Math.min(MAX_WIDTH_PX, specification.getWidth()));
        this.ratio = (double) width / CardSizes.CANVAS_WIDTH_MM;
    }

    @Override
    public int toPx(double sizeInMm)
    {
        return (int) (ratio * sizeInMm);
    }

}

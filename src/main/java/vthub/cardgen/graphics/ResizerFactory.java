/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.graphics;

import vthub.cardgen.model.Specification;

public interface ResizerFactory
{

    Resizer create(Specification specification);

}

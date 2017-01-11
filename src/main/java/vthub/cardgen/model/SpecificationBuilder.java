/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.model;

public final class SpecificationBuilder
{
    private int width;
    private String font;

    private SpecificationBuilder() {}

    public static SpecificationBuilder aSpecification() { return new SpecificationBuilder();}

    public SpecificationBuilder width(int width)
    {
        this.width = width;
        return this;
    }

    public SpecificationBuilder font(String font)
    {
        this.font = font;
        return this;
    }

    public Specification build()
    {
        Specification specification = new Specification();
        specification.setWidth(width);
        specification.setFont(font);
        return specification;
    }
}

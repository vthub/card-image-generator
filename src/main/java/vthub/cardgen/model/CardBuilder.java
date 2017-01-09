/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.model;

public final class CardBuilder
{
    private String number;
    private String expiry;
    private String cardHolder;

    private CardBuilder() {}

    public static CardBuilder aCard() { return new CardBuilder();}

    public CardBuilder number(String number)
    {
        this.number = number;
        return this;
    }

    public CardBuilder expiry(String expiry)
    {
        this.expiry = expiry;
        return this;
    }

    public CardBuilder cardHolder(String cardHolder)
    {
        this.cardHolder = cardHolder;
        return this;
    }

    public Card build()
    {
        Card card = new Card();
        card.setNumber(number);
        card.setExpiry(expiry);
        card.setCardHolder(cardHolder);
        return card;
    }
}

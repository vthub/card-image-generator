/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen.model;

public class Card
{

    private final String number;
    private final String expiry;
    private final String cardHolder;

    public Card(String number, String expiry, String cardHolder)
    {
        this.number = number;
        this.expiry = expiry;
        this.cardHolder = cardHolder;
    }

    public String getNumber()
    {
        return number;
    }

    public String getExpiry()
    {
        return expiry;
    }

    public String getCardHolder()
    {
        return cardHolder;
    }

}

package vthub.cardgen.model;

public class Card
{

    private String number;
    private String expiry;
    private String cardHolder;

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getExpiry()
    {
        return expiry;
    }

    public void setExpiry(String expiry)
    {
        this.expiry = expiry;
    }

    public String getCardHolder()
    {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder)
    {
        this.cardHolder = cardHolder;
    }
}

package vthub.cardgen;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static vthub.cardgen.CardGeneratorUtils.luhnChecksum;
import static vthub.cardgen.CardGeneratorUtils.stringToInts;

public class CardGeneratorTest
{

    private CardGenerator generator;

    @Before
    public void setUp() throws Exception
    {
        generator = new CardGenerator();
    }

    @Test
    public void testGenerateNumber() throws Exception
    {
        String number = generator.generateNumber();
        assertThat(number.length(), is(16));
        assertThat(luhnChecksum(stringToInts(number)), is(0));
    }
}

package vthub.cardgen;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static vthub.cardgen.CardGeneratorUtils.*;

public class CardGeneratorUtilsTest
{

    @Test
    public void testStringToInts() throws Exception
    {
        assertThat(stringToInts("1357908642"), is(new int[]{1, 3, 5, 7, 9, 0, 8, 6, 4, 2}));
        assertThat(stringToInts(""), is(new int[]{}));
        assertThat(stringToInts(null), is(new int[]{}));
    }

    @Test
    public void testLuhnChecksum() throws Exception
    {
        assertThat(luhnChecksum(stringToInts("4534987582177718")), is(0));
        assertThat(luhnChecksum(stringToInts("6011481272108982")), is(0));
        assertThat(luhnChecksum(stringToInts("6706386224805934")), is(0));
    }

    @Test
    public void testLuhnCheckDigit() throws Exception
    {
        assertThat(luhnCheckDigit(stringToInts("453498758217771")), is(8));
        assertThat(luhnCheckDigit(stringToInts("601148127210898")), is(2));
        assertThat(luhnCheckDigit(stringToInts("670638622480593")), is(4));
    }

    @Test
    public void testGenerateNumber() throws Exception
    {
        String number = generateNumber();
        assertThat(number.length(), is(16));
        assertThat(luhnChecksum(stringToInts(number)), is(0));
    }

    @Test
    public void testPrepareNumberForPrint() throws Exception
    {
        assertThat(prepareNumberForPrint(null), is(""));
        assertThat(prepareNumberForPrint(""), is(""));
        assertThat(prepareNumberForPrint("123"), is("123"));
        assertThat(prepareNumberForPrint("1234"), is("1234"));
        assertThat(prepareNumberForPrint("12345"), is("1234 5"));
        assertThat(prepareNumberForPrint("1234567890"), is("1234 5678 90"));
    }
}

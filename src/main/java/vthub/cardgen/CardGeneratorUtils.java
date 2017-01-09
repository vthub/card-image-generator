/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardGeneratorUtils
{

    private CardGeneratorUtils()
    {
    }

    public static int[] stringToInts(String s)
    {
        return Optional.ofNullable(s).orElse("")
                .chars()
                .map(c -> Integer.valueOf(Character.toString((char) c)))
                .toArray();
    }

    public static int luhnChecksum(int... ints)
    {
        int oddSum = IntStream.range(0, ints.length)
                .filter(i -> i % 2 != 0)
                .map(i -> ints[ints.length - i - 1])
                .map(i -> i * 2)
                .map(i -> i > 9 ? i - 9 : i)
                .sum();
        int evenSum = IntStream.range(0, ints.length)
                .filter(i -> i % 2 == 0)
                .map(i -> ints[ints.length - i - 1])
                .sum();
        return (oddSum + evenSum) % 10;
    }

    public static int luhnCheckDigit(int... ints)
    {
        int checksum = luhnChecksum(ArrayUtils.add(ints, 0));
        return checksum == 0 ? checksum : 10 - checksum;
    }

    public static String generateNumber()
    {
        int[] ints = IntStream.rangeClosed(1, 15)
                .map(i -> new Random().nextInt(10))
                .toArray();
        return Arrays.stream(ArrayUtils.add(ints, luhnCheckDigit(ints)))
                .boxed()
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    public static String prepareNumberForPrint(String number)
    {
        String s = StringUtils.substring(number, 0, 19);
        return IntStream.range(0, (int) Math.ceil((double) StringUtils.length(s) / 4))
                .boxed()
                .map(i -> StringUtils.substring(s, i * 4, (i + 1) * 4))
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.joining(" "));
    }

}

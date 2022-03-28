package fr.istic.vv;
import net.jqwik.api.*;

public class RomanNumeralTest {

    @Property
    boolean parseToRomanNumeral(@ForAll("symbols") String aString) {
        int parsed = RomanNumeraUtils.parseRomanNumeral(aString);
        return RomanNumeraUtils.toRomanNumeral(parsed).equals(aString);
    }

    @Property
    boolean toParseRomanNumeral(@ForAll("numbers") int anInteger) {
        String toNumeral = RomanNumeraUtils.toRomanNumeral(anInteger);
        return RomanNumeraUtils.parseRomanNumeral(toNumeral) == anInteger;
    }

    @Provide
    Arbitrary<String> symbols() {
       return Arbitraries.strings()
                .withChars('M', 'D', 'C', 'X', 'V', 'I').ofMinLength(1);
    }

    @Provide
    Arbitrary<Integer> numbers() {
        return Arbitraries.integers().between(1, 3999);
    }

}

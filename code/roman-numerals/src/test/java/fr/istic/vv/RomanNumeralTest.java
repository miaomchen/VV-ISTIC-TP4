package fr.istic.vv;
import net.jqwik.api.*;

public class RomanNumeralTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }
}

package fr.istic.vv;
import net.jqwik.api.*;

public class SortingTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }
}
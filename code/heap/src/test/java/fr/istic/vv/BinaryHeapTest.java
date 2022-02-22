package fr.istic.vv;
import net.jqwik.api.*;


public class BinaryHeapTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }
}

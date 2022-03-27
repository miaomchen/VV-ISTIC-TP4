package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.lifecycle.BeforeProperty;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class SortingTest {

    Comparator<Integer> comparator;

    @BeforeProperty
    void beforeProperty() {
        comparator = Integer::compareTo;
    }

    @Property
    boolean testBubbleSort(@ForAll @Size(max = 100) Integer[] anArray) {
        Integer[] sortedArray = Sorting.bubblesort(anArray, comparator);
        for (int i = 0; i < sortedArray.length-1; i++) {
            if ( comparator.compare(sortedArray[i], sortedArray[i+1]) > 0) {
                return false;
            }
        }
        return true;
    }

    @Property
    boolean testQuickSort(@ForAll @Size(max = 100) Integer[] anArray) {
        Integer[] sortedArray = Sorting.quicksort(anArray, comparator);
        for (int i = 0; i < sortedArray.length-1; i++) {
            if ( comparator.compare(sortedArray[i], sortedArray[i+1]) > 0) {
                return false;
            }
        }
        return true;
    }

    @Property
    boolean testMergeSort(@ForAll @Size(max = 100) Integer[] anArray) {
        Integer[] sortedArray = Sorting.mergesort(anArray, comparator);
        for (int i = 0; i < sortedArray.length-1; i++) {
            if ( comparator.compare(sortedArray[i], sortedArray[i+1]) > 0) {
                return false;
            }
        }
        return true;
    }


}

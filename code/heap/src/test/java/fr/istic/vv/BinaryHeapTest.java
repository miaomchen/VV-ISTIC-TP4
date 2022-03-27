package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.lifecycle.BeforeProperty;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


public class BinaryHeapTest {

    BinaryHeap<Integer> heap;

    @BeforeProperty
    void beforeProperty() {
         heap = new BinaryHeap<Integer>(null);
    }

    @Property
    boolean throwExceptionOnEmptyPop() {
        try {
            heap.pop();
        } catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }

    @Property
    boolean throwExceptionOnEmptyPeek() {
        try {
            heap.peek();
        } catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }

    @Property
    boolean pushPopAValue(@ForAll int anInteger) {
        heap.push(anInteger);
        return heap.pop() == anInteger;
    }

    @Property
    boolean pushPeekAValue(@ForAll int anInteger) {
        heap.push(anInteger);
        return heap.peek() == anInteger;
    }

    @Property
    boolean pushPopCountAValue(@ForAll int anInteger) {
        heap.push(anInteger);
        heap.pop();
        return heap.count() == 0;
    }

    @Property
    boolean pushCountAList(@ForAll List<Integer> aList) {
        for (Integer i: aList) {
            heap.push(i);
        }
        return heap.count() == aList.size();
    }

    @Property
    boolean pushAListPeekOne(@ForAll List<Integer> aList) {
        for (Integer i: aList) {
            heap.push(i);
        }
        return Objects.equals(heap.peek(), Collections.min(aList));
    }

    @Property
    boolean pushAListPopOne(@ForAll List<Integer> aList) {
        for (Integer i: aList) {
            heap.push(i);
        }
        return Objects.equals(heap.pop(), Collections.min(aList));
    }

    @Property
    boolean pushPopCountAList(@ForAll @Size(10) List<Integer> aList) {
        for (int i = 0; i < 10; i++) {
            heap.push(aList.get(i));
        }

        for (int j = 0; j < 5; j++) {
            heap.pop();
        }

        return heap.count() == 5;
    }

    @Property
    boolean pushPopAList(@ForAll @Size(10) List<Integer> aList) {
        for (int i = 0; i < 10; i++) {
            heap.push(aList.get(i));
        }

        for (int j = 0; j < 5; j++) {
            heap.pop();
        }

        return heap.pop() == Collections.min(aList);
    }

    @Property
    boolean pushPopAListSeveralTimes(@ForAll @Size(10) List<Integer> aList) {
        for (int i = 0; i < 5; i++) {
            heap.push(aList.get(i));
        }

        for (int j = 0; j < 3; j++) {
            heap.pop();
        }

        for (int i = 5; i < 10; i++) {
            heap.push(aList.get(i));
        }

        return Objects.equals(heap.peek(), Collections.min(aList)) && heap.count() == 7;
    }

}

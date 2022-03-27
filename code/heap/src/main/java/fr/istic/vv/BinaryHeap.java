package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeap<T> {

    private ArrayList<T> items;

    private Comparator<T> comparator;


    public BinaryHeap(Comparator<T> comparator) {
        items = new ArrayList<T>();
        this.comparator = comparator;
    }

    /**
     * returns and removes the minimum object in the heap. If the heap is empty it throws a NotSuchElementException
     * @return
     */
    public T pop() {
        if (count() == 0) {
            throw new NoSuchElementException();
        } else if (count() == 1) {
            return items.remove(0);
        } else {
            T e = items.get(0);

            T lastElement = items.remove(count() - 1);
            if (lastElement != null) {
                items.set(0, lastElement);
                siftDown(0);
            }
            return e;
        }
    }

    /**
     * returns the minimum object but it does not remove it
     * @return the minimum object
     */
    public T peek() {

        if (count() == 0) {
            throw new NoSuchElementException();
        } else {
            return items.get(0);
        }

    }

    /**
     * adds an element to the BinaryHeap
     * @param element
     */
    public void push(T element) {
        if (element ==  null) {
            throw new IllegalArgumentException("Element can not be null");
        }
        items.add(element);
        siftUp(count()-1);
    }

    /**
     * returns the number of elements in the BinaryHeap
     * @return the number of elements
     */
    public int count() { return items.size(); }


    private int compare(T e1, T e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        } else {
            return ((Comparable<T>) e1).compareTo(e2);
        }
    }

    /**
     * return the index of left child
     * @param i index of parent
     * @return index of left child
     */
    private int leftChildIndex(int i) {
        return 2*i + 1;
    }



    /**
     * return the index of right child
     * @param i index of parent
     * @return index of right child
     */
/*
    private int rightChildIndex(int i) {
        return 2*i + 2;
    }
*/

    /**
     * return the index of parent
     * @param i index of left child
     * @return index of parent
     */
    private int parentIndex(int i) {
        return (i-1)/2;
    }

    public void siftDown(int i) {
        T e = items.get(i);
        int leftIndex = leftChildIndex(i);

        while (leftIndex < count()) {
            T selectedE = items.get(leftIndex);
            int selectedIndex = leftIndex;
            int rightIndex = leftIndex + 1;

            if (rightIndex < count()) {
                if (compare(items.get(rightIndex), selectedE) < 0) {
                    selectedE = items.get(rightIndex);
                    selectedIndex = rightIndex;
                }
            }

            if (compare(selectedE, e) < 0) {
                items.set(i,selectedE);
                i = selectedIndex;
                leftIndex = leftChildIndex(i);
            } else {
                break;
            }
        }

        items.set(i,e);
    }

    public void siftUp(int i) {
        T e = items.get(i);
        while (i > 0) {
            int parentIndex = parentIndex(i);
            if (compare(e, items.get(parentIndex)) < 0) {
                items.set(i, items.get(parentIndex));
                i = parentIndex;
            } else {
                break;
            }
        }
        items.set(i, e);
    }


}

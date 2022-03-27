package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {

        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if (comparator.compare(array[j], array[j+1]) > 0) {
                    swap(array, j, j+1);
                }
            }
        }
        return array;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        subSort(array,comparator,0,array.length-1);
        return array;
    }

    private static <T> void subSort(T[] array, Comparator<T> comparator, int start, int end) {
        if (start < end) {
            T pivot = array[start];
            int low = start;
            int high = end + 1;

            while (true) {
                while (low < end && comparator.compare(array[++low], pivot) <= 0);
                while (high > start && comparator.compare(array[--high], pivot) >= 0);
                if (low < high) {
                    swap(array, low, high);
                } else {
                    break;
                }
            }

            swap(array,start, high);
            subSort(array,comparator,start,high-1);
            subSort(array,comparator,high+1,end);
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        mergeSortLowHigh(array,comparator,0, array.length-1);
        return array;
    }

    private static <T> void mergeSortLowHigh(T[] array, Comparator<T> comparator, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // left
            mergeSortLowHigh(array, comparator,low,mid);
            // right
            mergeSortLowHigh(array, comparator,mid + 1,high);
            // merge left and right
            merge(array,comparator,low,mid,high);
        }
    }

    private static <T> void merge(T[] array, Comparator<T> comparator, int low, int mid, int high) {
        T[] temp = Arrays.copyOf(array,high - low + 1);
        int i = low; // left pointer
        int j = mid + 1;  // right pointer
        int k = 0;

        // move the lesser objects into new array
        while (i <= mid && j <= high) {
            if (comparator.compare(array[i], array[j]) < 0) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        // move the rest of left objects into new array
        while (i <= mid) {
            temp[k++] = array[i++];
        }

        // move the rest of right objects into new array
        while (j <= high) {
            temp[k++] = array[j++];
        }

        // bug temp.length-1 was found by the test, corrected by temp.length
        // cover the original array by the new array
        for (int k2 = 0; k2 < temp.length; k2++) {
            array[k2 + low] = temp[k2];
        }
    }

}

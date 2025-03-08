package edu.grinnell.csc207.sortslab;

import java.util.Arrays;

/**
 * A collection of sorting algorithms over generic arrays.
 */
public class Sorts {
    
    public static int binarySearch(int value, int[] arr, int lo, int hi) {
        int mid = lo + (hi - lo)/ 2;
        if (arr[mid]==value) {
            return mid;
        } else if (lo == hi) {
            return -1;
        } else if (arr[mid]<value) {
            return(binarySearch(value,arr,lo,mid));
        } else {
            return(binarySearch(value,arr,mid+1,hi));
        } 
    }
    
    /**
     * Swaps indices <code>i</code> and <code>j</code> of array <code>arr</code>.
     * @param <T> the carrier type of the array
     * @param arr the array to swap
     * @param i the first index to swap
     * @param j the second index to swap
     */
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Sorts the array according to the bubble sort algorithm:
     * <pre>
     * [ unprocessed | i largest elements in order ]
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i ++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j+1]) > 0) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    /**
     * Sorts the array according to the selection sort algorithm:
     * <pre>
     * [ i smallest elements in order | unprocessed ]
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i ++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    /**
     * Sorts the array according to the insertion sort algorithm:
     * <pre>
     * [ i elements in order | unprocessed ] 
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if(arr[j].compareTo(arr[i])<0) {
                    swap(arr,j,i);
                }
            }
        }
    }

    /**
     * Sorts the array according to the merge sort algorithm:
     * <pre>
     * [ sorted | sorted ] -> [ sorted ]
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
        if (arr.length <= 1) {
            return;
        }
        
        int mid = arr.length/2;
        T[] arr1 = Arrays.copyOfRange(arr,0,mid);
        T[] arr2 = Arrays.copyOfRange(arr,mid,arr.length);
        
        mergeSort(arr1);
        mergeSort(arr2);
        
        merge(arr,arr1,arr2);
    }
    
    public static <T extends Comparable<? super T>> void merge(T[] arr, T[] arr1, T[] arr2) {

        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i].compareTo(arr2[j]) <= 0) {
                arr[k] = arr1[i];
                k++;
                i++;
            } else {
                arr[k] = arr2[j];
                k++;
                j++;
            }
        }
        while (i < arr1.length) {
            arr[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            arr[k++] = arr2[j++];
        }
    }
    

/**
     * Sorts the array according to the quick sort algorithm:
     * <pre>
     * []
     * </pre>      
     * @param <T>
     * @param arr
     * pick some number to sort the array into two halves. One half is less than
     * that number, the other half is greater. then recursively doing this for 
     * the new halves, etc
     */
     public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSortHelper(T[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = pivotGetter(arr, low, high);
            quickSortHelper(arr, low, pivotIndex - 1); // Sort left half
            quickSortHelper(arr, pivotIndex + 1, high); // Sort right half
        }
    }

    private static <T extends Comparable<? super T>> int pivotGetter(T[] arr, int low, int high) {
        int pivotIndex = medianOfThree(arr, low, high);
        T pivot = arr[pivotIndex];

        // Move pivot to end
        swap(arr, pivotIndex, high);

        int i = low - 1;
        int j = high - 1;

        while (i < j) {
            while (arr[i].compareTo(pivot) <= 0) {
                i++;
            }
            while (arr[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }

        // Place pivot in correct position
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static <T extends Comparable<? super T>> int medianOfThree(T[] arr, int low, int high) {
        int mid = low + (high - low) / 2;

        // Move the median to index mid
        if (arr[low].compareTo(arr[mid]) > 0) {
            swap(arr, low, mid);
        }
        if (arr[low].compareTo(arr[high]) > 0) {
            swap(arr, low, high);
        }
        if (arr[mid].compareTo(arr[high]) > 0) {
            swap(arr, mid, high);
        }
        return mid;
    }
}


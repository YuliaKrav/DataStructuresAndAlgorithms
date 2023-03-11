import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {

        int[] array = {0, 0, -200, 0, 10, 0, 7};

        System.out.println("A given array : " + Arrays.toString(array));
        heapSort(array);
        System.out.println("A sorted array : " + Arrays.toString(array));
    }

    public static void heapSort(int[] array) {

        for (int i = array.length / 2 - 1; i >= 0; i--) {
            buildHeap(array, array.length, i);
        }

        for (int i = array.length - 1; i >= 0; i--) {
            int forSwap = array[0];
            array[0] = array[i];
            array[i] = forSwap;

            buildHeap(array, i, 0);
        }
    }

    public static void buildHeap(int[] array, int heapLength, int indexMax) {

        int leftChildIndex = 2 * indexMax + 1;
        int rightChildIndex = 2 * indexMax + 2;
        int elementMaxIndex = indexMax;

        if (leftChildIndex < heapLength && array[leftChildIndex] > array[elementMaxIndex]) {
            elementMaxIndex = leftChildIndex;
        }

        if (rightChildIndex < heapLength && array[rightChildIndex] > array[elementMaxIndex]) {
            elementMaxIndex = rightChildIndex;
        }

        if (elementMaxIndex != indexMax) {
            int forSwap = array[indexMax];
            array[indexMax] = array[elementMaxIndex];
            array[elementMaxIndex] = forSwap;
            buildHeap(array, heapLength, elementMaxIndex);
        }
    }
}

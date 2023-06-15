import java.util.Arrays;
import java.util.Random;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = generateRandomArray(20);

        System.out.println("Original Array: " + Arrays.toString(arr));

        heapSort(arr);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from the heap in descending order
        for (int i = n - 1; i > 0; i--) {
            // Swap root (maximum element) with the last element
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Heapify the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check if left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Check if right child is larger than root
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not the root, swap and heapify the affected sub-tree
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(100) + 1;
        }

        return arr;
    }
}

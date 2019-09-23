import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            var mid = (high + low) / 2;
            mergeSort(arr,low,mid);
            mergeSort(arr,mid+1,high);
            merge(arr,low,high);
        }
    }

    private static void merge(int[] arr, int low, int high) {
        int[] temp = new int[high-low+1];
        int l = low;
        int h = (high+low)/2+1;
        int k = 0;
        while(l <= (high+low)/2 && h <= high) {
            if (arr[l] < arr[h])
                temp[k++] = arr[l++];
            else
                temp[k++] = arr[h++];
        }
        while(l<=(high+low)/2)
            temp[k++] = arr[l++];
        while(h<=high)
            temp[k++] = arr[h++];
        for(int kk = 0; kk < temp.length;kk++)
            arr[kk + low] = temp[kk];
    }

    public static void main(String[] args) {
        int arr[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        MergeSort.mergeSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}

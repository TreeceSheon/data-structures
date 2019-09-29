/**
 * 基于双指针从数组两边运动比较的快速排序，pivot默认为数组第一个元素。
 * author:Zhuang Xiong;
 */
public class QuickSort2 {
    public static void quickSort(int[] arr, int begin, int end) {
        if ( begin < end) {
            int pivot = partition(arr,begin,end);
            quickSort(arr,begin,pivot-1);
            quickSort(arr,pivot + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        var pivot = arr[begin];
        while (begin < end) {
            while (arr[end] > pivot)
                end--;
            arr[begin] = arr[end];
            begin++;
            while (begin < end && arr[begin] < pivot)
                begin++;
            arr[end] = arr[begin];
        }
        //此时不能赋值为 arr[begin] = pivot
        //如果数组满足在不执行第二个whiled的情况下排列成功的话，begin就已经等于了high，因为begin的值的18行自加，
        //导致begin在此情况下会大于high一位数。
        arr[end] = pivot;
        //同理也不能返回begin。

        //把18行的自加删除后便可将最后两行的end替换成begin，此时第二个while会进行一次无用的比较。
        return end;
    }
}

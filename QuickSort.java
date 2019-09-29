/**
 * 基于快慢指针从左往右比较的快速排序算法，此时pivot默认去末尾值。
 * author:Zhuang Xiong;
 */
public class QuickSort1 {


    public static void quickSort(int[] num,int begin,int end) {
        if (begin < end) {
            int pivot =  partition(num, 0, end);
            quickSort(num, 0, pivot - 1);
            quickSort(num, pivot + 1, end);
        }
    }

    private static int partition(int[] num, int begin, int end) {
        int i = -1;
        int j = 0;
        var pivot = num[end];
        while (j < end) {
            if (num[j] < pivot) {
                i++;
                var temp = num[i];
                num[i] = num[j];
                num[j] = temp;
                j++;
            } else {
                j++;
            }
        }
        if (j == end) {
            var temp = num[i+1];
            num[i+1] = num[end];
            num[end] = temp;
        }
        return i + 1;
    }
}

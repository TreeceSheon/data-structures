import java.util.Arrays;

/**
 *桶排序首先遍历一次待排数组的所有元素，再遍历一次桶元素，所以时间复杂度为O(n+m)。
 * 时间复杂度较低，但空间利用率也很低，适合数据跨度较低且最好已知数组数据上限的数组排序。
 */
public class BucketSort {

    public static void bucketSort(int[] arr, int size){
        int[] bucket = new int[size+1];
        for(int num : arr) {
            bucket[num]++;
        }
        int pointer = 0;
        int i = 0;
        while (pointer <= size) {
                while (bucket[pointer] > 0) {
                    arr[i] = pointer;
                    bucket[pointer]--;
                    i++;
                }
                pointer++;
            }
        }

}

package com.wyc.tips.algorithm.sort.quick;/**
 * Created by wyc on 2016/12/22.
 */

import com.wyc.tips.algorithm.sort.SortBase;

import java.util.Arrays;

/**
 * 快速排序,时间复杂度最好O(nlog2(n))，最差O(n²),空间复杂度O(1)
 * 不稳定的排序算法
 * @author wangyongcan
 * @Date 2016/12/22 16:50
 */
public class QuickSort extends SortBase {
    @Override
    public void sort(int[] array) {
        quickSort(array,0,array.length-1);
    }

    private void quickSort(int[] array,int start,int end) {
        if(start >= end) {
            return;
        }
        int firstValue = array[start];
        int position = start;
        int right = end,left = start;
        while(right > left) {
            while(right > left && array[right] >= firstValue) {
                right --;
            }
            if(right > left) {
                swap(position,right,array);
                position = right;
                left++;
            }
            while(right > left && array[left] <= firstValue) {
                left ++;
            }
            if(right > left) {
                swap(position,left,array);
                position = left;
                // 可以少一次比较
                right--;
            }
        }
        array[right] = firstValue;
        quickSort(array,start,position-1);
        quickSort(array,position+1,end);
    }

    public static SortBase instance = new QuickSort();
    public static SortBase getInstance() {
        return instance;
    }

    public static void main(String args[]) {
        int array[] = instance.getUnSortArray(20);
        System.out.println(Arrays.toString(array));
        instance.sort(array);
        System.out.println(Arrays.toString(array));
    }

}

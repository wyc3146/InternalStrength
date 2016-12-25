package com.wyc.tips.algorithm.sort.merge;/**
 * Created by wyc on 2016/12/23.
 */

import com.wyc.tips.algorithm.sort.SortBase;
import com.wyc.tips.algorithm.sort.simple_select.SimpleSelectSort;

import java.util.Arrays;

/**
 * 归并排序，时间复杂度O(nlogn)
 * 稳定排序算法
 * 算法描述：分治法，先分成多个子序列，排序子序列，排序完后将两个子序列合并
 * @author wangyongcan
 * @Date 2016/12/23 18:24
 */
public class MergeSort extends SortBase {
    @Override
    public void sort(int[] array) {
        mergeSort(array,0,array.length-1);
    }

    private void mergeSort(int []array,int start,int end) {
        if(end-start <= 0) {
            return ;
        } else if(end - start == 1) {
            if(array[start] > array[end]) {
                swap(start,end,array);
            }
            return ;
        }

        int mid = (start+end) >> 1;
        mergeSort(array,start,mid);
        mergeSort(array,mid+1,end);

        int left = start,right = mid+1;
        int _array[] = new int[end-start+1];
        int position = 0;

        while(left <= mid && right <= end) {
            int leftValue = array[left];
            int rightValue = array[right];
            if(leftValue < rightValue) {
                _array[position++] = array[left++];
            } else {
                _array[position++] = array[right++];
            }
        }
        if(left > mid) {
            System.arraycopy(array,right,_array,position,_array.length-position);
        }
        if(right > end) {
            System.arraycopy(array,left,_array,position,_array.length-position);
        }
        System.arraycopy(_array,0,array,start,_array.length);
    }

    public static SortBase instance = new MergeSort();
    public static SortBase getInstance() {
        return instance;
    }

    public static void main(String args[]) {
        int array[] = instance.getUnSortArray(11);
        System.out.println(Arrays.toString(array));
        instance.sort(array);
        System.out.println(Arrays.toString(array));
    }

}

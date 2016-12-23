package com.wyc.tips.algorithm.sort.merge;/**
 * Created by wyc on 2016/12/23.
 */

import com.wyc.tips.algorithm.sort.SortBase;
import com.wyc.tips.algorithm.sort.simple_select.SimpleSelectSort;

import java.util.Arrays;

/**
 * 归并排序
 * @author wangyongcan
 * @Date 2016/12/23 18:24
 */
public class MergeSort extends SortBase {
    @Override
    public void sort(int[] array) {

    }

    private void mergeSort(int []array,int start,int end) {
        int mid = (start+end) >> 2;
        mergeSort(array,start,mid);
        mergeSort(array,mid+1,end);

        int left = start,right = mid+1;
        while(left > mid && right > end) {
            
        }

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

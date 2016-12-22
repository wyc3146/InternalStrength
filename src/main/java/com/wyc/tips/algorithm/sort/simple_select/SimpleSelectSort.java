package com.wyc.tips.algorithm.sort.simple_select;/**
 * Created by wyc on 2016/12/21.
 */

import com.wyc.tips.algorithm.sort.SortBase;

import java.util.Arrays;

/**
 * 简单选择排序，时间复杂度O(n²)，空间复杂度O(1)
 * 不稳定排序算法(比如5、2、5、1、6)
 * @author wangyongcan
 * @Date 2016/12/21 09:41
 */
public class SimpleSelectSort extends SortBase {

    @Override
    public void sort(int[] array) {
        int minPosition;
        for(int i=0;i<array.length;i++) {
            minPosition = i;
            for(int j=i+1;j<array.length;j++) {
                if(array[j]<array[minPosition]) {
                    minPosition = j;
                }
            }
            swap(i,minPosition,array);
        }
    }

    public static SortBase instance = new SimpleSelectSort();
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

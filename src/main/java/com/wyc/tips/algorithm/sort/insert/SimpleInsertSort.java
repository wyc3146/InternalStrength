package com.wyc.tips.algorithm.sort.insert;/**
 * Created by wyc on 2016/12/22.
 */

import com.wyc.tips.algorithm.sort.SortBase;

import java.util.Arrays;

/**
 * @author wangyongcan
 * @Date 2016/12/22 21:50
 */
public class SimpleInsertSort extends SortBase {
    @Override
    public void sort(int[] array) {
        for(int i=1;i<array.length;i++) {
            for(int j=i;j>=1;j--) {
                if(array[j] < array[j-1]) {
                    swap(j,j-1,array);
                }
            }
        }
    }

    public static SortBase instance = new SimpleInsertSort();
    public static SortBase getInstance() {
        return instance;
    }

    public static void main(String args[]) {
        int array[] = instance.getUnSortArray(15);
        System.out.println(Arrays.toString(array));
        instance.sort(array);
        System.out.println(Arrays.toString(array));
    }

}

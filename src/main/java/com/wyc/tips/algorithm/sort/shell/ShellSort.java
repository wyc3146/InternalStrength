package com.wyc.tips.algorithm.sort.shell;

import com.wyc.tips.algorithm.sort.SortBase;

import java.util.Arrays;

/**
 * 希尔排序，时间复杂度最好O(n^1.3)、O(n)、O(n^2)
 * 不稳定排序算法
 * 插入排序的改进版
 * Created by Administrator on 2016/12/24 0024.
 */
public class ShellSort extends SortBase {
    @Override
    public void sort(int[] array) {
        for(int i=1;i>=1;i--) {
            shellSort(array,i);
        }
    }

    private void shellSort(int []array,int d) {
        for(int k=0;k<d;k++) {
            for(int i=k+d;i<array.length;i+=d) {
                for(int j=i;j>d-1;j-=d) {
                    if(array[j] < array[j-d]) {
                        swap(j,j-d,array);
                    }
                }
            }
        }

    }

    public static SortBase instance = new ShellSort();
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

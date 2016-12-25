package com.wyc.tips.algorithm.sort.radix;

import com.wyc.tips.algorithm.sort.SortBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/12/25 0025.
 */
public class RadixSort extends SortBase {
    @Override
    public void sort(int[] array) {
        int notZeroCount = array.length;
        int nowPosition = 1;
        while(notZeroCount > 0) {
            List<Integer>[] list = new List[10];
            for(int i=0;i<10;i++) {
                list[i] = new ArrayList();
            }
            for(int i=0;i<array.length;i++) {
                int value = array[i];
                list[(value / nowPosition) % 10].add(value);
            }
            int position = 0;
            for(int i=0;i<10;i++) {
                for(Integer integer : list[i]) {
                    array[position++] = integer;
                }
            }
            nowPosition *= 10;
            notZeroCount = array.length - list[0].size();
        }
    }

    public static SortBase instance = new RadixSort();
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

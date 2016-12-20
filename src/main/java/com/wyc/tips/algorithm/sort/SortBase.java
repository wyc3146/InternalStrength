package com.wyc.tips.algorithm.sort;/**
 * Created by wyc on 2016/12/20.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

/**
 * @author wangyongcan
 * @Date 2016/12/20 20:53
 */
public abstract class SortBase {
    public abstract void sort(int [] array);

    public int [] getUnSortArray(int length) {
        int [] array = new int[length];
        for(int i=0;i<array.length;i++) {
            array[i] = i;
        }
        Random random = new Random();
        for(int i=0;i<length;i++) {
            int randomInt = random.nextInt(length);
            int value = array[i];
            array[i] = array[randomInt];
            array[randomInt] = value;
        }
        return array;
    }

}

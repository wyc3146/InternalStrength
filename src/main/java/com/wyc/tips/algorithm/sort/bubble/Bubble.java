package com.wyc.tips.algorithm.sort.bubble;/**
 * Created by wyc on 2016/12/20.
 */

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.wyc.tips.algorithm.sort.SortBase;

import java.util.Arrays;

/**
 *
 * @author wangyongcan
 * @Date 2016/12/20 20:46
 */
public class Bubble extends SortBase {

    public void sort(int [] array) {
        for(int i=0;i<array.length;i++) {
            for(int j=1;j<array.length-i;j++) {
                int temp = array[j-1];
                if(array[j] < array[j-1]) {
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static SortBase instance = new Bubble();
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

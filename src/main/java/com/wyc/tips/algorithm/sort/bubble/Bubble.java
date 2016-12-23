package com.wyc.tips.algorithm.sort.bubble;/**
 * Created by wyc on 2016/12/20.
 */

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.wyc.tips.algorithm.sort.SortBase;

import java.util.Arrays;

/**
 * 冒泡排序，时间复杂度O(n²)，空间复杂度O(1)
 * 稳定的排序算法
 * 算法描述：将数组看做竖直的，最上面为位置0。每次从最底端开始，和上面的数值比较，小就交换，直到到达最顶端。
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

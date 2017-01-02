package com.wyc.tips.algorithm.sort.heap;/**
 * Created by wyc on 2016/12/22.
 */

import com.wyc.tips.algorithm.sort.SortBase;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 堆排序，时间复杂度最好O(nlog2(n))
 * 不稳定排序算法
 * 算法描述：先根据原始数组创建一个大顶堆。建立完毕后数组的第一个值肯定是最大的，和最后一个值交换，将(length-i)个数组重新排成大顶堆，直到最后一个数。
 * @author wangyongcan
 * @Date 2016/12/22 20:04
 */
public class HeapSort extends SortBase {
    @Override
    public void sort(int[] array) {
        toSmallHeap(array,0,array.length-1);
        for(int i=array.length-1;i>=1;i--) {
            swap(0,i,array);

            int position = 0;
            while((position*2)+1 < i) {
                int leftPosition = position * 2 + 1;
                int rightPosition = position * 2 + 2;
                int maxVal = array[position];
                int swapPosition = position;
                if(maxVal < array[leftPosition]) {
                    maxVal = array[leftPosition];
                    swapPosition = leftPosition;
                }
                if(rightPosition < i && maxVal < array[rightPosition]) {
                    swapPosition = rightPosition;
                }
                if(position != swapPosition) {
                    swap(position,swapPosition,array);
                    position = swapPosition;
                } else {
                    position = i;
                }
            }

        }
    }

    private void toSmallHeap(int[] array,int start,int end) {
        for(int i=start+1;i<=end;i++) {
            if(i==end-1) {
                int a = 2;
            }
            int position = i;
            while(position>0) {
                if(array[position] > array[(position-1)>>1]) {
                    swap(position,(position-1)>>1,array);
                }
                position = (position-1)>>1;
            }
        }
    }

    public static SortBase instance = new HeapSort();
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

package com.wyc.tips.algorithm.sort;/**
 * Created by wyc on 2016/12/20.
 */

import com.wyc.tips.algorithm.sort.bubble.Bubble;
import com.wyc.tips.algorithm.sort.heap.HeapSort;
import com.wyc.tips.algorithm.sort.insert.SimpleInsertSort;
import com.wyc.tips.algorithm.sort.merge.MergeSort;
import com.wyc.tips.algorithm.sort.quick.QuickSort;
import com.wyc.tips.algorithm.sort.radix.RadixSort;
import com.wyc.tips.algorithm.sort.shell.ShellSort;
import com.wyc.tips.algorithm.sort.simple_select.SimpleSelectSort;

import java.util.*;

/**
 * @author wangyongcan
 * @Date 2016/12/20 20:46
 */
public class SortMain {
    public static void main(String args[]) {
        System.out.println(aLotSortToVerificate(new RadixSort()));
    }

    private static boolean aLotSortToVerificate(SortBase sortBase) {
        for(int i=0;i<=5000;i++) {
            int []array = sortBase.getUnSortArray(i);
            sortBase.sort(array);
            for(int j=1;j<array.length;j++) {
                if(array[j] < array[j-1]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void timeTest() {
        Map<String,SortBase> sortUtilMap = new LinkedHashMap<String, SortBase>();
        sortUtilMap.put("quickSort",new QuickSort());
        sortUtilMap.put("simpleSelectSort",new SimpleSelectSort());
        sortUtilMap.put("bubble",new Bubble());
        sortUtilMap.put("heapSort",new HeapSort());

        int array[] = sortUtilMap.get("quickSort").getUnSortArray(50000);

        for(Map.Entry<String,SortBase> entry : sortUtilMap.entrySet()) {
            int _copy[] = new int[array.length];
            System.arraycopy(array,0,_copy,0,array.length);
            long time = System.currentTimeMillis();
            entry.getValue().sort(_copy);
            System.out.println(entry.getKey() + "耗时:" + (System.currentTimeMillis() - time) );
        }
    }

}

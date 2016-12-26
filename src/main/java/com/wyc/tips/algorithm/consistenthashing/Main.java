package com.wyc.tips.algorithm.consistenthashing;/**
 * Created by wyc on 2016/12/26.
 */

import com.wyc.tips.algorithm.consistenthashing.impl.ConsistenHashingDistribute;
import com.wyc.tips.algorithm.consistenthashing.impl.Machine;

import java.util.Arrays;
import java.util.Map;

/**
 * @author wangyongcan
 * @Date 2016/12/26 20:42
 */
public class Main {
    public static void main(String args[]) {
        ConsistenHashingDistribute<Machine> distribute = new ConsistenHashingDistribute<Machine>(100000);
        distribute.add(new Machine("192.168.10.46",11211));
        distribute.add(new Machine("192.168.20.46",11211));
        distribute.add(new Machine("192.168.30.46",11211));
        distribute.add(new Machine("192.168.40.46",11211));
        distribute.add(new Machine("192.168.50.46",11211));

        int []dis = new int[5];
        for(int i=0;i<20000;i++) {
            dis[distribute.distribute("down_"+i).getId()]++;
        }

        System.out.println(Arrays.toString(dis));

    }
}

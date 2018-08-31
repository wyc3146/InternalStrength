package com.wyc.tips.algorithm.consistenthashing.impl;/**
 * Created by wyc on 2016/12/26.
 */

import com.wyc.tips.algorithm.consistenthashing.IDistribute;
import com.wyc.tips.algorithm.consistenthashing.IMachine;

import java.util.*;
import java.util.zip.CRC32;

/**
 * 一致性hash节点分配类，分配算法实现
 * @author wangyongcan
 * @Date 2016/12/26 20:54
 */
public class ConsistenHashingDistribute<T extends IMachine> implements IDistribute<T> {
    private List<T> machineList = new ArrayList<T>();
    public TreeMap<Long,T> machineTreeMap = new TreeMap<Long, T>();
    private Map<T,List<Long>> imachineHashedKeyMap = new HashMap<T, List<Long>>();

    private int virtualNodeNum;
    public ConsistenHashingDistribute(int virtualNodeNum) {
        this.virtualNodeNum = virtualNodeNum;
    }

    @Override
    public synchronized void add(T machine) {
        machineList.add(machine);

        List<Long> hashedKeyList = new ArrayList<Long>();
        for(int i=0;i<virtualNodeNum;i++) {
            Long hashedKey = hashing(machine.getIp()+":"+machine.getPort()+":"+i);
            machineTreeMap.put(hashedKey,machine);
            hashedKeyList.add(hashedKey);
        }
        imachineHashedKeyMap.put(machine, hashedKeyList);
    }

    @Override
    public synchronized void remove(T machine) {
        machineList.remove(machine);
        List<Long> hashedKeyList = imachineHashedKeyMap.remove(machine);
        for(Long hashedKey : hashedKeyList) {
            machineTreeMap.remove(hashedKey);
        }
    }

    @Override
    public T distribute(String key) {
        Long hashedKey = hashing(key);
        SortedMap<Long,T> sortedMap = machineTreeMap.tailMap(hashedKey);
        Long firstKey = sortedMap.isEmpty()?machineTreeMap.firstKey() : sortedMap.firstKey();
        return machineTreeMap.get(firstKey);
    }

    private Long hashing(String key) {
        CRC32 crc = new CRC32();
        crc.update(key.getBytes());
        long value = crc.getValue();
        value = ((value & 0xFFFF0000) >> 16) + ((value & 0xFFFF) << 16);
        value = ((value & 0xFFFF0000) >> 25) + ((value & 0xFFFF) << (32-25));
        return value;
    }

}

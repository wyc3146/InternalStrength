package com.wyc.tips.algorithm.consistenthashing;

/**
 * 节点接口，每个对象对应一个节点
 * Created by wyc on 2016/12/26.
 */
public interface IMachine {
    String getIp();
    int getPort();
    int getId();
    String getName();

    void set(String key,Object value);
    Object get(String key);

}

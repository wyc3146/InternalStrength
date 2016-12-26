package com.wyc.tips.algorithm.consistenthashing;

/**
 * 分发接口，负责增加节点和根据对应key分配节点
 * Created by wyc on 2016/12/26.
 */
public interface IDistribute<T extends IMachine> {
    void add(T machine);
    void remove(T machine);
    T distribute(String key);
}

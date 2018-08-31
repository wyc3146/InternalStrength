package com.wyc.tips.algorithm.tree;

import java.util.Collection;

/**
 * 树形结构接口
 * @author Wangyc 2018-08-30 17:25
 */
public interface Tree<K, V> {
    /**
     * 添加元素
     * @param key
     * @param value
     */
    void add(K key, V value);

    /**
     * 查找元素
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 判断元素是否存在
     * @param key
     * @return
     */
    boolean contain(K key);

    /**
     * 移除元素
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * 顺序遍历整棵树，返回一个顺序的Collection对象
     * @return
     */
    Collection<TreeEntry<K, V>> list();

}

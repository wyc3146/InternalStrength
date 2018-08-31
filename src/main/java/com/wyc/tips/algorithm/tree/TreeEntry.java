package com.wyc.tips.algorithm.tree;

/**
 * @author Wangyc 2018-08-29 20:31
 */
public interface TreeEntry<K, V> {
    K getKey();

    V getValue();

    V setValue(V value);
}

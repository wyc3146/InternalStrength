package com.wyc.tips.algorithm.tree.binaryTree;/**
 * Created by wyc on 2016/12/29.
 */

/**
 * @author wangyongcan
 * @Date 2016/12/29 09:31
 */
public class AVLTree<K,V> extends BinarySearchTree<K,V> {
    @Override
    public void addNode(K k, V v) {
        super.addNode(k, v);
        balance();
    }

    private void balance() {

    }

}

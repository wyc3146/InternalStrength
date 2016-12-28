package com.wyc.tips.algorithm.tree.binaryTree;/**
 * Created by wyc on 2016/12/28.
 */

import java.util.Comparator;
import java.util.Map;

/**
 * @author wangyongcan
 * @Date 2016/12/28 18:20
 */
public class BinarySearchTree<K,V> extends BinaryTree<K,V> {
    private Comparator<K> comparator;

    public void addNode(K k,V v) {
        System.out.println("add " + k);
        if(getRoot() == null) {
            buildRoot(k,v);
        } else {
            BinaryTreeNode<K, V> node = getRoot();
            for (; ; ) {
                int compareResult = compare(k,node.getKey());
                if(compareResult == 0) {
                    node.setValue(v);
                    return ;
                } else if (compareResult > 0) {
                    BinaryTreeNode<K, V> nodeTemp = node.getRightChild();
                    if (nodeTemp == null) {
                        node.setRightChild(new BinaryTreeNode(k, v));
                        return;
                    }
                    node = nodeTemp;
                } else {
                    BinaryTreeNode<K, V> nodeTemp = node.getLeftChild();
                    if (nodeTemp == null) {
                        node.setLeftChild(new BinaryTreeNode(k, v));
                        return;
                    }
                    node = nodeTemp;
                }
            }
        }
    }

    private int compare(K key, K k) {
        if(comparator != null) {
            return comparator.compare(key,k);
        }
        Comparable<K> key1 = (Comparable) key;
        return key1.compareTo(k);
    }

    public V searchValue(K k) {
        BinaryTreeNode<K,V> node = getRoot();
        while(node != null) {
            int compareValue = compare(k,node.getKey());
            if(compareValue == 0) {
                return node.getValue();
            } else if(compareValue > 0) {
                node = node.getRightChild();
            } else {
                node = node.getLeftChild();
            }
        }
        return null;
    }

    public BinarySearchTree buildTree(Map<K,V> map) {
        for(Map.Entry<K,V> entry : map.entrySet()) {
            addNode(entry.getKey(),entry.getValue());
        }
        return this;
    }

    public void setComparator(Comparator<K> comparator) {
        this.comparator = comparator;
    }

}

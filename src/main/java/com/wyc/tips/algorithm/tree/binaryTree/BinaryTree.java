package com.wyc.tips.algorithm.tree.binaryTree;/**
 * Created by wyc on 2016/12/28.
 */

/**
 * @author wangyongcan
 * @Date 2016/12/28 18:12
 */
public class BinaryTree<K,V> {
    private BinaryTreeNode<K,V> root;

    public BinaryTreeNode<K, V> getRoot() {
        return root;
    }

    public void buildRoot(K k,V v) {
        root = new BinaryTreeNode<K, V>(k,v);
    }

}

class BinaryTreeNode<K,V> {
    private K key;
    private V value;
    private BinaryTreeNode parent;
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;

    public BinaryTreeNode() {}

    public BinaryTreeNode(K k,V v) {
        this.key = k;
        this.value = v;
    }

    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    public BinaryTreeNode getParent() {
        return parent;
    }
    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }
    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }
    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }
    public BinaryTreeNode getRightChild() {
        return rightChild;
    }
    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }
}

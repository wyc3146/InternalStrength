/*
package com.wyc.tips.algorithm.tree.binary;

*/
/**
 * Created by cc on 2017/1/2.
 *//*

public class RedBlackTree<K,V> extends BinarySearchTree<K,V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private RedBlackTreeNode<K,V> root;
    private int size;

    public V remove(K key) {
        RedBlackTreeNode<K,V> node = getNode(key);
        if(node == null) {
            return null;
        }
        V oldValue = node.getValue();
        deleteNode(node);
        return oldValue;
    }

    private void deleteNode(RedBlackTreeNode<K, V> node) {

    }

    private RedBlackTreeNode<K, V> getNode(K key) {
        if(root == null) return null;
        RedBlackTreeNode<K,V> nowNode = root;
        do {
            int compareValue = compare(key,nowNode.getKey());
            if(compareValue > 0) {
                nowNode = nowNode.getRightChild();
            } else if(compareValue < 0) {
                nowNode = nowNode.getLeftChild();
            } else {
                return nowNode;
            }
        } while (nowNode != null);
        return null;
    }

    public V put(K key,V value) {
        RedBlackTreeNode<K,V> t = root;
        if(t == null) {
            root = new RedBlackTreeNode<K, V>(key,value,null);
            size = 1;
            return null;
        }
        int compareValue;
        RedBlackTreeNode<K,V> parent;
        do {
            parent = t;
            compareValue = compare(key,parent.getKey());
            if(compareValue > 0) {
                t = t.getRightChild();
            } else if (compareValue < 0) {
                t = t.getLeftChild();
            } else {
                return parent.setValue(value);
            }
        } while (t != null);

        RedBlackTreeNode<K,V> e = new RedBlackTreeNode<K, V>(key,value,parent);
        if(compareValue < 0) {
            parent.setLeftChild(e);
        } else {
            parent.setRightChild(e);
        }

        fixAfterInsertion(e);
        size ++;

        return null;
    }

    private void fixAfterInsertion(RedBlackTreeNode<K, V> x) {
        x.color = RED;
        while(x != root && x.getParent().color == RED) {
            // x在它父节点的父节点的左子树上
            */
/*if(parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                // x的父节点的父节点的右孩子(叔节点)
                RedBlackTreeNode<K,V> y = rightOf(parentOf(parentOf(x)));
                // 叔节点是红色的
                if(colorOf(y) == RED) {
                    setColor(parentOf(x),BLACK);
                    setColor(y,BLACK);
                    setColor(parentOf(parentOf(x)),RED);
                    x = parentOf(parentOf(x));
                } else {
                    if(x == rightOf(parentOf(x))) {
                        // x是它父节点的右儿子
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x),BLACK);
                    setColor(parentOf(parentOf(x)),RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                RedBlackTreeNode<K,V> uncleNode = leftOf(parentOf(parentOf(x)));
                if(colorOf(uncleNode) == RED) {
                    setColor(parentOf(x),BLACK);
                    setColor(uncleNode,BLACK);
                    setColor(parentOf(parentOf(x)),RED);
                    x = parentOf(parentOf(x));
                } else {
                    if(x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x),BLACK);
                    setColor(parentOf(parentOf(x)),RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }*//*

        }
        root.color = BLACK;
    }

    private void rotateRight(RedBlackTreeNode<K, V> p) {
        if (p != null) {
            RedBlackTreeNode<K,V> l = p.getLeftChild();
            p.setLeftChild(l.getRightChild());
            if (l.getRightChild() != null)
                l.getRightChild().setParent(p);
            l.setParent(p.getParent());
            if (p.getParent() == null)
                root = l;
            else if (p.getParent().getRightChild() == p)
                p.getParent().setRightChild(l);
            else
                p.getParent().setLeftChild(l);
            l.setRightChild(p);
            p.setParent(l);
        }
    }

    private void rotateLeft(RedBlackTreeNode<K, V> x) {
        RedBlackTreeNode<K,V> parent = x.getParent();
        if(parent.getLeftChild() == x) {
            parent.setLeftChild(x.getRightChild());
        } else {
            parent.setRightChild(x.getRightChild());
        }
        RedBlackTreeNode<K,V> right = x.getRightChild();
        x.setRightChild(right.getLeftChild());
        right.setLeftChild(x);
    }

    private void setColor(RedBlackTreeNode<K, V> x, boolean color) {
        x.color = color;
    }

    private boolean colorOf(RedBlackTreeNode<K, V> y) {
        return y.color;
    }

    private RedBlackTreeNode<K, V> rightOf(RedBlackTreeNode<K, V> x) {
        if(x != null) {
            return x.getRightChild();
        }
        return null;
    }

    private RedBlackTreeNode<K, V> leftOf(RedBlackTreeNode<K, V> x) {
        if(x != null) {
            return x.getLeftChild();
        }
        return null;
    }

    private RedBlackTreeNode<K, V> parentOf(RedBlackTreeNode<K, V> x) {
        if(x != null) {
            return x.getParent();
        }
        return null;
    }

    class RedBlackTreeNode<K,V> extends BinaryTreeNode<K,V> {
        private boolean color = BLACK;

        public RedBlackTreeNode(K k,V v,RedBlackTreeNode<K,V> parent) {
            super(k,v);
            setParent(parent);
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public RedBlackTreeNode<K,V> getLeftChild() {
            return (RedBlackTreeNode<K,V>) super.getLeftChild();
        }

        public RedBlackTreeNode<K,V> getRightChild() {
            return (RedBlackTreeNode<K,V>) super.getRightChild();
        }

        public RedBlackTreeNode<K,V> getParent() {
            return (RedBlackTreeNode<K,V>)super.getParent();
        }

    }

}
*/

package com.wyc.tips.algorithm.tree.binary;

import com.wyc.tips.algorithm.tree.Tree;
import com.wyc.tips.algorithm.tree.TreeEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * 二叉排序树实现，不平衡
 * @author Wangyc 2018-08-29 19:57
 */
public class BinarySearchTree<K, V> implements Tree<K, V> {
    private TreeNode<K, V> root;
    private Comparator<K> comparator;

    public BinarySearchTree() {}

    public BinarySearchTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    /**
     * 从根结点开始依次向下遍历，待插入节点和当前比较，分三种情况
     * 1、相等，直接把该节点值设置成新的值
     * 2、待插入节点大于当前节点，找当前节点的右节点，没有则设置待插入节点为当前节点的右节点，有则设置当前节点为右节点继续遍历
     * 3、待插入节点小于当前节点，找当前节点的左节点，没有则设置待插入节点为当前节点的左节点，有则设置当前节点为左节点继续遍历
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        if(root == null) root = new TreeNode<>(key, value, null);
        TreeNode<K, V> node = root;
        for(;;) {
            int compareResult = compare(key, node.key);
            if(compareResult == 0) {
                // 发现key大小和待插入数据一样的node，直接修改这个node的值
                node.value = value;
                return ;
            } else if(compareResult > 0) {
                // 待插入的元素应该放在当前元素右子树上
                if(node.right == null) {
                    node.right = new TreeNode<>(key, value, node);
                    return ;
                }
                node = node.right;
            } else {
                // 待插入的元素应该放在当前元素左子树上
                if(node.left == null) {
                    node.left = new TreeNode<>(key, value, node);
                    return ;
                }
                node = node.left;
            }
        }
    }

    @Override
    public V get(K key) {
        TreeNode<K, V> node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public boolean contain(K key) {
        return getNode(key) != null;
    }

    /**
     * 分三种情况
     * 1、被删除节点是一个叶子节点，直接删除
     * 2、被删除节点有且只有一个叶子节点，删除该节点将他的叶子节点替换到该节点的位置
     * 3、被删除节点有两个叶子节点，找到这个节点的前驱节点（比该节点小的所有节点中值最大的节点），
     *    用前驱节点替换该节点，因为前驱节点肯定没有右儿子，所以前驱节点如果有左儿子直接将左儿子替换到前驱节点原来的位置
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        TreeNode<K, V> node = getNode(key);
        V removeValue = node.value;
        if(node == null) return null;
        // 情况1：被删除节点没有子节点
        if (node.left == null && node.right == null) {
            if(node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
        // 情况2：被删除节点只有左节点或者右节点，那么直接删除该节点将子节点提到当前节点的位置
        else if(node.left == null || node.right == null) {
            // 被删除元素不为空的子节点
            TreeNode<K, V> childNode;
            if(node.left != null) {
                childNode = node.left;
            } else {
                childNode = node.right;
            }
            if(node.parent.left == node) {
                node.parent.left = childNode;
            } else {
                node.parent.right = childNode;
            }
        }
        // 情况3：被删除的节点有左右节点，那么找到该节点的前驱节点替换之
        else {
            TreeNode<K, V> preNode = getPreNode(node);
            // 这里也有两种情况，前驱节点可能有左子树也可能没有左子树
            if(preNode.left == null) {
                // 情况3.1：前驱节点没有左子树，直接替换被删除节点
                node.key = preNode.key;
                node.value = preNode.value;
                if (preNode.parent.left == preNode) {
                    preNode.parent.left = null;
                } else {
                    preNode.parent.right = null;
                }
            } else {
                // 情况3.2：前驱节点有左子树，用前驱节点替换被删除节点，用前驱节点的左节点替换前驱节点
                node.key = preNode.key;
                node.value = preNode.value;
                if (preNode.parent.left == preNode) {
                    preNode.parent.left = preNode.left;
                } else {
                    preNode.parent.right = preNode.left;
                }
            }
        }
        return removeValue;
    }

    /**
     * 找到对应节点的前驱节点
     * @param node
     * @return
     */
    private TreeNode<K, V> getPreNode(TreeNode<K, V> node) {
        // 前驱节点肯定在这个节点的左子树上
        if(node == null || node.left == null) return null;
        TreeNode<K, V> resultNode = node.left;
        // 前驱节点就是左子树上一直往右找的最终一个节点
        while(resultNode.right != null) {
            resultNode = resultNode.right;
        }
        return resultNode;
    }

    private TreeNode<K, V> getNode(K key) {
        TreeNode<K, V> node = root;
        for (;;) {
            if(node == null) return null;
            int compareResult = compare(key, node.key);
            if(compareResult == 0) {
                return node;
            } else if (compareResult > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
    }

    public Collection<TreeEntry<K, V>> list() {
        Collection<TreeEntry<K, V>> collection = new ArrayList<>();
        listNode(root, collection);
        return collection;
    }

    private void listNode(TreeNode<K, V> node, Collection<TreeEntry<K, V>> collection) {
        if(node == null) return ;
        listNode(node.left, collection);
        collection.add(new TreeEntry<K, V>() {
            @Override
            public K getKey() {
                return node.key;
            }

            @Override
            public V getValue() {
                return node.value;
            }

            @Override
            public V setValue(V value) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        });
        listNode(node.right, collection);
    }

    /**
     * 比较两个key的大小，如果有的话默认用Comparator
     * @param key1
     * @param key2
     * @return
     */
    int compare(K key1, K key2) {
        if(comparator != null) {
            return comparator.compare(key1,key2);
        }
        return ((Comparable) key1).compareTo(key2);
    }

    private static class TreeNode<K, V> {
        K key;
        V value;
        TreeNode<K, V> parent;
        TreeNode<K, V> left;
        TreeNode<K, V> right;

        TreeNode(K key, V value, TreeNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
    }
}

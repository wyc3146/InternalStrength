package com.wyc.tips.algorithm.tree.binary;

import com.wyc.tips.algorithm.tree.Tree;
import com.wyc.tips.algorithm.tree.TreeEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * 平衡的二叉排序树
 * @param <K>
 * @param <V>
 */
public class AVLTree<K,V> implements Tree<K, V> {
    private TreeNode<K, V> root;
    private Comparator<K> comparator;

    public AVLTree() {}

    public AVLTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(K key, V value) {
        if(root == null) {
            root = new TreeNode<>(key, value, null);
            return ;
        }
        root = add(new TreeNode<>(key, value, null), root);
    }

    private TreeNode<K, V> add(TreeNode<K, V> waitAdd, TreeNode<K, V> parent) {
        int compareResult = compare(waitAdd.key, parent.key);
        if(compareResult == 0) {
            parent.key(waitAdd.key);
            parent.value(waitAdd.value);
            return parent;
        } else if(compareResult < 0) {
            if(parent.left == null) {
                parent.left(waitAdd);
                return parent;
            }
            parent.left(add(waitAdd, parent.left));
            // 这棵树不平衡了
            if(Math.abs(height(parent.left) - height(parent.right)) >= 2) {
                if(compare(waitAdd.key, parent.left.key) < 0) {
                    // 新节点被加到了左节点的左子树，说明是左左的情况
                    return leftLeftRotation(parent);
                } else {
                    // 新节点被加到了左节点的右子树，说明是左右的情况
                    return leftRightRotation(parent);
                }
            }
        } else {
            if(parent.right == null) {
                parent.right(waitAdd);
                return parent;
            }
            parent.right(add(waitAdd, parent.right));
            if(Math.abs(height(parent.left) - height(parent.right)) >= 2) {
                if(compare(waitAdd.key, parent.right.key) < 0) {
                    // 新节点被加到了右节点的左子树，说明是右左的情况
                    return rightLeftRotation(parent);
                } else {
                    // 新节点被加到了右节点的右子树，说明是右右的情况
                    return rightRightRotation(parent);
                }
            }
        }
        return parent;
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

    @Override
    public V remove(K key) {
        TreeNode<K, V> node = getNode(key);
        if(node == null) return null;

        V value = node.value;
        root = remove(node, root);
        return value;
    }

    private TreeNode<K, V> remove(TreeNode<K, V> waitDelete, TreeNode<K, V> parent) {
        int compareResult = compare(waitDelete.key, parent.key);
        if(compareResult == 0) {
            // 删除要分三种情况，1、叶子节点，直接删除 2、只有一个儿子节点，删除并用儿子节点替代 3、找到前驱节点，替代他
            if (parent.left == null && parent.right == null) {
                if(parent.parent == null) {
                    root = null;
                } else if(parent.parent.left == parent) {
                    parent.parent.left(null);
                } else {
                    parent.parent.right(null);
                }
            } else if(parent.left != null && parent.right != null) {
                TreeNode<K, V> preNode = preNode(parent);
                // 这里分两种情况1、前驱节点有左子树 2、前驱节点没有左子树
                if(preNode.left == null) {
                    parent.key = preNode.key;
                    parent.value = preNode.value;
                    if(preNode.parent.left == preNode) {
                        preNode.parent.left(null);
                    } else {
                        preNode.parent.left(null);
                    }
                } else {
                    parent.key = preNode.key;
                    parent.value = preNode.value;
                    if(preNode.parent.left == preNode) {
                        preNode.parent.left(preNode.left);
                    } else {
                        preNode.parent.left(preNode.left);
                    }
                }
            } else {
                TreeNode<K, V> childNode = parent.left != null ? parent.left : parent.right;
                if(parent.parent == null) {
                    root = childNode;
                } else if (parent.parent.left == parent) {
                    parent.parent.left(childNode);
                } else {
                    parent.parent.right(childNode);
                }
            }
        } else if(compareResult > 0) {
            parent.right(remove(waitDelete, parent.right));
            if(Math.abs(height(parent.left) - height(parent.right)) >= 2) {
                // 右边节点被删除，只可能是左子树失衡
                if(height(parent.left.left) > height(parent.left.right)) {
                    // 左节点的左子树比右子树高
                    return leftLeftRotation(parent);
                } else {
                    // 左节点的右子树比左子树高
                    return leftRightRotation(parent);
                }
            }
        } else {
            parent.left(remove(waitDelete, parent.left));
            if(Math.abs(height(parent.left) - height(parent.right)) >= 2) {
                // 左边节点被删除，只可能是右子树失衡
                if(height(parent.right.left) > height(parent.right.right)) {
                    // 右节点的左子树比右子树高
                    return rightLeftRotation(parent);
                } else {
                    // 右节点的右子树比左子树高
                    return rightRightRotation(parent);
                }
            }
        }
        return parent;
    }

    @Override
    public Collection<TreeEntry<K, V>> list() {
        Collection<TreeEntry<K, V>> collection = new ArrayList<>();
        listNode(root, collection);
        return collection;
    }

    public TreeNode<K, V> getRoot() {
        return root;
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
     * 找到对应节点的前驱节点
     * @param node
     * @return
     */
    private TreeNode<K, V> preNode(TreeNode<K, V> node) {
        // 前驱节点肯定在这个节点的左子树上
        if(node == null || node.left == null) return null;
        TreeNode<K, V> resultNode = node.left;
        // 前驱节点就是左子树上一直往右找的最终一个节点
        while(resultNode.right != null) {
            resultNode = resultNode.right;
        }
        return resultNode;
    }

    private int height(TreeNode<K, V> node) {
        return height(node, 0);
    }

    private int height(TreeNode<K, V> node, int height) {
        if(node == null) {
            return height - 1;
        }
        if(node.left() == null && node.right() == null) {
            return height;
        }
        int leftHeight = height, rightHeight = height;
        if(node.left() != null) {
            leftHeight = height(node.left, height + 1);
        }
        if(node.right() != null) {
            rightHeight = height(node.right, height + 1);
        }
        return Math.max(leftHeight, rightHeight);
    }

    /**
     * 左左旋转
     * 节点右节点是一个叶子结点，左节点的左节点有子节点，导致以这个节点为根的子树不平衡
     * 通过左左旋转将节点的左节点提为根结点，达到平衡的目的
     *           8                          4
     *      4        9     ---->        2       8
     *   2    6                     1         6   9
     * 1
     * @param node
     * @return 旋转后的根结点
     */
    private TreeNode<K, V> leftLeftRotation(TreeNode<K, V> node) {
        TreeNode<K, V> finalRoot = node.left();
        finalRoot.parent(null);
        // 根结点的左节点设置为左节点的右节点（8的左节点设置成6）
        node.left(finalRoot.right());
        // 左节点的右节点设置为根结点（4的右节点设置为8）
        finalRoot.right(node);

        return finalRoot;
    }

    /**
     * 右右旋转
     * 节点的左节点是一个叶子节点，右节点的右节点有子节点，导致以这个节点为根的子树不平衡
     *           3                          6
     *      1        6     ---->        3      8
     *             4   8             1    4       9
     *                   9
     * @param node
     * @return 旋转后的根结点
     */
    private TreeNode<K, V> rightRightRotation(TreeNode<K, V> node) {
        TreeNode<K, V> finalRoot = node.right();
        finalRoot.parent(null);
        // 根结点的右节点设置为他右节点的左节点（3的右节点设置为4）
        node.right(finalRoot.left());
        // 右节点的左节点设置为根结点（6的左节点设置为3）
        finalRoot.left(node);

        return finalRoot;
    }

    /**
     * 左右旋转
     * 给定节点的右节点是一个叶子节点，左节点的右节点不是叶子节点，导致以这个节点为根的树不平衡。
     * 这种情况需要旋转两次，先以左节点为根做右右旋转，旋转完成后整棵树变成左左情况，再做一次左左旋转
     *           8                         8                         6
     *      4        9     ---->        6     9     ---->        4      8
     *   2     6                     4                         2   5      9
     *       5                     2  5
     * @param node
     * @return 旋转后的根结点
     */
    private TreeNode<K, V> leftRightRotation(TreeNode<K, V> node) {
        node.left(rightRightRotation(node.left()));
        TreeNode<K, V> resultNode = leftLeftRotation(node);
        return resultNode;
    }

    /**
     * 右左旋转
     * 给定节点的左节点是一个叶子节点，右节点的左节点不是叶子节点，导致以这个节点为根的树不平衡
     * 这种情况需要旋转两次，先以右节点为根进行左左旋转，再以给定节点为根进行右右旋转
     *           3                       3                               4
     *      1        6     ---->     1       4        ---->          3       6
     *             4   8                        6                  1       5   8
     *              5                         5   8
     * @param node
     * @return 旋转后的根结点
     */
    private TreeNode<K, V> rightLeftRotation(TreeNode<K, V> node) {
        node.right(leftLeftRotation(node.right()));
        TreeNode<K, V> resultNode = rightRightRotation(node);
        return resultNode;
    }

    public static void main(String[] args) {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        tree.add(8, 8);
        tree.add(4, 4);
        tree.add(9, 9);
        tree.add(2, 2);
        tree.add(6, 6);

        System.out.println();
        tree.add(1, 1);
        System.out.println();
    }

    private static void testRightLeftRotation() {
        TreeNode<Integer, Integer> treeRoot = new TreeNode<>(3, 3, null);
        addLeft(treeRoot, new TreeNode<>(1, 1, null));
        addRight(treeRoot, new TreeNode<>(6, 6, null));
        addLeft(treeRoot.right(), new TreeNode<>(4, 4, null));
        addRight(treeRoot.right().left(), new TreeNode<>(5, 5 ,null));
        addRight(treeRoot.right(), new TreeNode<>(8, 8, null));

        AVLTree<Integer, Integer> tree = new AVLTree<>();
        TreeNode<Integer, Integer> resultNode = tree.rightLeftRotation(treeRoot);
        System.out.println();
    }

    private static void testLeftRightRotation() {
        TreeNode<Integer, Integer> treeRoot = new TreeNode<>(8, 8, null);
        addLeft(treeRoot, new TreeNode<>(4, 4, null));
        addLeft(treeRoot.left(), new TreeNode<>(2, 2, null));
        addRight(treeRoot.left(), new TreeNode<>(6, 6, null));
        addLeft(treeRoot.left().right(), new TreeNode<>(5, 5, null));
        addRight(treeRoot, new TreeNode<>(9, 9, null));

        AVLTree<Integer, Integer> tree = new AVLTree<>();
        TreeNode<Integer, Integer> resultNode = tree.leftRightRotation(treeRoot);
        System.out.println();
    }

    private static void testRightRightRotation() {
        TreeNode<Integer, Integer> treeRoot = new TreeNode<>(3, 3, null);
        addLeft(treeRoot, new TreeNode<>(1, 1, null));
        addRight(treeRoot, new TreeNode<>(6, 6, null));
        addLeft(treeRoot.right, new TreeNode<>(4, 4, null));
        addRight(treeRoot.right, new TreeNode<>(8, 8, null));
        addRight(treeRoot.right.right, new TreeNode<>(9, 9, null));

        AVLTree<Integer, Integer> tree = new AVLTree<>();
        TreeNode<Integer, Integer> resultNode = tree.rightRightRotation(treeRoot);
        System.out.println();
    }

    private static void testLeftLeftRotation() {
        TreeNode<Integer, Integer> treeRoot = new TreeNode<>(8, 8, null);
        addLeft(treeRoot, new TreeNode<>(4, 4, null));
        addRight(treeRoot, new TreeNode<>(9, 9, null));
        addLeft(treeRoot.left, new TreeNode<>(2,2, null));
        addRight(treeRoot.left, new TreeNode<>(6, 6, null));
        addLeft(treeRoot.left.left, new TreeNode<>(1, 1, null));

        AVLTree<Integer, Integer> tree = new AVLTree<>();
        TreeNode<Integer, Integer> resultNode = tree.leftLeftRotation(treeRoot);
        System.out.println();
    }

    private static void addLeft(TreeNode<Integer, Integer> rootNode, TreeNode<Integer, Integer> leftNode) {
        rootNode.left = leftNode;
        leftNode.parent = rootNode;
    }

    private static void addRight(TreeNode<Integer, Integer> rootNode, TreeNode<Integer, Integer> rightNode) {
        rootNode.right = rightNode;
        rightNode.parent = rootNode;
    }

    TreeNode<K, V> getNode(K key) {
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

    public static class TreeNode<K, V> {
        K key;
        V value;
        TreeNode<K, V> parent;
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        int height;

        TreeNode(K key, V value, TreeNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        void left(TreeNode<K, V> left) {
            this.left = left;
            if(left != null)
                left.parent = this;
        }

        void right(TreeNode<K, V> right) {
            this.right = right;
            if(right != null)
                right.parent = this;
        }

        void parent(TreeNode<K, V> parent) {
            this.parent = parent;
        }

        TreeNode<K, V> left() {
            return left;
        }

        TreeNode<K, V> right() {
            return right;
        }

        TreeNode<K, V> parent() {
            return parent;
        }

        int height() {
            return height;
        }

        K key() {
            return key;
        }

        V value() {
            return value;
        }

        void key(K key) {
            this.key = key;
        }

        void value(V value) {
            this.value = value;
        }
    }
}

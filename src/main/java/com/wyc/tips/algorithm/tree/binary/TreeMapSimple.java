package com.wyc.tips.algorithm.tree.binary;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by cc on 2017/1/2.
 */
public class TreeMapSimple<K,V> {
    /**
     * The comparator used to maintain order in this tree map, or
     * null if it uses the natural ordering of its keys.
     *
     * @serial
     */
    private final Comparator<? super K> comparator;

    private transient Entry<K,V> root;

    private final static boolean RED = false;
    private final static boolean BLACK = true;

    /**
     * The number of entries in the tree
     */
    private transient int size = 0;

    public TreeMapSimple() {
        comparator = null;
    }

    public TreeMapSimple(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    public V put(K key, V value) {
        Entry<K,V> t = root;
        if (t == null) {
            root = new Entry<K,V>(key, value, null);
            size = 1;
            return null;
        }
        int cmp;
        Entry<K,V> parent;
        do {
            parent = t;
            cmp = compare(key, t.key);
            if (cmp < 0)
                t = t.left;
            else if (cmp > 0)
                t = t.right;
            else
                return t.setValue(value);
        } while (t != null);

        Entry<K,V> e = new Entry<K,V>(key, value, parent);
        if (cmp < 0)
            parent.left = e;
        else
            parent.right = e;
        fixAfterInsertion(e);
        size++;
        return null;
    }

    public V remove(K key) {
        Entry<K,V> p = getEntry(key);
        if (p == null)
            return null;

        V oldValue = p.value;
        deleteEntry(p);
        return oldValue;
    }

    private void deleteEntry(Entry<K, V> p) {
        size--;

        // If strictly internal, copy successor's element to p and then make p
        // point to successor.
        if (p.left != null && p.right != null) {
            Entry<K,V> s = successor(p);
            p.key = s.key;
            p.value = s.value;
            p = s;
        } // p has 2 children

        // Start fixup at replacement node, if it exists.
        Entry<K,V> replacement = (p.left != null ? p.left : p.right);

        if (replacement != null) {
            // Link replacement to parent
            replacement.parent = p.parent;
            if (p.parent == null)
                root = replacement;
            else if (p == p.parent.left)
                p.parent.left  = replacement;
            else
                p.parent.right = replacement;

            // Null out links so they are OK to use by fixAfterDeletion.
            p.left = p.right = p.parent = null;

            // Fix replacement
            if (p.color == BLACK)
                fixAfterDeletion(replacement);
        } else if (p.parent == null) { // return if we are the only node.
            root = null;
        } else { //  No children. Use self as phantom replacement and unlink.
            if (p.color == BLACK)
                fixAfterDeletion(p);

            if (p.parent != null) {
                if (p == p.parent.left)
                    p.parent.left = null;
                else if (p == p.parent.right)
                    p.parent.right = null;
                p.parent = null;
            }
        }
    }

    /**
     * Returns the successor of the specified Entry, or null if no such.
     */
    static <K,V> Entry<K,V> successor(Entry<K,V> t) {
        if (t == null)
            return null;
        else if (t.right != null) {
            // 右子树不为空，那么找到右子树上key最小的一个节点返回
            Entry<K,V> p = t.right;
            while (p.left != null)
                p = p.left;
            return p;
        } else {
            // 右子树为空，
            Entry<K,V> p = t.parent;
            Entry<K,V> now = t;
            while (p != null && now == p.right) {
                now = p;
                p = p.parent;
            }
            return p;
        }
    }

    /** From CLR */
    private void fixAfterDeletion(Entry<K,V> x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) {
                Entry<K,V> sib = rightOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }

                if (colorOf(leftOf(sib))  == BLACK &&
                        colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else { // symmetric
                Entry<K,V> sib = leftOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colorOf(rightOf(sib)) == BLACK &&
                        colorOf(leftOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }

        setColor(x, BLACK);
    }

    private void fixAfterInsertion(Entry<K, V> x) {
        x.color = RED;

        // x不是跟节点或x的父节点颜色是红色，那只需把x的颜色改为黑色就行
        while (x != null && x != root && x.parent.color == RED) {
            // 当前节点的父节点是当前节点的爷节点的左孩子
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                // 当前节点的叔节点
                Entry<K, V> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    // 情况一，叔节点是红色，则将父节点和叔节点设置为黑色、爷节点设为红色，x指向爷节点
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        // 情况二，叔节点是黑色、x是它父节点的右孩子，则对x对父节点进行左旋转
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    // 情况三，叔节点是黑色、x是它的父节点的左孩子，则对x对爷节点进行右旋转(有可能经历情况二，那么x的指向就是情况二处理之前的父节点)
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Entry<K, V> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }

    private void rotateRight(Entry<K, V> node) {
        if (node != null) {
            Entry<K,V> l = node.left;
            node.left = l.right;
            if (l.right != null) l.right.parent = node;
            l.parent = node.parent;
            if (node.parent == null)
                root = l;
            else if (node.parent.right == node)
                node.parent.right = l;
            else node.parent.left = l;
            l.right = node;
            node.parent = l;
        }
    }

    private void rotateLeft(Entry<K, V> node) {
        if (node != null) {
            Entry<K,V> right = node.right;
            node.right = right.left;
            if (right.left != null)
                right.left.parent = node;
            right.parent = node.parent;
            if (node.parent == null)
                root = right;
            else if (node.parent.left == node)
                node.parent.left = right;
            else
                node.parent.right = right;
            right.left = node;
            node.parent = right;
        }
    }

    private static <K,V> boolean colorOf(Entry<K,V> p) {
        return (p == null ? BLACK : p.color);
    }

    private static <K,V> Entry<K,V> parentOf(Entry<K,V> p) {
        return (p == null ? null: p.parent);
    }

    private static <K,V> void setColor(Entry<K,V> p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private static <K,V> Entry<K,V> leftOf(Entry<K,V> p) {
        return (p == null) ? null: p.left;
    }

    private static <K,V> Entry<K,V> rightOf(Entry<K,V> p) {
        return (p == null) ? null: p.right;
    }

    public V get(K key) {
        TreeMapSimple.Entry<K,V> p = getEntry(key);
        return (p==null ? null : p.value);
    }

    private Entry<K, V> getEntry(K key) {
        TreeMapSimple.Entry<K,V> p = root;
        while (p != null) {
            int cmp = compare(key,p.key);
            if (cmp < 0)
                p = p.left;
            else if (cmp > 0)
                p = p.right;
            else
                return p;
        }
        return null;
    }

    private int compare(K key1,K key2) {
        if(comparator != null) {
            return comparator.compare(key1,key2);
        }
        Comparable<? super K> comparable = (Comparable<? super K>) key1;
        return comparable.compareTo(key2);
    }

    static final boolean valEquals(Object o1, Object o2) {
        return (o1==null ? o2==null : o1.equals(o2));
    }

    static final class Entry<K,V> implements Map.Entry<K,V> {
        K key;
        V value;
        Entry<K,V> left;
        Entry<K,V> right;
        Entry<K,V> parent;
        boolean color = BLACK;

        /**
         * Make a new cell with given key, value, and parent, and with
         * {@code null} child links, and BLACK color.
         */
        Entry(K key, V value, TreeMapSimple.Entry<K,V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Returns the key.
         *
         * @return the key
         */
        public K getKey() {
            return key;
        }

        /**
         * Returns the value associated with the key.
         *
         * @return the value associated with the key
         */
        public V getValue() {
            return value;
        }

        /**
         * Replaces the value currently associated with the key with the given
         * value.
         *
         * @return the value associated with the key before this method was
         *         called
         */
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof TreeMapSimple.Entry))
                return false;
            TreeMapSimple.Entry<?,?> e = (TreeMapSimple.Entry<?,?>)o;

            return valEquals(key,e.getKey()) && valEquals(value,e.getValue());
        }

        public int hashCode() {
            int keyHash = (key==null ? 0 : key.hashCode());
            int valueHash = (value==null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }

}

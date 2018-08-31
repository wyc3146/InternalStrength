package com.wyc.tips.algorithm.tree.binary;

import com.wyc.tips.algorithm.tree.TreeEntry;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wangyc 2018-08-31 16:25
 */
public class AVLTreeTest {
    @Test
    public void testRandom() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        for(int i=0;i<100;i++) {
            int randomInt = random.nextInt(1000);
            if(!list.contains(randomInt)) {
                list.add(randomInt);
            }
            tree.add(randomInt, randomInt);
        }
        Collection<TreeEntry<Integer, Integer>> collection = tree.list();
        List<Integer> resultList = collection.stream().map(TreeEntry::getKey).collect(Collectors.toList());

        assertBalance(tree.getRoot(), 0);
        Collections.sort(list);
        Assert.assertEquals(list, resultList);
    }

    private int assertBalance(AVLTree.TreeNode<Integer, Integer> treeNode, int height) {
        if(treeNode == null) return height;
        int leftHeight = assertBalance(treeNode.left, height + 1);
        int rightHeight = assertBalance(treeNode.right, height + 1);
        Assert.assertEquals(true, Math.abs(leftHeight - rightHeight) <= 1);

        return Math.max(leftHeight, rightHeight);
    }

    @Test
    public void test() {
        for(int i=0;i<100;i++) {
            testRandom();
        }
    }

    @Test
    public void testEspect() {
        List<Integer> list = Arrays.asList(new Integer[]{37, 29, 81, 7, 29, 46, 15});
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        for(int i=0;i<list.size();i++) {
            tree.add(list.get(i), list.get(i));
        }
        System.out.println();
        assertBalance(tree.getRoot(), 0);
    }
}

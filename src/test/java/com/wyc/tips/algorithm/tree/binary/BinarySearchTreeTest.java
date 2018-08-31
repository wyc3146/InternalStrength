package com.wyc.tips.algorithm.tree.binary;

import com.wyc.tips.algorithm.tree.TreeEntry;
import org.junit.Test;

import java.util.Collection;
import java.util.Random;

/**
 * @author Wangyc 2018-08-29 20:26
 */
public class BinarySearchTreeTest {
    @Test
    public void testSort() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        for(int i=0;i<7;i++) {
            Random random = new Random();
            int randomInt = random.nextInt(100);
            tree.add(randomInt, randomInt + "");
        }
        Collection<TreeEntry<Integer, String>> entries = tree.list();
        printEnties(entries);
    }

    private void printEnties(Collection<TreeEntry<Integer, String>> entries) {
        for (TreeEntry<Integer, String> entry : entries) {
            System.out.println(entry.getValue());
        }
    }
}

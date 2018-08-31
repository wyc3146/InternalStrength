/*
package com.wyc.tips.algorithm.tree.binary;*/
/**
 * Created by wyc on 2016/12/29.
 *//*


*/
/**
 * AVL树旋转的基本实现
 * @author wangyongcan
 * @Date 2016/12/29 09:32
 *//*

public class AVLTreeTest {

    */
/**
     * 双左，只用旋转一次
     * @param node
     * @return
     *//*

    public static BinaryTreeNode doubleLeft(BinaryTreeNode node) {
        BinaryTreeNode root = node.getLeftChild();
        if(root.getRightChild() != null) {
            node.setLeftChild(root.getRightChild());
        } else {
            node.setLeftChild(null);
        }
        root.setRightChild(node);
        return root;
    }

    */
/**
     * 双右，只用旋转一次
     * @param node
     * @return
     *//*

    public static BinaryTreeNode doubleRight(BinaryTreeNode node) {
        BinaryTreeNode root = node.getRightChild();
        if(root.getLeftChild() != null) {
            node.setRightChild(root.getLeftChild());
        } else {
            node.setRightChild(null);
        }
        root.setLeftChild(node);
        return root;
    }

    public static BinaryTreeNode leftRight(BinaryTreeNode node) {
        node.setLeftChild(doubleRight(node.getLeftChild()));
        BinaryTreeNode root = doubleLeft(node);
        return root;
    }

    public static BinaryTreeNode buildDoubleLeftTree() {
        BinaryTreeNode<Integer,String> root = new BinaryTreeNode<Integer, String>(8,"8");
        root.setLeftChild(new BinaryTreeNode(4,"4"));
        root.setRightChild(new BinaryTreeNode(12,"12"));
        root.getLeftChild().setLeftChild(new BinaryTreeNode(2,"2"));
        root.getLeftChild().setRightChild(new BinaryTreeNode(6,"6"));
        root.getLeftChild().getLeftChild().setLeftChild(new BinaryTreeNode(1,"1"));
        return root;
    }

    public static BinaryTreeNode buildLeftRight() {
        BinaryTreeNode<Integer,String> root = new BinaryTreeNode<Integer, String>(7,"7");
        root.setLeftChild(new BinaryTreeNode(3,"3"));
        root.setRightChild(new BinaryTreeNode(10,"10"));
        root.getLeftChild().setLeftChild(new BinaryTreeNode(1,"1"));
        root.getLeftChild().setRightChild(new BinaryTreeNode(5,"5"));
        root.getLeftChild().getRightChild().setLeftChild(new BinaryTreeNode(4,"4"));
        return root;
    }

    */
/**
     * 递归判断树是否是平衡树，效率不高，先判断根节点再判断子节点
     * @param node
     * @return
     *//*

    public static boolean isBalanceByForce(BinaryTreeNode node) {
        if(node == null) {
            return true;
        }
        int leftHeight = getHeight(node.getLeftChild(),0);
        int rightHeight = getHeight(node.getRightChild(),0);
        int diff = leftHeight - rightHeight;
        if(diff < -1 || diff > 1) {
            return false;
        }
        return isBalanceByForce(node.getLeftChild()) && isBalanceByForce(node.getRightChild());
    }

    public static boolean isBalance(BinaryTreeNode node) {

        return false;
    }

    private static int getHeight(BinaryTreeNode node,int nowHeight) {
        if(node == null) {
            return nowHeight;
        }
        int leftHeight = getHeight(node.getLeftChild(),nowHeight+1);
        int rightHeight = getHeight(node.getRightChild(),nowHeight+1);
        return leftHeight>rightHeight?leftHeight:rightHeight;
    }

    public static void main(String[] args) {
        */
/*BinaryTreeNode root = doubleLeft(buildDoubleLeftTree());
        System.out.println(root);*//*


        */
/*BinaryTreeNode root = leftRight(buildLeftRight());
        System.out.println(root);*//*


        BinaryTreeNode root = buildDoubleLeftTree();
        System.out.println(isBalance(root));
        System.out.println(isBalance(doubleLeft(root)));

    }

}
*/

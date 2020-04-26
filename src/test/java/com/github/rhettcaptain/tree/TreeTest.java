package com.github.rhettcaptain.tree;

import org.junit.Assert;
import org.junit.Test;

public class TreeTest {
    @Test
    public void binSearchTreeTest() {
        BinSearchTree<Integer> binSearchTree = new BinSearchTree<>(5);
        binSearchTree = (BinSearchTree<Integer>)templateTest(binSearchTree);
        Assert.assertEquals(new Integer(1), binSearchTree.findMin(binSearchTree));
    }

    @Test
    public void avlTreeTest() {
        AVLTree2<Integer> avlTree2 = new AVLTree2<>(5);
        avlTree2 = (AVLTree2<Integer>)templateTest(avlTree2);
        Assert.assertEquals(new Integer(1), avlTree2.findMin(avlTree2));
        Assert.assertFalse(checkHeightBroken(avlTree2));
    }

    private TreeNode<Integer> templateTest(TreeNode<Integer> root) {
        for (int i = 0; i < 20; i++) {
            root = root.insert(i, root);
        }
        root = root.delete(0, root);
        root = root.delete(5, root);
        root = root.delete(15, root);
        Assert.assertNotNull(root.find(1, root));
        Assert.assertNull(root.find(5, root));
        root = root.insert(5, root);
        Assert.assertNotNull(root.find(5, root));
        return root;
    }

    private boolean checkHeightBroken(AVLTree2<Integer> node){
        if(node == null){
            return false;
        }
        int heightError = Math.abs(node.getHeight(node.getLeft()) - node.getHeight(node.getRight()));
        return heightError <= 1 && !checkHeightBroken(node.getLeft()) && !checkHeightBroken(node.getRight());
    }
}

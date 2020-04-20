package com.github.rhettcaptain.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class AVLTree<E extends Comparable> {
    private E val;
    AVLTree<E> left;
    AVLTree<E> right;
    int height;
    boolean delete;

    public static void main(String[] args){
        AVLTree<Integer> root = new AVLTree<>(1);
        for(int i=2;i<=20;i++){
            root = root.insert(i, root);
        }
        root.delete(4,root);
        log.info("Tree: {}", root);
    }

    public AVLTree(E val) {
        this(val, null, null, 0);
    }

    public AVLTree(E val, AVLTree<E> left, AVLTree<E> right, int height) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.height = height;
    }

    public static int getHeight(AVLTree tree) {
        return tree == null ? 0 : tree.height;
    }

    public AVLTree<E> insert(E val, AVLTree<E> tree) {
        if (tree == null) {
            return new AVLTree<>(val);
        }

        int compareRes = val.compareTo(tree.val);

        if (compareRes < 0) {
            tree.left = insert(val, tree.left);
            if (getHeight(tree.left) - getHeight(tree.right) == 2) {
                if (val.compareTo(tree.left.val) < 0) {
                    tree = rotateWithLeftChild(tree);
                } else {
                    tree = doubleWithLeftChild(tree);
                }
            }
        } else if (compareRes > 0) {
            tree.right = insert(val, tree.right);
            if (getHeight(tree.right) - getHeight(tree.left) == 2) {
                if (val.compareTo(tree.right.val) > 0) {
                    tree = rotateWithRightChild(tree);
                } else {
                    tree = doubleWithRightChild(tree);
                }
            }
        } else {
            tree.delete = false;
        }
        tree.height = Math.max(getHeight(tree.left), getHeight(tree.right)) + 1;
        return tree;
    }

    public boolean delete(E val, AVLTree<E> tree){
        if(tree == null){
            return false;
        }
        int comp = val.compareTo(tree.val);
        if(comp < 0){
            return delete(val, tree.left);
        }else if(comp > 0){
            return delete(val, tree.right);
        }else{
            tree.delete = true;
            return true;
        }
    }

    private AVLTree<E> doubleWithRightChild(AVLTree<E> tree) {
        tree.right = rotateWithLeftChild(tree.right);
        return rotateWithRightChild(tree);
    }

    private AVLTree<E> rotateWithRightChild(AVLTree<E> k1) {
        AVLTree<E> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(getHeight(k1.left), getHeight(k1.right)) + 1;
        k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
        return k2;
    }

    private AVLTree<E> doubleWithLeftChild(AVLTree<E> tree) {
        tree.left = rotateWithRightChild(tree.left);
        return rotateWithLeftChild(tree);
    }

    private AVLTree<E> rotateWithLeftChild(AVLTree<E> tree) {
        AVLTree<E> k1 = tree.left;
        tree.left = k1.right;
        k1.right = tree;
        tree.height = Math.max(getHeight(tree.left), getHeight(tree.right)) + 1;
        k1.height = Math.max(getHeight(k1.left), getHeight(tree)) + 1;
        return k1;
    }
}

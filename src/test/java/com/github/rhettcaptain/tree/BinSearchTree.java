package com.github.rhettcaptain.tree;

import lombok.Data;

@Data
public class BinSearchTree<E extends Comparable> implements TreeNode<E> {
    private E item;
    private BinSearchTree<E> left;
    private BinSearchTree<E> right;

    public BinSearchTree(E item) {
        this.item = item;
    }

    @Override public TreeNode<E> insert(E item, TreeNode<E> node) {
        BinSearchTree<E> bNode = (BinSearchTree<E>)node;
        if(bNode == null){
            return new BinSearchTree<>(item);
        }
        int compRes = item.compareTo(bNode.getItem());
        if(compRes < 0) {
            bNode.setLeft((BinSearchTree<E>)insert(item, bNode.getLeft()));
        } else if(compRes > 0){
            bNode.setRight((BinSearchTree<E>)insert(item,bNode.getRight()));
        }
        return bNode;
    }

    @Override public TreeNode<E> delete(E item, TreeNode<E> node) {
        BinSearchTree<E> bNode = (BinSearchTree<E>)node;
        if(bNode == null){
            return bNode;
        }
        int compRes = item.compareTo(bNode.getItem());
        if(compRes < 0){
            bNode.setLeft((BinSearchTree<E>)delete(item,bNode.getLeft()));
        } else if(compRes > 0){
            bNode.setRight((BinSearchTree<E>)delete(item,bNode.getRight()));
        } else if(bNode.getLeft() != null && bNode.getRight() != null){
            bNode.setItem(findMin(bNode.getRight()));
            bNode.setRight((BinSearchTree<E>)delete(bNode.getItem(),bNode.getRight()));
        } else{
            bNode = bNode.getLeft() != null ? bNode : bNode.getRight();
        }
        return bNode;
    }

    @Override public TreeNode<E> find(E item, TreeNode<E> node) {
        BinSearchTree<E> bNode = (BinSearchTree<E>)node;
        if(bNode == null){
            return bNode;
        }
        int compRes = item.compareTo(bNode.getItem());
        if(compRes < 0) {
            return find(item, bNode.getLeft());
        } else if(compRes > 0){
            return find(item, bNode.getRight());
        } else {
            return bNode;
        }
    }

    public E findMin(BinSearchTree<E> node){
        if(node == null){
            return null;
        }
        return node.getLeft() != null ? findMin(node.getLeft()) : node.getItem();
    }
}

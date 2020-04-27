package com.github.rhettcaptain.tree;

import lombok.Data;

@Data
public class BTree<E extends Comparable> implements TreeNode<E> {
    private final static int order = 5;
    private int n;
    private BTree<E>[] children;
    private E[] items;

    public BTree(){
        this.n = 0;
        this.children = new BTree[order];
        this.items = (E[])new Object[order-1];
    }

    public BTree(E item){
        this.children = new BTree[order];
        this.items = (E[])new Object[order-1];
        items[0] = item;
        this.n = 1;

    }

    @Override public TreeNode<E> insert(E item, TreeNode<E> node) {
        BTree<E> bNode = (BTree<E>)node;
        if(bNode == null){
            bNode = new BTree<>(item);
            return bNode;
        }
        for(int i=0;i<bNode.getN();i++){
            if()
        }
    }

    @Override public TreeNode<E> delete(E item, TreeNode<E> node) {
        return null;
    }

    @Override public TreeNode<E> find(E item, TreeNode<E> node) {
        return null;
    }
}

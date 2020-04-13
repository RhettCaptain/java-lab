package com.github.rhettcaptain.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Slf4j
public class MyTreeNode<E> {
    private E item;
    private MyTreeNode firstChild;
    private MyTreeNode nextSibling;

    public static void main(String[] args) {
        MyTreeNode<Integer> rightSubTree = new MyTreeNode<>(3, new MyTreeNode(5, null, new MyTreeNode(6, null, null)),
                null);
        MyTreeNode<Integer> leftSubTree = new MyTreeNode<>(2, new MyTreeNode(4, null, null), rightSubTree);
        MyTreeNode<Integer> myTree = new MyTreeNode<>(1, leftSubTree, null);
        MyTreeNode<Integer> treePtn = myTree;
        preOrder(treePtn);
    }

    private static void preOrder(MyTreeNode<Integer> node){
        if(node == null){
            return;
        }
        log.info("{}-",node.item);
        if(node.getFirstChild() != null){
            preOrder(node.getFirstChild());
        }
        if(node.getNextSibling() != null){
            preOrder(node.getNextSibling());
        }
    }
}

package com.github.rhettcaptain.tree;

import lombok.Data;

@Data
public class AVLTree2<E extends Comparable> implements TreeNode<E> {
    private E item;
    private AVLTree2<E> left;
    private AVLTree2<E> right;
    private int height;

    public AVLTree2(E item){
        this.item = item;
        this.height = 1;
    }

    public int getHeight(AVLTree2<E> node){
        return node == null ? 0 : node.getHeight();
    }

    // TODO how height is updated when insert?
    @Override public TreeNode<E> insert(E item, TreeNode<E> node) {
        AVLTree2<E> aNode = (AVLTree2<E>)node;
        if(aNode == null){
            aNode = new AVLTree2<>(item);
            return aNode;
        }
        int compRes = item.compareTo(aNode.getItem());
        if(compRes < 0){
            aNode.setLeft((AVLTree2<E>)insert(item, aNode.getLeft()));
            if(getHeight(aNode.getLeft()) - getHeight(aNode.getRight()) == 2){
                int childRes = item.compareTo(aNode.getLeft().getItem());
                aNode = childRes < 0 ? leftSingleRotate(aNode) : leftDoubleRotate(aNode);
            }
        }else if(compRes > 0){
            aNode.setRight((AVLTree2<E>)insert(item,aNode.getRight()));
            if(getHeight(aNode.getRight()) - getHeight(aNode.getLeft()) == 2){
                int childRes = item.compareTo(aNode.getRight().getItem());
                aNode = childRes < 0 ? rightDoubleRotate(aNode) : rightSingleRotate(aNode);
            }
        }
        aNode.setHeight(Math.max(getHeight(aNode.getLeft()),getHeight(aNode.getRight())) + 1);
        return aNode;
    }

    @Override public TreeNode<E> delete(E item, TreeNode<E> node) {
        AVLTree2<E> aNode = (AVLTree2<E>)node;
        if(aNode == null){
            return null;
        }
        int compRes = item.compareTo(aNode.getItem());
        if(compRes < 0){
            aNode.setLeft((AVLTree2<E>)delete(item,aNode.getLeft()));
            if( getHeight(aNode.getRight()) - getHeight(aNode.getLeft()) == 2){
                aNode = getHeight(aNode.getRight().getRight()) > getHeight(aNode.getRight().getLeft()) ? rightSingleRotate(aNode) : rightDoubleRotate(aNode);
            }
        } else if(compRes > 0){
            aNode.setRight((AVLTree2<E>)delete(item,aNode.getRight()));
            if( getHeight(aNode.getLeft()) - getHeight(aNode.getRight()) == 2){
                aNode = getHeight(aNode.getLeft().getLeft()) > getHeight(aNode.getLeft().getRight()) ? leftSingleRotate(aNode) : leftDoubleRotate(aNode);
            }
        } else if(aNode.getLeft() != null && aNode.getRight() != null){
            aNode.setItem(findMin(aNode.getRight()));
            aNode.setRight((AVLTree2<E>)delete(aNode.getItem(), aNode.getRight()));
            if( getHeight(aNode.getLeft()) - getHeight(aNode.getRight()) == 2){
                aNode = getHeight(aNode.getLeft().getLeft()) > getHeight(aNode.getLeft().getRight()) ? leftSingleRotate(aNode) : leftDoubleRotate(aNode);
            }
        } else {
            aNode = aNode.getLeft() != null ? aNode.getLeft() : aNode.getRight();
        }
        if (aNode != null) {
            aNode.setHeight(Math.max(getHeight(aNode.getLeft()), getHeight(aNode.getRight())) + 1);
        }

        return aNode;
    }

    @Override public TreeNode<E> find(E item, TreeNode<E> node) {
        AVLTree2<E> aNode = (AVLTree2<E>)node;
        if(aNode == null){
            return null;
        }
        int compRes = item.compareTo(aNode.getItem());
        if(compRes < 0){
            return find(item, aNode.getLeft());
        } else if(compRes > 0){
            return find(item, aNode.getRight());
        } else {
            return aNode;
        }
    }

    private AVLTree2<E> leftSingleRotate(AVLTree2<E> node){
        AVLTree2<E> root = node;
        AVLTree2<E> left = node.getLeft();
        root.setLeft(left.getRight());
        root.setHeight(Math.max(getHeight(root.getLeft()), getHeight(root.getRight())) + 1);
        left.setRight(root);
        left.setHeight(Math.max(getHeight(left.getLeft()), getHeight(left.getRight())) + 1);
        return left;
    }

    private AVLTree2<E> rightSingleRotate(AVLTree2<E> node){
        AVLTree2<E> root = node;
        AVLTree2<E> right = node.getRight();
        root.setRight(right.getLeft());
        root.setHeight(Math.max(getHeight(root.getLeft()),getHeight(root.getRight())) + 1);
        right.setLeft(root);
        right.setHeight(Math.max( getHeight(right.getLeft()), getHeight(right.getRight()) ) + 1);
        return right;
    }

    private AVLTree2<E> leftDoubleRotate(AVLTree2<E> node){
        node.setLeft(rightSingleRotate(node.getLeft()));
        return leftSingleRotate(node);
    }

    private AVLTree2<E> rightDoubleRotate(AVLTree2<E> node){
        node.setRight(leftSingleRotate(node.getRight()));
        return rightSingleRotate(node);
    }

    public E findMin(AVLTree2<E> node){
        if(node == null){
            return null;
        }
        return node.getLeft() != null ? findMin(node.getLeft()) : node.getItem();
    }
}

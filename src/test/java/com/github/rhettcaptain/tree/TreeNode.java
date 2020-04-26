package com.github.rhettcaptain.tree;

public interface TreeNode<E> {
    TreeNode<E> insert(E item, TreeNode<E> node);

    TreeNode<E> delete(E item, TreeNode<E> node);

    TreeNode<E> find(E item, TreeNode<E> node);
}

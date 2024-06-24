package ru.academits.orlov.tree;

public class TreeNode<E> {
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;
    private final E data;

    public TreeNode(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public TreeNode<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<E> node) {
        leftChild = node;
    }

    public TreeNode<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<E> node) {
        rightChild = node;
    }
}

package ru.academits.orlov.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private Comparator<? super E> comparator;
    private TreeNode<E> root;
    private int size;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    public void insert(E element) {
        TreeNode<E> newNode = new TreeNode<>(element);

        if (size == 0) {
            root = newNode;
        } else {
            //noinspection unchecked
            Comparable<E> comparableElement = (Comparable<E>) element;
            TreeNode<E> currentNode = root;

            while (true) {
                if (currentNode.getData() == null
                        || comparator != null && comparator.compare(element, currentNode.getData()) >= 0
                        || element != null && comparableElement.compareTo(currentNode.getData()) >= 0) {
                    if (currentNode.getRightChild() != null) {
                        currentNode = currentNode.getRightChild();
                    } else {
                        currentNode.setRightChild(newNode);
                        break;
                    }
                } else {
                    if (currentNode.getLeftChild() != null) {
                        currentNode = currentNode.getLeftChild();
                    } else {
                        currentNode.setLeftChild(newNode);
                        break;
                    }
                }
            }
        }

        ++size;
    }

    public boolean contains(E element) {
        if (size == 0) {
            return false;
        }

        TreeNode<E> currentNode = root;

        if (element == null) {
            while (true) {
                if (currentNode == null) {
                    return false;
                }

                if (currentNode.getData() == null) {
                    return true;
                }

                currentNode = currentNode.getLeftChild();
            }
        } else {
            //noinspection unchecked
            Comparable<E> comparableElement = (Comparable<E>) element;

            while (true) {
                if (currentNode.getData() == null
                        || comparator != null && comparator.compare(element, currentNode.getData()) > 0
                        || currentNode.getData() != null && comparableElement.compareTo(currentNode.getData()) > 0) {

                    if (currentNode.getRightChild() == null) {
                        return false;
                    }

                    currentNode = currentNode.getRightChild();
                } else if (comparator != null && comparator.compare(element, currentNode.getData()) < 0
                        || comparableElement.compareTo(currentNode.getData()) < 0) {
                    if (currentNode.getLeftChild() == null) {
                        return false;
                    }

                    currentNode = currentNode.getLeftChild();
                } else {
                    return true;
                }
            }
        }
    }

    public boolean remove(E element) {
        if (size == 0) {
            return false;
        }

        TreeNode<E> removedNodeParent = null;
        TreeNode<E> removedNode = root;
        boolean isLeftChild = true;

        if (element == null) {
            while (true) {
                if (removedNode == null) {
                    return false;
                }

                if (removedNode.getData() == null) {
                    break;
                } else {
                    removedNodeParent = removedNode;
                    removedNode = removedNode.getLeftChild();
                }
            }
        } else {
            while (true) {
                if (removedNode == null) {
                    return false;
                }

                if (removedNode.getData() == null) {
                    if (removedNode.getRightChild() == null) {
                        return false;
                    }

                    removedNodeParent = removedNode;
                    removedNode = removedNode.getRightChild();
                    isLeftChild = false;
                } else {
                    //noinspection unchecked
                    Comparable<E> comparableElement = (Comparable<E>) element;

                    if (comparator != null && comparator.compare(element, removedNode.getData()) == 0
                            || comparableElement.compareTo(removedNode.getData()) == 0) {
                        break;
                    }

                    removedNodeParent = removedNode;

                    if (comparator != null && comparator.compare(element, removedNode.getData()) < 0
                            || comparableElement.compareTo(removedNode.getData()) < 0) {
                        removedNode = removedNode.getLeftChild();
                        isLeftChild = true;
                    } else {
                        removedNode = removedNode.getRightChild();
                        isLeftChild = false;
                    }
                }
            }
        }

        boolean hasLeftChild = removedNode.getLeftChild() != null;
        boolean hasRightChild = removedNode.getRightChild() != null;
        TreeNode<E> replacementNode = null;
        TreeNode<E> minLeftNode = removedNode.getRightChild();

        if (hasLeftChild && hasRightChild) {
            TreeNode<E> minLeftNodeParent = removedNode;

            while (minLeftNode.getLeftChild() != null) {
                minLeftNodeParent = minLeftNode;
                minLeftNode = minLeftNode.getLeftChild();
            }

            if (minLeftNodeParent == removedNode) {
                minLeftNodeParent.setRightChild(minLeftNode.getRightChild());
            } else {
                minLeftNodeParent.setLeftChild(minLeftNode.getRightChild());
            }

            minLeftNode.setLeftChild(removedNode.getLeftChild());
            minLeftNode.setRightChild(removedNode.getRightChild());

            if (removedNodeParent == null) {
                root = minLeftNode;
            } else {
                replacementNode = minLeftNode;
            }
        } else {
            if (hasLeftChild) {
                replacementNode = removedNode.getLeftChild();
            } else if (hasRightChild) {
                replacementNode = removedNode.getRightChild();
            }

            if (removedNodeParent == null) {
                root = replacementNode;
            }
        }

        if (removedNodeParent != null) {
            if (isLeftChild) {
                removedNodeParent.setLeftChild(replacementNode);
            } else {
                removedNodeParent.setRightChild(replacementNode);
            }
        }

        --size;

        return true;
    }

    public void traverseBreadth(Consumer<E> consumer) {
        if (size > 0) {
            Queue<TreeNode<E>> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                TreeNode<E> currentNode = queue.remove();

                consumer.accept(currentNode.getData());

                if (currentNode.getLeftChild() != null) {
                    queue.add(currentNode.getLeftChild());
                }

                if (currentNode.getRightChild() != null) {
                    queue.add(currentNode.getRightChild());
                }
            }
        }
    }

    public void traverseDepth(Consumer<E> consumer) {
        if (size > 0) {
            Deque<TreeNode<E>> deque = new LinkedList<>();
            deque.push(root);

            while (!deque.isEmpty()) {
                TreeNode<E> currentNode = deque.pop();

                consumer.accept(currentNode.getData());

                if (currentNode.getRightChild() != null) {
                    deque.push(currentNode.getRightChild());
                }

                if (currentNode.getLeftChild() != null) {
                    deque.push(currentNode.getLeftChild());
                }
            }
        }
    }

    public void traverseDepthRecursive(Consumer<E> consumer) {
        traverseDepthRecursive(root, consumer);
    }

    private void traverseDepthRecursive(TreeNode<E> node, Consumer<E> consumer) {
        if (node != null) {
            consumer.accept(node.getData());

            traverseDepthRecursive(node.getLeftChild(), consumer);
            traverseDepthRecursive(node.getRightChild(), consumer);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        if (size == 1) {
            return "[" + root.getData() + ']';
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        Queue<TreeNode<E>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.remove();

            stringBuilder.append(currentNode.getData()).append(", ");

            if (currentNode.getLeftChild() != null) {
                queue.add(currentNode.getLeftChild());
            }

            if (currentNode.getRightChild() != null) {
                queue.add(currentNode.getRightChild());
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}

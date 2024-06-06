package ru.academits.orlov.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int elementsCount;

    public BinarySearchTree(E data) {
        root = new TreeNode<>(data);
        elementsCount = 1;
    }

    public BinarySearchTree() {
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public boolean insert(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Передана пустая ссылка. Бинарное дерево поиска не поддерживает null.");
        }

        if (elementsCount == 0) {
            root = new TreeNode<>(element);
            ++elementsCount;

            return true;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            if (element.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeftChild() != null) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode.setLeftChild(new TreeNode<>(element));
                    ++elementsCount;

                    return true;
                }
            } else {
                if (currentNode.getRightChild() != null) {
                    currentNode = currentNode.getRightChild();
                } else {
                    currentNode.setRightChild(new TreeNode<>(element));
                    ++elementsCount;

                    return true;
                }
            }
        }
    }

    public boolean contains(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Передана пустая ссылка. Бинарное дерево поиска не поддерживает null.");
        }

        if (elementsCount == 0) {
            return false;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            if (element.equals(currentNode.getData())) {
                return true;
            }

            if (element.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeftChild() == null) {
                    return false;
                }

                currentNode = currentNode.getLeftChild();
            } else {
                if (currentNode.getRightChild() == null) {
                    return false;
                }

                currentNode = currentNode.getRightChild();
            }
        }
    }

    public boolean remove(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Передана пустая ссылка. Бинарное дерево поиска не поддерживает null.");
        }

        if (elementsCount == 0) {
            return false;
        }

        if (element.equals(root.getData())) {
            return removeRoot();
        }

        TreeNode<E> removedNodeParent = root;
        TreeNode<E> removedNode;
        boolean isRemovedNodeLeftChild;

        if (element.compareTo(root.getData()) < 0) {
            removedNode = root.getLeftChild();
            isRemovedNodeLeftChild = true;
        } else {
            removedNode = root.getRightChild();
            isRemovedNodeLeftChild = false;
        }

        while (true) {
            if (element.equals(removedNode.getData())) {
                if (removedNode.getLeftChild() == null) {
                    if (removedNode.getRightChild() == null) {
                        if (isRemovedNodeLeftChild) {
                            removedNodeParent.setLeftChild(null);
                        } else {
                            removedNodeParent.setRightChild(null);
                        }
                    } else {
                        if (isRemovedNodeLeftChild) {
                            removedNodeParent.setLeftChild(removedNode.getRightChild());
                        } else {
                            removedNodeParent.setRightChild(removedNode.getRightChild());
                        }
                    }

                    --elementsCount;

                    return true;
                }

                if (removedNode.getRightChild() == null) {
                    if (isRemovedNodeLeftChild) {
                        removedNodeParent.setLeftChild(removedNode.getLeftChild());
                    } else {
                        removedNodeParent.setRightChild(removedNode.getLeftChild());
                    }

                    --elementsCount;

                    return true;
                }

                TreeNode<E> removedNodeReplaceCandidateParent = removedNode;
                TreeNode<E> removedNodeReplaceCandidate = removedNode.getRightChild();

                while (true) {
                    if (removedNodeReplaceCandidate.getLeftChild() == null) {
                        if (removedNodeReplaceCandidate.getRightChild() == null) {
                            removedNodeReplaceCandidate = removedNode.getRightChild();
                        } else {
                            removedNodeReplaceCandidate = removedNodeReplaceCandidate.getRightChild();
                            continue;
                        }
                    } else {
                        while (removedNodeReplaceCandidate.getLeftChild() != null) {
                            removedNodeReplaceCandidateParent = removedNodeReplaceCandidate;
                            removedNodeReplaceCandidate = removedNodeReplaceCandidate.getLeftChild();
                        }

                        if (removedNodeReplaceCandidate.getRightChild() == null) {
                            removedNodeReplaceCandidateParent.setLeftChild(null);
                        } else {
                            removedNodeReplaceCandidateParent.setLeftChild(removedNodeReplaceCandidate.getRightChild());
                        }

                        removedNodeReplaceCandidate.setRightChild(removedNode.getRightChild());
                    }

                    removedNodeReplaceCandidate.setLeftChild(removedNode.getLeftChild());

                    if (isRemovedNodeLeftChild) {
                        removedNodeParent.setLeftChild(removedNodeReplaceCandidate);
                    } else {
                        removedNodeParent.setRightChild(removedNodeReplaceCandidate);
                    }

                    --elementsCount;

                    return true;
                }
            }

            if (element.compareTo(removedNode.getData()) < 0) {
                if (removedNode.getLeftChild() == null) {
                    return false;
                }

                removedNodeParent = removedNode;
                removedNode = removedNode.getLeftChild();
                isRemovedNodeLeftChild = true;
            } else {
                if (removedNode.getRightChild() == null) {
                    return false;
                }

                removedNodeParent = removedNode;
                removedNode = removedNode.getRightChild();
                isRemovedNodeLeftChild = false;
            }
        }
    }

    private boolean removeRoot() {
        if (elementsCount == 1) {
            root = null;
        } else {
            if (root.getLeftChild() == null) {
                root = root.getRightChild();
            } else {
                if (root.getRightChild() == null) {
                    root = root.getLeftChild();
                } else {
                    TreeNode<E> leftChild = root.getLeftChild();
                    root = root.getRightChild();
                    TreeNode<E> minLeftNode = root;

                    while (minLeftNode.getLeftChild() != null) {
                        minLeftNode = minLeftNode.getLeftChild();
                    }

                    minLeftNode.setLeftChild(leftChild);
                }
            }
        }

        --elementsCount;

        return true;
    }

    public void traverseBreadth() {
        if (elementsCount == 0) {
            System.out.println("[]");
        } else {
            System.out.println(getAsString());
        }
    }

    public void traverseDepth() {
        if (elementsCount == 0) {
            System.out.println("[]");

            return;
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        Deque<TreeNode<E>> nodes = new LinkedList<>();

        nodes.push(root);

        while (!nodes.isEmpty()) {
            TreeNode<E> currentNode = nodes.pop();

            stringBuilder.append(currentNode.getData()).append(", ");

            if (currentNode.getRightChild() != null) {
                nodes.push(currentNode.getRightChild());
            }

            if (currentNode.getLeftChild() != null) {
                nodes.push(currentNode.getLeftChild());
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        System.out.println(stringBuilder);
    }

    public void traverseDepthRecursive() {
        if (elementsCount == 0) {
            System.out.println("[]");

            return;
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        visit(root, stringBuilder);

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        System.out.println(stringBuilder);
    }

    private void visit(TreeNode<E> node, StringBuilder stringBuilder) {
        if (node != null) {
            stringBuilder.append(node.getData()).append(", ");

            visit(node.getLeftChild(), stringBuilder);
            visit(node.getRightChild(), stringBuilder);
        }
    }

    @Override
    public String toString() {
        if (elementsCount == 0) {
            return "[]";
        }

        if (elementsCount == 1) {
            return "[" + root.getData() + ']';
        }

        return getAsString();
    }

    private String getAsString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Queue<TreeNode<E>> nodes = new LinkedList<>();

        nodes.add(root);

        while (!nodes.isEmpty()) {
            TreeNode<E> currentNode = nodes.remove();

            stringBuilder.append(currentNode.getData()).append(", ");

            if (currentNode.getLeftChild() != null) {
                nodes.add(currentNode.getLeftChild());
            }

            if (currentNode.getRightChild() != null) {
                nodes.add(currentNode.getRightChild());
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}

package ru.academits.orlov.tree_main;

import ru.academits.orlov.tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<String> strings1 = new BinarySearchTree<>("foo");

        System.out.println("strings1: " + strings1);
        System.out.println("strings1.remove(\"foo\"): " + strings1.remove("foo"));
        System.out.println("strings1: " + strings1);
        System.out.println();

        BinarySearchTree<String> strings2 = new BinarySearchTree<>();
        strings2.insert("bar");
        strings2.insert("baz");
        strings2.insert("foo");
        strings2.insert("aba");

        System.out.println("strings2: " + strings2);
        System.out.println("strings2.contains(\"foo\"): " + strings2.contains("foo"));
        System.out.println("strings2.contains(\"bar\"): " + strings2.contains("bar"));
        System.out.println("strings2.contains(\"baz\"): " + strings2.contains("baz"));
        System.out.println("strings2.contains(\"aba\"): " + strings2.contains("aba"));
        System.out.println("strings2.contains(\"con\"): " + strings2.contains("con"));
        System.out.println("strings2.contains(\"zyx\"): " + strings2.contains("zyx"));
        System.out.println();

        BinarySearchTree<Integer> integers1 = new BinarySearchTree<>();
        integers1.insert(8);
        integers1.insert(3);
        integers1.insert(10);
        integers1.insert(1);
        integers1.insert(6);
        integers1.insert(14);
        integers1.insert(4);
        integers1.insert(7);
        integers1.insert(13);
        integers1.insert(30);
        integers1.insert(25);
        integers1.insert(35);
        integers1.insert(23);
        integers1.insert(24);

        System.out.println("integers1: " + integers1);
        System.out.println("integers1.getElementsCount(): " + integers1.getElementsCount());
        System.out.println();
        System.out.println("integers1.traverseBreadth():");
        integers1.traverseBreadth();

        System.out.println();
        System.out.println("integers1.traverseDepth():");
        integers1.traverseDepth();

        System.out.println();
        System.out.println("integers1.traverseDepthRecursive():");
        integers1.traverseDepthRecursive();

        System.out.println();
        System.out.println("integers1.remove(3): " + integers1.remove(3));
        System.out.println("integers1: " + integers1);
        System.out.println("integers1.remove(14): " + integers1.remove(14));
        System.out.println("integers1: " + integers1);
        System.out.println("integers1.remove(10): " + integers1.remove(10));
        System.out.println("integers1: " + integers1);
        System.out.println("integers1.remove(7): " + integers1.remove(7));
        System.out.println("integers1: " + integers1);
        System.out.println("integers1.remove(24): " + integers1.remove(24));
        System.out.println("integers1: " + integers1);
        System.out.println("integers1.remove(30): " + integers1.remove(30));
        System.out.println("integers1: " + integers1);
        System.out.println("integers1.remove(8): " + integers1.remove(8));
        System.out.println("integers1: " + integers1);
        System.out.println();

        BinarySearchTree<Integer> integers2 = new BinarySearchTree<>(6);

        System.out.println("integers2: " + integers2);
        System.out.println("integers2.insert(8): " + integers2.insert(8));
        System.out.println("integers2: " + integers2);
        System.out.println("integers2.remove(6): " + integers2.remove(6));
        System.out.println("integers2: " + integers2);
        System.out.println();

        BinarySearchTree<Integer> integers3 = new BinarySearchTree<>(6);

        System.out.println("integers3: " + integers3);
        System.out.println("integers3.insert(4): " + integers3.insert(4));
        System.out.println("integers3: " + integers3);
        System.out.println("integers3.remove(6): " + integers3.remove(6));
        System.out.println("integers3: " + integers3);
        System.out.println("integers3.remove(4): " + integers3.remove(4));
        System.out.println("integers3: " + integers3);
        System.out.println();

        BinarySearchTree<Integer> integers4 = new BinarySearchTree<>(6);

        System.out.println("integers4: " + integers4);
        System.out.println("integers4.remove(6): " + integers4.remove(6));
        System.out.println("integers4: " + integers4);
        System.out.println();

        BinarySearchTree<Integer> integers5 = new BinarySearchTree<>(10);
        integers5.insert(4);
        integers5.insert(5);
        integers5.insert(6);
        integers5.insert(7);
        integers5.insert(3);

        System.out.println("integers5: " + integers5);
        System.out.println("integers5.remove(4): " + integers5.remove(4));
        System.out.println("integers5: " + integers5);
        System.out.println();

        BinarySearchTree<Integer> integers6 = new BinarySearchTree<>(10);
        integers6.insert(20);
        integers6.insert(25);
        integers6.insert(23);
        integers6.insert(30);
        integers6.insert(35);

        System.out.println("integers6: " + integers6);
        System.out.println("integers6.remove(25): " + integers6.remove(25));
        System.out.println("integers6: " + integers6);
        System.out.println();

        BinarySearchTree<Integer> integers7 = new BinarySearchTree<>(30);
        integers7.insert(20);
        integers7.insert(5);
        integers7.insert(3);
        integers7.insert(9);
        integers7.insert(10);
        integers7.insert(15);

        System.out.println("integers7: " + integers7);
        System.out.println("integers7.remove(5): " + integers7.remove(5));
        System.out.println("integers7: " + integers7);
    }
}
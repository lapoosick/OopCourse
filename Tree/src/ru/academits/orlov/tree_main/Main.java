package ru.academits.orlov.tree_main;

import ru.academits.orlov.tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> integers1 = new BinarySearchTree<>();
        integers1.insert(8);
        integers1.insert(null);
        integers1.insert(null);
        integers1.insert(3);
        integers1.insert(null);
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
        System.out.println("integers1.contains(8): " + integers1.contains(8));
        System.out.println("integers1.contains(3): " + integers1.contains(3));
        System.out.println("integers1.contains(1): " + integers1.contains(1));
        System.out.println("integers1.contains(6): " + integers1.contains(6));
        System.out.println("integers1.contains(14): " + integers1.contains(14));
        System.out.println("integers1.contains(4): " + integers1.contains(4));
        System.out.println("integers1.contains(7): " + integers1.contains(7));
        System.out.println("integers1.contains(13): " + integers1.contains(13));
        System.out.println("integers1.contains(30): " + integers1.contains(30));
        System.out.println("integers1.contains(25): " + integers1.contains(25));
        System.out.println("integers1.contains(35): " + integers1.contains(35));
        System.out.println("integers1.contains(23): " + integers1.contains(23));
        System.out.println("integers1.contains(24): " + integers1.contains(24));
        System.out.println("integers1.contains(22): " + integers1.contains(22));
        System.out.println();

        System.out.println("integers1.getElementsCount(): " + integers1.getSize());
        System.out.println();
        System.out.println("integers1.traverseBreadth():");
        integers1.traverseBreadth(System.out::println);

        System.out.println();
        System.out.println("integers1.traverseDepth():");
        integers1.traverseDepth(System.out::println);

        System.out.println();
        System.out.println("integers1.traverseDepthRecursive():");
        integers1.traverseDepthRecursive(System.out::println);

        System.out.println();
        System.out.println("integers1.remove(8): " + integers1.remove(8));
        System.out.println("integers1: " + integers1);
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
        System.out.println("integers1.remove(null): " + integers1.remove(null));
        System.out.println("integers1: " + integers1);
        System.out.println("integers1.remove(4): " + integers1.remove(4));
        System.out.println("integers1: " + integers1);
        System.out.println();

        BinarySearchTree<Integer> integers2 = new BinarySearchTree<>();
        integers2.insert(null);
        System.out.println("integers2: " + integers2);
        integers2.insert(null);
        System.out.println("integers2: " + integers2);
        integers2.insert(5);
        System.out.println("integers2: " + integers2);
        integers2.insert(null);
        System.out.println("integers2: " + integers2);
        integers2.insert(3);
        System.out.println("integers2: " + integers2);
        integers2.insert(null);
        System.out.println("integers2: " + integers2);
        System.out.println();
        System.out.println("integers2.contains(null): " + integers2.contains(null));
        System.out.println("integers2.contains(3): " + integers2.contains(3));
        System.out.println("integers2.contains(5): " + integers2.contains(5));
        System.out.println("integers2.contains(4): " + integers2.contains(4));
        System.out.println();
        System.out.println("integers2.remove(null): " + integers2.remove(null));
        System.out.println("integers2: " + integers2);
        System.out.println("integers2.remove(null): " + integers2.remove(null));
        System.out.println("integers2: " + integers2);
        System.out.println("integers2.remove(5): " + integers2.remove(5));
        System.out.println("integers2: " + integers2);
        System.out.println("integers2.remove(null): " + integers2.remove(null));
        System.out.println("integers2: " + integers2);
    }
}
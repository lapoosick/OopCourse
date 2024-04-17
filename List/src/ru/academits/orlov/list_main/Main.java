package ru.academits.orlov.list_main;

import ru.academits.orlov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> stringsList1 = new SinglyLinkedList<>("head");

        System.out.println("После создания списка stringsList1.getCount() = " + stringsList1.getCount());

        stringsList1.addFirst("zero");

        System.out.println("После добавления элемента stringsList1.getCount() = " + stringsList1.getCount());
        System.out.println("stringsList1.getFirst() = " + stringsList1.getFirst());
        System.out.println("stringsList1: " + stringsList1);
        System.out.println("stringsList1.set(1, \"one\"), старое значение: " + stringsList1.set(1, "one"));
        System.out.println("stringsList1: " + stringsList1);

        stringsList1.add(2, "two");

        System.out.println("stringsList1: " + stringsList1);

        stringsList1.reverse();

        System.out.println("reversed stringsList1: " + stringsList1);
        System.out.println("удаление элемента one: " + stringsList1.remove("one"));
        System.out.println("удаление элемента five: " + stringsList1.remove("five"));
        System.out.println("reversed stringsList1 после удаления элемента one: " + stringsList1);
        System.out.println();

        SinglyLinkedList<Integer> integersList = new SinglyLinkedList<>(5);
        integersList.add(0, 3);
        integersList.add(2, 8);
        integersList.add(1, 0);

        System.out.println("integersList: " + integersList);

        integersList.reverse();

        System.out.println("reversed integersList: " + integersList);
        System.out.println("удаление элемента 5: " + integersList.remove(Integer.valueOf(5)));
        System.out.println("reversed integersList после удаления элемента 5: " + integersList);
        System.out.println();

        SinglyLinkedList<String> stringsList2 = new SinglyLinkedList<>();
        stringsList2.addFirst("a");
        stringsList2.addFirst("b");
        stringsList2.addFirst(null);
        stringsList2.addFirst("d");
        stringsList2.addFirst("e");

        System.out.println("stringsList2: " + stringsList2);

        stringsList2.reverse();

        System.out.println("reversed stringsList2: " + stringsList2);

        SinglyLinkedList<String> copy = stringsList2.copy();

        System.out.println("reversed stringsList2 copy: " + copy);
        System.out.println("reversed stringsList2.get(2): " + stringsList2.get(2));
        System.out.println("reversed stringsList2.get(1): " + stringsList2.get(1));
        System.out.println("reversed stringsList2.get(0): " + stringsList2.get(0));
        System.out.println("reversed stringsList2.remove(null): " + stringsList2.remove(null));
        System.out.println("reversed stringsList2.remove(\"e\"): " + stringsList2.remove("e"));
        System.out.println("reversed stringsList2: " + stringsList2);
        System.out.println("reversed stringsList2.remove(2): " + stringsList2.remove(2));
        System.out.println("reversed stringsList2: " + stringsList2);
        System.out.println("reversed stringsList2.removeFirst(): " + stringsList2.removeFirst());
        System.out.println("reversed stringsList2 после удаления первого элемента: " + stringsList2);
        System.out.println("reversed stringsList2 copy: " + copy);
    }
}
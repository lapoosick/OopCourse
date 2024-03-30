package ru.academits.orlov.list_main;

import ru.academits.orlov.list.ListItem;
import ru.academits.orlov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        ListItem<String> listItem = new ListItem<>("head");
        SinglyLinkedList<String> stringsList0 = new SinglyLinkedList<>(listItem);

        System.out.println("После создания списка stringsList0.getCount() = " + stringsList0.getCount());

        stringsList0.addFirst("zero");

        System.out.println("После добавления элемента stringsList0.getCount() = " + stringsList0.getCount());
        System.out.println("stringsList0.getHead() = " + stringsList0.getHead());
        System.out.println("stringsList0.getFirst() = " + stringsList0.getFirst());
        System.out.println("stringsList0: " + stringsList0);
        System.out.println("stringsList0.set(1, \"one\") = " + stringsList0.set(1, "one"));
        System.out.println("stringsList0: " + stringsList0);

        stringsList0.add(2, "two");

        System.out.println("stringsList0: " + stringsList0);

        stringsList0.reverse();

        System.out.println("reversed stringsList0: " + stringsList0);
        System.out.println("удаление элемента one: " + stringsList0.remove("one"));
        System.out.println("удаление элемента five: " + stringsList0.remove("five"));
        System.out.println("reversed stringsList0 после удаления элемента one: " + stringsList0);
        System.out.println();

        SinglyLinkedList<Integer> integersList = new SinglyLinkedList<>(new ListItem<>(5));
        integersList.add(0, 3);
        integersList.add(2, 8);

        System.out.println("integersList: " + integersList);

        integersList.reverse();

        System.out.println("reversed integersList: " + integersList);
        System.out.println("удаление элемента 5: " + integersList.remove(Integer.valueOf(5)));
        System.out.println("reversed integersList после удаления элемента 5: " + integersList);
        System.out.println();

        SinglyLinkedList<String> stringsList1 = new SinglyLinkedList<>();
        stringsList1.addFirst("a");
        stringsList1.addFirst("b");
        stringsList1.addFirst("c");
        stringsList1.addFirst("d");
        stringsList1.addFirst("e");

        System.out.println("stringsList1: " + stringsList1);

        stringsList1.reverse();

        System.out.println("reversed stringsList1: " + stringsList1);
        System.out.println("reversed stringsList1 copy: " + stringsList1.copy());
        System.out.println("reversed stringsList1.get(2): " + stringsList1.get(2));
        System.out.println("reversed stringsList1.remove(2): " + stringsList1.remove(2));
        System.out.println("reversed stringsList1: " + stringsList1);
    }
}
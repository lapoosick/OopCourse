package ru.academits.orlov.arraylist_main;

import ru.academits.orlov.arraylist.CustomArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> integersList = new CustomArrayList<>();
        System.out.println("integersList.add(3): " + integersList.add(3));
        System.out.println("integersList.add(4): " + integersList.add(4));
        System.out.println("integersList.add(2): " + integersList.add(2));
        System.out.println("integersList.add(1): " + integersList.add(1));
        System.out.println("integersList.add(2): " + integersList.add(2));
        System.out.println();

        Collection<Integer> collection = new ArrayList<>();
        collection.add(4);
        collection.add(0);

        System.out.println("integersList: " + integersList);
        System.out.println("collection: " + collection);
        System.out.println();
        System.out.println("integersList.removeAll(collection): " + integersList.removeAll(collection));
        System.out.println("integersList после removeAll(collection): " + integersList);
        System.out.println();
        System.out.println("collection.add(2): " + collection.add(2));
        System.out.println("collection после добавления двойки: " + collection);
        System.out.println();
        System.out.println("integersList.retainAll(collection): " + integersList.retainAll(collection));
        System.out.println("integersList после retainAll(collection): " + integersList);
        System.out.println();
        System.out.println("integersList.add(3): " + integersList.add(3));
        System.out.println("integersList.add(4): " + integersList.add(4));
        System.out.println("integersList.add(1): " + integersList.add(1));
        System.out.println("integersList после добавления трёх элементов: " + integersList);
        System.out.println();
        System.out.println("integersList.set(0, 7): " + integersList.set(0, 7));
        System.out.println("integersList.set(4, 4): " + integersList.set(4, 4));
        System.out.println("integersList после изменения элементов: " + integersList);
        System.out.println();
        System.out.println("integersList size: " + integersList.size());
        System.out.println("integersList contains null: " + integersList.contains(null));
        System.out.println("integersList contains 0: " + integersList.contains(0));
        System.out.println("integersList contains 3: " + integersList.contains(3));
        System.out.println();
        System.out.println("integersList.toArray(): " + Arrays.toString(integersList.toArray()));

        Number[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println("integersList.toArray(numbers): " + Arrays.toString(integersList.toArray(numbers)));
        System.out.println();
        System.out.println("integersList.remove(4): " + integersList.remove(4));
        System.out.println("integersList.remove(0): " + integersList.remove(0));
        System.out.println("integersList после удаления элементов по индексам 0 и 4: " + integersList);
        System.out.println();

        integersList.add(2, 0);

        System.out.println("integersList после добавления 0 по индексу 2: " + integersList);

        integersList.add(4, 1);

        System.out.println("integersList после добавления 1 по индексу 4: " + integersList);

        CustomArrayList<CustomArrayList<Integer>> superIntegersList1 = getCustomArrayLists();
        CustomArrayList<CustomArrayList<Integer>> superIntegersList2 = getCustomArrayLists();

        System.out.println("superIntegersList1 equals superIntegersList2: " + superIntegersList1.equals(superIntegersList2));
        System.out.println("superIntegersList1.hashCode(): " + superIntegersList1.hashCode());
        System.out.println("superIntegersList2.hashCode(): " + superIntegersList2.hashCode());
        System.out.println();

        CustomArrayList<Integer> initialCapacityIntegersList = new CustomArrayList<>(38);

        System.out.println("initialCapacityIntegersList isEmpty: " + initialCapacityIntegersList.isEmpty());
        System.out.println("initialCapacityIntegersList.add(2): " + initialCapacityIntegersList.add(2));
        System.out.println("initialCapacityIntegersList.add(1): " + initialCapacityIntegersList.add(1));

        initialCapacityIntegersList.trimToSize();

        System.out.println("initialCapacityIntegersList isEmpty после добавления элементов: " + initialCapacityIntegersList.isEmpty());
        System.out.println();
        System.out.println("integersList: " + integersList);
        System.out.println("initialCapacityIntegersList: " + initialCapacityIntegersList);
        System.out.println("integersList.containsAll(initialCapacityIntegersList): " + integersList.containsAll(initialCapacityIntegersList));
        System.out.println("initialCapacityIntegersList.add(9): " + initialCapacityIntegersList.add(9));
        System.out.println("integersList.containsAll(initialCapacityIntegersList) после добавления элемента: " + integersList.containsAll(initialCapacityIntegersList));
        System.out.println();

        integersList.clear();

        System.out.println("integersList после clear(): " + integersList);
        System.out.println("Добавление пустой коллекции: " + initialCapacityIntegersList.addAll(integersList));
        System.out.println("Удаление пустой коллекции: " + initialCapacityIntegersList.removeAll(integersList));
        System.out.println();
        System.out.println("integersList.containsAll(initialCapacityIntegersList): " + integersList.containsAll(initialCapacityIntegersList));
        System.out.println("integersList.addAll(initialCapacityIntegersList): " + integersList.addAll(initialCapacityIntegersList));
        System.out.println("integersList после добавления коллекции: " + integersList);
        System.out.println();
        System.out.println("integersList.addAll(1, initialCapacityIntegersList): " + integersList.addAll(1, initialCapacityIntegersList));
        System.out.println("integersList после добавления коллекции по индексу 1: " + integersList);
        System.out.println();
        System.out.println("integersList.get(1): " + integersList.get(1));
        System.out.println("integersList.indexOf(9): " + integersList.indexOf(9));
        System.out.println("integersList.lastIndexOf(9): " + integersList.lastIndexOf(9));
        System.out.println();
        System.out.println("integersList.retainAll(new ArrayList<Integer>()): " + integersList.retainAll(new ArrayList<Integer>()));
        System.out.println("integersList после retainAll пустой коллекции: " + integersList);
        System.out.println("integersList.removeAll(initialCapacityIntegersList): " + integersList.removeAll(initialCapacityIntegersList));
        System.out.println("integersList.retainAll(initialCapacityIntegersList): " + integersList.retainAll(initialCapacityIntegersList));
        System.out.println("integersList: " + integersList);
    }

    private static CustomArrayList<CustomArrayList<Integer>> getCustomArrayLists() {
        CustomArrayList<CustomArrayList<Integer>> superIntegersList = new CustomArrayList<>();

        CustomArrayList<Integer> integersList1 = new CustomArrayList<>();
        integersList1.add(1);
        integersList1.add(2);
        integersList1.add(3);
        superIntegersList.add(integersList1);

        CustomArrayList<Integer> integersList2 = new CustomArrayList<>();
        integersList2.add(4);
        integersList2.add(5);
        integersList2.add(6);
        superIntegersList.add(integersList2);

        return superIntegersList;
    }
}
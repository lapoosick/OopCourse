package ru.academits.orlov.arraylist_main;

import ru.academits.orlov.arraylist.CustomArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> integersList = new CustomArrayList<>();
        integersList.add(3);
        integersList.add(4);
        integersList.add(2);
        integersList.add(1);
        integersList.add(2);

        Collection<Integer> collection = new ArrayList<>();
        collection.add(4);
        collection.add(0);

        System.out.println("integersList: " + integersList);
        System.out.println("collection: " + collection);

        integersList.removeAll(collection);

        System.out.println("integersList после removeAll(collection): " + integersList);

        collection.add(2);

        System.out.println("collection после добавления двойки: " + collection);

        integersList.retainAll(collection);

        System.out.println("integersList после retainAll(collection): " + integersList);

        integersList.add(3);
        integersList.add(4);
        integersList.add(1);

        System.out.println("integersList после добавления трёх элементов: " + integersList);

        integersList.set(0, 7);
        integersList.set(4, 4);

        System.out.println("integersList после изменения элементов: " + integersList);
        System.out.println("integersList size: " + integersList.size());
        System.out.println("integersList contains null: " + integersList.contains(null));
        System.out.println("integersList contains 0: " + integersList.contains(0));
        System.out.println("integersList contains 3: " + integersList.contains(3));
        System.out.println("integersList.toArray(): " + Arrays.toString(integersList.toArray()));

        Number[] numbers = new Number[]{};

        System.out.println("integersList.toArray(numbers): " + Arrays.toString(integersList.toArray(numbers)));

        integersList.remove(4);
        integersList.remove(0);

        System.out.println("integersList после удаления элементов по индексам 0 и 4: " + integersList);

        integersList.add(2, 0);

        System.out.println("integersList после добавления 0 по индексу 2: " + integersList);

        integersList.add(4, 1);

        System.out.println("integersList после добавления 1 по индексу 4: " + integersList);

        CustomArrayList<CustomArrayList<Integer>> superIntegersList1 = getCustomArrayLists();
        CustomArrayList<CustomArrayList<Integer>> superIntegersList2 = getCustomArrayLists();

        System.out.println("superIntegersList1 equals superIntegersList2: " + superIntegersList1.equals(superIntegersList2));
        System.out.println("superIntegersList1.hashCode(): " + superIntegersList1.hashCode());
        System.out.println("superIntegersList2.hashCode(): " + superIntegersList2.hashCode());

        CustomArrayList<Integer> initialCapacityIntegersList = new CustomArrayList<>(38);

        System.out.println("initialCapacityIntegersList isEmpty: " + initialCapacityIntegersList.isEmpty());

        initialCapacityIntegersList.add(2);
        initialCapacityIntegersList.add(1);
        initialCapacityIntegersList.trimToSize();

        System.out.println("initialCapacityIntegersList isEmpty после добавления элементов: " + initialCapacityIntegersList.isEmpty());
        System.out.println("integersList: " + integersList);
        System.out.println("initialCapacityIntegersList: " + initialCapacityIntegersList);
        System.out.println("integersList.containsAll(initialCapacityIntegersList): " + integersList.containsAll(initialCapacityIntegersList));

        initialCapacityIntegersList.add(9);

        System.out.println("integersList.containsAll(initialCapacityIntegersList) после добавления элемента: " + integersList.containsAll(initialCapacityIntegersList));

        integersList.clear();

        System.out.println("Добавление пустой коллекции: " + initialCapacityIntegersList.addAll(integersList));
        System.out.println("Удаление пустой коллекции: " + initialCapacityIntegersList.removeAll(integersList));
        System.out.println("integersList после clear(): " + integersList);
        System.out.println("integersList.containsAll(initialCapacityIntegersList): " + integersList.containsAll(initialCapacityIntegersList));

        integersList.addAll(initialCapacityIntegersList);

        System.out.println("integersList после добавления коллекции: " + integersList);

        integersList.addAll(1, initialCapacityIntegersList);

        System.out.println("integersList после добавления коллекции по индексу 1: " + integersList);
        System.out.println("integersList.get(1): " + integersList.get(1));
        System.out.println("integersList.indexOf(9): " + integersList.indexOf(9));
        System.out.println("integersList.lastIndexOf(9): " + integersList.lastIndexOf(9));

        integersList.retainAll(new ArrayList<Integer>());

        System.out.println("integersList после retainAll пустой коллекции: " + integersList);
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
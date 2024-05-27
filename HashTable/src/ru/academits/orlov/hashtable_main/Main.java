package ru.academits.orlov.hashtable_main;

import ru.academits.orlov.hashtable.CustomHashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CustomHashTable<String> defaultSizeHashTable = new CustomHashTable<>();
        CustomHashTable<String> customSizeHashTable = new CustomHashTable<>(5);

        System.out.println("defaultSizeHashTable.size(): " + defaultSizeHashTable.size());
        System.out.println("customSizeHashTable.size(): " + customSizeHashTable.size());
        System.out.println();
        System.out.println("defaultSizeHashTable.isEmpty(): " + defaultSizeHashTable.isEmpty());
        System.out.println("customSizeHashTable.isEmpty(): " + customSizeHashTable.isEmpty());
        System.out.println();
        System.out.println("defaultSizeHashTable.contains(null): " + defaultSizeHashTable.contains(null));
        System.out.println("defaultSizeHashTable.contains(\"foo\"): " + defaultSizeHashTable.contains("foo"));
        System.out.println();
        System.out.println("customSizeHashTable.contains(null): " + customSizeHashTable.contains(null));
        System.out.println("customSizeHashTable.contains(\"foo\"): " + customSizeHashTable.contains("foo"));
        System.out.println();
        System.out.println("defaultSizeHashTable.add(\"foo\"): " + defaultSizeHashTable.add("foo"));
        System.out.println("defaultSizeHashTable.add(\"foo\"): " + defaultSizeHashTable.add("foo"));
        System.out.println("customSizeHashTable.add(\"foo\"): " + customSizeHashTable.add("foo"));
        System.out.println("customSizeHashTable.add(\"foo\"): " + customSizeHashTable.add("bar"));
        System.out.println();
        System.out.println("defaultSizeHashTable: " + defaultSizeHashTable);
        System.out.println("customSizeHashTable: " + customSizeHashTable);
        System.out.println();
        System.out.println("defaultSizeHashTable.add(null): " + defaultSizeHashTable.add(null));
        System.out.println("customSizeHashTable.add(null): " + customSizeHashTable.add(null));
        System.out.println();
        System.out.println("defaultSizeHashTable: " + defaultSizeHashTable);
        System.out.println("customSizeHashTable: " + customSizeHashTable);
        System.out.println();
        System.out.println("defaultSizeHashTable.toArray(): " + Arrays.toString(defaultSizeHashTable.toArray()));
        System.out.println("customSizeHashTable.toArray(): " + Arrays.toString(customSizeHashTable.toArray()));
        System.out.println("emptyHashTable.toArray(): " + Arrays.toString(new CustomHashTable<>(1).toArray()));
        System.out.println();
        System.out.println("defaultSizeHashTable.containsAll(customSizeHashTable): " + defaultSizeHashTable.containsAll(customSizeHashTable));
        System.out.println();

        CustomHashTable<String> customSizeHashTableCopy = new CustomHashTable<>();

        System.out.println("customSizeHashTableCopy.addAll(customSizeHashTable): " + customSizeHashTableCopy.addAll(customSizeHashTable));
        System.out.println("customSizeHashTableCopy: " + customSizeHashTableCopy);
        System.out.println("customSizeHashTable: " + customSizeHashTable);
        System.out.println();
        System.out.println("customSizeHashTable.containsAll(customSizeHashTableCopy): " + customSizeHashTable.containsAll(customSizeHashTableCopy));
        System.out.println();
        System.out.println("customSizeHashTable.contains(\"baz\"): " + customSizeHashTable.contains("baz"));
        System.out.println("customSizeHashTable.contains(\"bar\"): " + customSizeHashTable.contains("bar"));
        System.out.println();
        System.out.println("defaultSizeHashTable.addAll(customSizeHashTable): " + defaultSizeHashTable.addAll(customSizeHashTable));
        System.out.println("defaultSizeHashTable.addAll(new CustomHashTable<>(0)): " + defaultSizeHashTable.addAll(new CustomHashTable<>(1)));
        System.out.println("defaultSizeHashTable: " + defaultSizeHashTable);
        System.out.println();
        System.out.println("customSizeHashTable.remove(\"baz\"): " + customSizeHashTable.remove("baz"));
        System.out.println("customSizeHashTable.remove(\"bar\"): " + customSizeHashTable.remove("bar"));
        System.out.println();
        System.out.println("customSizeHashTable: " + customSizeHashTable);
        System.out.println();
        System.out.println("defaultSizeHashTable: " + defaultSizeHashTable);
        System.out.println("defaultSizeHashTable.retainAll(customSizeHashTable): " + defaultSizeHashTable.retainAll(customSizeHashTable));
        System.out.println("defaultSizeHashTable.size(): " + defaultSizeHashTable.size());
        System.out.println("defaultSizeHashTable: " + defaultSizeHashTable);
        System.out.println("customSizeHashTable: " + customSizeHashTable);
        System.out.println("defaultSizeHashTable.removeAll(customSizeHashTable): " + defaultSizeHashTable.removeAll(customSizeHashTable));
        System.out.println("defaultSizeHashTable.size(): " + defaultSizeHashTable.size());
        System.out.println("defaultSizeHashTable: " + defaultSizeHashTable);
        System.out.println("defaultSizeHashTable.removeAll(customSizeHashTable): " + defaultSizeHashTable.removeAll(customSizeHashTable));
        System.out.println("defaultSizeHashTable.retainAll(customSizeHashTable): " + defaultSizeHashTable.retainAll(customSizeHashTable));
        System.out.println();

        CustomHashTable<Integer> integers = new CustomHashTable<>();
        integers.add(3);
        integers.add(4);
        integers.add(2);
        integers.add(1);
        integers.add(2);

        Number[] numbers1 = new Number[1];
        Number[] numbers2 = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        System.out.println("integers: " + integers);
        System.out.println("numbers1: " + Arrays.toString(numbers1));
        System.out.println("numbers2: " + Arrays.toString(numbers2));
        System.out.println("integers.toArray(): " + Arrays.toString(integers.toArray()));
        System.out.println("integers.toArray(numbers1): " + Arrays.toString(integers.toArray(numbers1)));
        System.out.println("integers.toArray(numbers2): " + Arrays.toString(integers.toArray(numbers2)));
        System.out.println();
        System.out.println("customSizeHashTable: " + customSizeHashTable);

        customSizeHashTable.clear();

        System.out.println("customSizeHashTable после clear(): " + customSizeHashTable);
        System.out.println("customSizeHashTable.size(): " + customSizeHashTable.size());
        System.out.println("customSizeHashTable.add(\"foo\"): " + customSizeHashTable.add("foo"));
        System.out.println("customSizeHashTable: " + customSizeHashTable);
    }
}
package ru.academits.orlov.hashtable_main;

import ru.academits.orlov.hashtable.CustomHashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CustomHashTable<String> defaultSizeTable = new CustomHashTable<>();
        CustomHashTable<String> customSizeTable = new CustomHashTable<>(5);

        System.out.println("defaultSizeTable.size(): " + defaultSizeTable.size());
        System.out.println("customSizeTable.size(): " + customSizeTable.size());
        System.out.println();
        System.out.println("defaultSizeTable.isEmpty(): " + defaultSizeTable.isEmpty());
        System.out.println("customSizeTable.isEmpty(): " + customSizeTable.isEmpty());
        System.out.println();
        System.out.println("defaultSizeTable.contains(null): " + defaultSizeTable.contains(null));
        System.out.println("defaultSizeTable.contains(\"foo\"): " + defaultSizeTable.contains("foo"));
        System.out.println();
        System.out.println("customSizeTable.contains(null): " + customSizeTable.contains(null));
        System.out.println("customSizeTable.contains(\"foo\"): " + customSizeTable.contains("foo"));
        System.out.println();
        System.out.println("defaultSizeTable.add(\"foo\"): " + defaultSizeTable.add("foo"));
        System.out.println("defaultSizeTable.add(\"foo\"): " + defaultSizeTable.add("foo"));
        System.out.println("customSizeTable.add(\"foo\"): " + customSizeTable.add("foo"));
        System.out.println("customSizeTable.add(\"foo\"): " + customSizeTable.add("bar"));
        System.out.println();
        System.out.println("defaultSizeTable: " + defaultSizeTable);
        System.out.println("customSizeTable: " + customSizeTable);
        System.out.println();
        System.out.println("defaultSizeTable.add(null): " + defaultSizeTable.add(null));
        System.out.println("customSizeTable.add(null): " + customSizeTable.add(null));
        System.out.println();
        System.out.println("defaultSizeTable: " + defaultSizeTable);
        System.out.println("customSizeTable: " + customSizeTable);
        System.out.println();
        System.out.println("defaultSizeTable.toArray(): " + Arrays.toString(defaultSizeTable.toArray()));
        System.out.println("customSizeTable.toArray(): " + Arrays.toString(customSizeTable.toArray()));
        System.out.println("emptyHashTable.toArray(): " + Arrays.toString(new CustomHashTable<>(0).toArray()));
        System.out.println();
        System.out.println("defaultSizeTable.containsAll(customSizeTable): " + defaultSizeTable.containsAll(customSizeTable));
        System.out.println();

        CustomHashTable<String> customSizeTableCopy = new CustomHashTable<>();
        customSizeTableCopy.addAll(customSizeTable);

        System.out.println("customSizeTableCopy: " + customSizeTableCopy);
        System.out.println("customSizeTable: " + customSizeTable);
        System.out.println("customSizeTableCopy.equals(customSizeTable): " + customSizeTableCopy.equals(customSizeTable));
        System.out.println("customSizeTable.equals(customSizeTableCopy): " + customSizeTable.equals(customSizeTableCopy));
        System.out.println();
        System.out.println("customSizeTable.hashCode(): " + customSizeTable.hashCode());
        System.out.println("customSizeTableCopy.hashCode(): " + customSizeTableCopy.hashCode());
        System.out.println();
        System.out.println("customSizeTable.containsAll(customSizeTableCopy): " + customSizeTable.containsAll(customSizeTableCopy));
        System.out.println();
        System.out.println("customSizeTable.contains(\"baz\"): " + customSizeTable.contains("baz"));
        System.out.println();
        System.out.println("defaultSizeTable.addAll(customSizeTable): " + defaultSizeTable.addAll(customSizeTable));
        System.out.println("defaultSizeTable.addAll(new CustomHashTable<>(0)): " + defaultSizeTable.addAll(new CustomHashTable<>(0)));
        System.out.println("defaultSizeTable: " + defaultSizeTable);
        System.out.println();
        System.out.println("customSizeTable.remove(\"baz\"): " + customSizeTable.remove("baz"));
        System.out.println("customSizeTable.remove(\"bar\"): " + customSizeTable.remove("bar"));
        System.out.println();
        System.out.println("customSizeTable: " + customSizeTable);
        System.out.println();
        System.out.println("defaultSizeTable.retainAll(customSizeTable): " + defaultSizeTable.retainAll(customSizeTable));
        System.out.println("defaultSizeTable: " + defaultSizeTable);
        System.out.println("defaultSizeTable.removeAll(customSizeTable): " + defaultSizeTable.removeAll(customSizeTable));
        System.out.println("defaultSizeTable: " + defaultSizeTable);
        System.out.println();

        CustomHashTable<Integer> integers = new CustomHashTable<>();
        integers.add(3);
        integers.add(4);
        integers.add(2);
        integers.add(1);
        integers.add(2);

        Number[] numbers1 = new Number[1];
        Number[] numbers2 = new Number[100];

        System.out.println("integers: " + integers);
        System.out.println("integers.toArray(): " + Arrays.toString(integers.toArray()));
        System.out.println("integers.toArray(numbers1): " + Arrays.toString(integers.toArray(numbers1)));
        System.out.println("integers.toArray(numbers2): " + Arrays.toString(integers.toArray(numbers2)));
    }
}
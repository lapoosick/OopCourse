package ru.academits.orlov.arraylist_main;

import ru.academits.orlov.arraylist.CustomArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<String> stringsCustomArrayList1 = new CustomArrayList<>(5);

        System.out.println("stringsMyArrayList1.size(): " + stringsCustomArrayList1.size());
        System.out.println("stringsMyArrayList1: " + stringsCustomArrayList1);

        stringsCustomArrayList1.add("foo");

        System.out.println("stringsMyArrayList1: " + stringsCustomArrayList1);
        System.out.println("stringsMyArrayList1.getFirst(): " + stringsCustomArrayList1.getFirst());

        stringsCustomArrayList1.add("bar");

        System.out.println("stringsMyArrayList1: " + stringsCustomArrayList1);
        System.out.println("stringsMyArrayList1.get(1): " + stringsCustomArrayList1.get(1));
        System.out.println("stringsMyArrayList1: " + stringsCustomArrayList1);
        System.out.println("stringsMyArrayList1.contains(null): " + stringsCustomArrayList1.contains(null));

        stringsCustomArrayList1.trimToSize();

        System.out.println("stringsMyArrayList1: " + stringsCustomArrayList1);
        System.out.println("stringsMyArrayList1.contains(\"foo\"): " + stringsCustomArrayList1.contains("foo"));
        System.out.println("stringsMyArrayList1.contains(\"bar\"): " + stringsCustomArrayList1.contains("bar"));
        System.out.println("stringsMyArrayList1.contains(\"baz\"): " + stringsCustomArrayList1.contains("baz"));
        System.out.println("stringsMyArrayList1.contains(null): " + stringsCustomArrayList1.contains(null));
        System.out.println("stringsMyArrayList1: " + stringsCustomArrayList1);

        stringsCustomArrayList1.add(null);

        System.out.println("stringsMyArrayList1: " + stringsCustomArrayList1);

        Object[] objects = stringsCustomArrayList1.toArray();

        System.out.println("Arrays.toString(objects): " + Arrays.toString(objects));

        CustomArrayList<String> stringsCustomArrayList2 = new CustomArrayList<>();
        stringsCustomArrayList2.add("foo");
        stringsCustomArrayList2.add("bar");
        stringsCustomArrayList2.add(null);

        System.out.println("stringsMyArrayList1.containsAll(stringsMyArrayList2): " + stringsCustomArrayList1.containsAll(stringsCustomArrayList2));

        CustomArrayList<String> stringsCustomArrayList3 = new CustomArrayList<>();
        stringsCustomArrayList3.add("foo");
        stringsCustomArrayList3.add("baz");

        System.out.println("stringsMyArrayList1.containsAll(stringsMyArrayList3): " + stringsCustomArrayList1.containsAll(stringsCustomArrayList3));

        stringsCustomArrayList1.add(1, "baz");

        System.out.println("stringsMyArrayList1: " + stringsCustomArrayList1);
        System.out.println("stringsMyArrayList1.indexOf(\"baz\"): " + stringsCustomArrayList1.indexOf("baz"));

        stringsCustomArrayList1.add("baz");
        stringsCustomArrayList1.add("buz");

        System.out.println("stringsMyArrayList1: " + stringsCustomArrayList1);
        System.out.println("stringsMyArrayList1.indexOf(\"baz\"): " + stringsCustomArrayList1.indexOf("baz"));
        System.out.println("stringsMyArrayList1.lastIndexOf(\"baz\"): " + stringsCustomArrayList1.lastIndexOf("baz"));
        System.out.println("stringsMyArrayList1.lastIndexOf(\"ban\"): " + stringsCustomArrayList1.lastIndexOf("ban"));
        System.out.println("stringsMyArrayList1: " + stringsCustomArrayList1);
        System.out.println("stringsMyArrayList2: " + stringsCustomArrayList2);

        System.out.println("stringsMyArrayList1.addAll(5, stringsMyArrayList2): " + stringsCustomArrayList1.addAll(5, stringsCustomArrayList2));
        System.out.println("stringsMyArrayList1.addAll(new MyArrayList<>()): " + stringsCustomArrayList1.addAll(new CustomArrayList<>()));
        System.out.println("stringsMyArrayList1.addAll(null): " + stringsCustomArrayList1.addAll(null));
        System.out.println("stringsMyArrayList1: " + stringsCustomArrayList1);

        CustomArrayList<String> strings = new CustomArrayList<>();
        strings.add("foo");
        strings.add("foo");
        strings.add("bar");
        strings.add(null);
        strings.add("baz");
        strings.add(null);
        strings.add("biz");

        System.out.println("strings: " + strings);

        CustomArrayList<String> strings1 = new CustomArrayList<>();
        strings1.add("foo");
        strings1.add("baz");
        strings1.add("bar");
        strings1.add(null);

        System.out.println("strings1: " + strings1);

        strings.removeAll(strings1);

        System.out.println("strings: " + strings);

        CustomArrayList<String> strings2 = new CustomArrayList<>();
        strings2.add("foo");
        strings2.add("baz");
        strings2.add("bar");
        strings2.add(null);

        System.out.println("stringsCustomArrayList1.hashCode(): " + stringsCustomArrayList1.hashCode());
        System.out.println("stringsCustomArrayList2.hashCode(): " + stringsCustomArrayList2.hashCode());
        System.out.println("stringsCustomArrayList3.hashCode(): " + stringsCustomArrayList3.hashCode());
        System.out.println("strings.hashCode(): " + strings.hashCode());
        System.out.println("strings1.hashCode(): " + strings1.hashCode());
        System.out.println("strings2.hashCode(): " + strings2.hashCode());
        System.out.println("strings1.equals(strings2): " + strings1.equals(strings2));
        System.out.println("strings1.equals(strings): " + strings1.equals(strings));
    }
}
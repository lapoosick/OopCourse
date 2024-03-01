package ru.academits.orlov.arraylisthome_main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/ru/academits/orlov/arraylisthome_main/input.csv"))) {
            while (scanner.hasNextLine()) {
                stringArrayList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Строки, прочитанные из файла:");

        for (String s : stringArrayList) {
            System.out.println(s);
        }

        System.out.println();

        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(8, 45, 15, 34, 2387, 45, 760, 15, 103, -3, 85, -96, -82, 0));

        System.out.println("Список целых чисел: " + integerArrayList);

        for (int i = 0; i < integerArrayList.size(); ) {
            int integer = integerArrayList.get(i);

            if (integer % 2 == 0) {
                integerArrayList.remove(Integer.valueOf(integer));
            } else {
                i++;
            }
        }

        System.out.println("Список целых чисел после удаления чётных чисел: " + integerArrayList);

        ArrayList<Integer> uniqueIntegersArrayList = new ArrayList<>();

        for (int integer : integerArrayList) {
            if (!uniqueIntegersArrayList.contains(integer)) {
                uniqueIntegersArrayList.add(integer);
            }
        }

        System.out.println("Список целых чисел без повторений: " + uniqueIntegersArrayList);
    }
}
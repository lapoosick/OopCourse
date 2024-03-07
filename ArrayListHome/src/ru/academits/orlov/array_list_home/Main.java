package ru.academits.orlov.array_list_home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String PATH = "ArrayListHome/src/ru/academits/orlov/arrayListHome/input.csv";
    private static final ArrayList<Integer> INTEGERS_LIST = new ArrayList<>(Arrays.asList(8, 45, 15, 9898, 34, 2387, 45, 760, 15, 2387, 103, -3, 85, -96, -82, 0));

    public static void main(String[] args) {
        readLinesToList(PATH);

        System.out.println();

        deleteEvenNumbersFromList(INTEGERS_LIST);

        System.out.println();

        System.out.println("Список целых чисел после удаления дубликатов: " + getUniqueNumbersList(INTEGERS_LIST));
    }

    private static void readLinesToList(String path) {
        ArrayList<String> linesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while (bufferedReader.ready()) {
                linesList.add(bufferedReader.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + path + " не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }

        System.out.println("Строки, прочитанные из файла:");

        for (String line : linesList) {
            System.out.println(line);
        }
    }

    private static void deleteEvenNumbersFromList(List<Integer> integersList) {
        System.out.println("Список целых чисел: " + integersList);

        for (int i = 0; i < integersList.size(); ) {
            if (integersList.get(i) % 2 == 0) {
                integersList.remove(i);
            } else {
                i++;
            }
        }

        System.out.println("Список целых чисел после удаления чётных чисел: " + integersList);
    }

    private static List<Integer> getUniqueNumbersList(List<Integer> integersList) {
        ArrayList<Integer> uniqueIntegersList = new ArrayList<>(integersList.size());

        for (Integer integer : integersList) {
            if (!uniqueIntegersList.contains(integer)) {
                uniqueIntegersList.add(integer);
            }
        }

        return uniqueIntegersList;
    }
}
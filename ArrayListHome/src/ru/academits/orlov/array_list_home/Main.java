package ru.academits.orlov.array_list_home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String path = "ArrayListHome/src/ru/academits/orlov/array_list_home/input.csv";
            List<String> linesList = getFileLines(path);

            System.out.println("Строки из файла:");

            for (String line : linesList) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл со строками не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла со строками. " + e.getMessage());
        }

        System.out.println();

        List<Integer> integersList = new ArrayList<>(Arrays.asList(8, 5, 1, 5, 7, 2));

        System.out.println("Список целых чисел: " + integersList);

        deleteEvenNumbers(integersList);

        System.out.println("Список целых чисел после удаления чётных чисел: " + integersList);
        System.out.println("Список целых чисел без дубликатов: " + getUniqueElementsList(integersList));
    }

    private static List<String> getFileLines(String path) throws IOException {
        List<String> linesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                linesList.add(line);
                line = bufferedReader.readLine();
            }
        }

        return linesList;
    }

    private static void deleteEvenNumbers(List<Integer> integersList) {
        for (int i = 0; i < integersList.size(); ) {
            if (integersList.get(i) % 2 == 0) {
                integersList.remove(i);
            } else {
                i++;
            }
        }
    }

    private static <T> List<T> getUniqueElementsList(List<T> elementsList) {
        List<T> uniqueElementsList = new ArrayList<>(elementsList.size());

        for (T e : elementsList) {
            if (!uniqueElementsList.contains(e)) {
                uniqueElementsList.add(e);
            }
        }

        return uniqueElementsList;
    }
}
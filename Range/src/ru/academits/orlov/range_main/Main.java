package ru.academits.orlov.range_main;

import ru.academits.orlov.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начало интервала: ");
        double from = scanner.nextDouble();

        System.out.print("Введите конец интервала: ");
        double to = scanner.nextDouble();

        System.out.print("Введите число для проверки: ");
        double number = scanner.nextDouble();

        Range range = new Range(from, to);

        System.out.printf("Длина интервала равна: %f.%n", range.getLength());
        System.out.println(range.isInside(number) ? "Ваше число в интервале." : "Ваше число вне интервала.");

        range.setFrom(1.6574);
        range.setTo(6.28749);

        System.out.printf("Новые границы интервала: %f и %f.%n", range.getFrom(), range.getTo());
        System.out.printf("Новая длина интервала: %f.", range.getLength());
        System.out.println();

        System.out.print("Укажите начало первого интервала: ");
        double from1 = scanner.nextDouble();

        System.out.print("Укажите конец первого интервала: ");
        double to1 = scanner.nextDouble();

        System.out.print("Укажите начало второго интервала: ");
        double from2 = scanner.nextDouble();

        System.out.print("Укажите конец второго интервала: ");
        double to2 = scanner.nextDouble();

        Range range1 = new Range(from1, to1);
        Range range2 = new Range(from2, to2);

        Range intersection = range1.getIntersection(range2);

        if (intersection == null) {
            System.out.println("Интервалы не пересекаются.");
        } else {
            System.out.println("Интервал-пересечение: " + intersection);
        }

        Range[] union = range1.getUnion(range2);

        System.out.println("Результат объединения двух интервалов: " + Arrays.toString(union));

        Range[] difference = range1.getDifference(range2);

        System.out.println("Разность двух интервалов: " + Arrays.toString(difference));
    }
}
package ru.academits.orlov.range_main;

import ru.academits.orlov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начало диапазона: ");
        double from = scanner.nextDouble();

        System.out.print("Введите конец диапазона: ");
        double to = scanner.nextDouble();

        System.out.print("Введите число для проверки: ");
        double number = scanner.nextDouble();

        Range range = new Range(from, to);

        System.out.printf("Длина диапазона равна: %f.%n", range.getLength());
        System.out.println(range.isInside(number) ? "Ваше число в диапазоне." : "Ваше число вне диапазона.");

        range.setFrom(1.6574);
        range.setTo(6.28749);

        System.out.printf("Новые границы диапазона: %f и %f.%n", range.getFrom(), range.getTo());
        System.out.printf("Новая длина диапазона: %f.", range.getLength());
        System.out.println();

        System.out.print("Укажите начало первого интервала: ");
        double fromA = scanner.nextDouble();

        System.out.print("Укажите конец первого интервала: ");
        double toA = scanner.nextDouble();

        System.out.print("Укажите начало второго интервала: ");
        double fromB = scanner.nextDouble();

        System.out.print("Укажите конец второго интервала: ");
        double toB = scanner.nextDouble();

        Range rangeA = new Range(fromA, toA);
        Range rangeB = new Range(fromB, toB);

        Range rangesIntersection = rangeA.getRangesIntersection(rangeB);

        if (rangesIntersection == null) {
            System.out.println("Интервалы не пересекаются.");
        } else {
            System.out.println("Интервал-пересечение: (" + rangesIntersection.getFrom() + ", " + rangesIntersection.getTo() + ")");
        }

        Range[] rangesUnion = rangeA.getRangesUnion(rangeB);

        System.out.print("Результат объединения двух интервалов: (" + rangesUnion[0].getFrom() + ", " + rangesUnion[0].getTo() + ")");

        if (rangesUnion.length > 1) {
            System.out.println(" + (" + rangesUnion[1].getFrom() + ", " + rangesUnion[1].getTo() + ")");
        } else {
            System.out.println();
        }

        Range[] rangeSubtraction = rangeA.getRangesSubtraction(rangeB);

        if (rangeSubtraction == null) {
            System.out.println("Разностью двух интервалов является пустое множество.");

            return;
        }

        System.out.print("Разность двух интервалов: (" + rangeSubtraction[0].getFrom() + ", " + rangeSubtraction[0].getTo() + ")");

        if (rangeSubtraction.length > 1) {
            System.out.println(" + (" + rangeSubtraction[1].getFrom() + ", " + rangeSubtraction[1].getTo() + ")");
        }
    }
}

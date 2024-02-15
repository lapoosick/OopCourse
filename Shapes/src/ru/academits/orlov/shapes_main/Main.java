package ru.academits.orlov.shapes_main;

import ru.academits.orlov.shapes_comparators.ShapeAreaComparator;
import ru.academits.orlov.shapes_comparators.ShapePerimeterComparator;
import ru.academits.orlov.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(3.14), new Circle(1.3),
                new Rectangle(3.24, 8.09), new Rectangle(16.3, 0.87),
                new Square(3.67), new Square(9.08),
                new Triangle(0, 0, -2.43, -6.7, 2.43, 6.7),
                new Triangle(-3.1, -6.3, 0, 1.7, 6.2, -2.6)
        };

        Shape maxAreaShape = getMaxAreaShape(shapes);

        System.out.println("Фигура с максимальной площадью - это " + maxAreaShape.getClass().getSimpleName());
        System.out.println("Её площадь: " + maxAreaShape.getArea());
        System.out.println("Её периметр: " + maxAreaShape.getPerimeter());
        System.out.println("Её высота: " + maxAreaShape.getHeight());
        System.out.println("Её ширина: " + maxAreaShape.getWidth());
        System.out.println();

        Shape secondPerimeterShape = getSecondPerimeterShape(shapes);

        System.out.println("Фигура со вторым по величине периметром - это " + secondPerimeterShape.getClass().getSimpleName());
        System.out.println("Её площадь: " + secondPerimeterShape.getArea());
        System.out.println("Её периметр: " + secondPerimeterShape.getPerimeter());
        System.out.println("Её высота: " + secondPerimeterShape.getHeight());
        System.out.println("Её ширина: " + secondPerimeterShape.getWidth());
    }

    private static Shape getMaxAreaShape(Shape[] shapes) {
        if (shapes.length < 1) {
            throw new IllegalArgumentException("Переданный массив не содержит ни одной фигуры.");
        }

        Arrays.sort(shapes, new ShapeAreaComparator());

        return shapes[shapes.length - 1];
    }

    private static Shape getSecondPerimeterShape(Shape[] shapes) {
        if (shapes.length <= 1) {
            throw new IllegalArgumentException("Переданный массив не содержит двух фигур.");
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());

        return shapes[shapes.length - 2];
    }
}
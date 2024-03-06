package ru.academits.orlov.vector_main;

import ru.academits.orlov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector0 = new Vector(3);

        System.out.println("vector0: " + vector0);
        System.out.println("Размерность vector0: " + vector0.getSize());
        System.out.println();

        Vector vector1 = new Vector(vector0);

        System.out.println("vector1: " + vector1);
        System.out.println("Размерность vector1: " + vector1.getSize());
        System.out.println();

        Vector vector2 = new Vector(new double[]{2, 2.2, 8, 4.4});

        System.out.println("vector2: " + vector2);
        System.out.println("Размерность vector2: " + vector2.getSize());
        System.out.println();

        Vector vector3 = new Vector(7, new double[]{0.1, 0.2, 0.4, 0.4, 0.5});

        System.out.println("vector3: " + vector3);
        System.out.println("Размерность vector3: " + vector3.getSize());
        System.out.println();

        Vector vector4 = new Vector(3, new double[]{10, 22, 33, 44, 58});

        System.out.println("vector4: " + vector4);
        System.out.println("Размерность vector4: " + vector4.getSize());
        System.out.println();

        System.out.println("Прибавление:");

        System.out.println(vector1 + " + " + vector2);
        vector1.add(vector2);
        System.out.println("Результат: " + vector1);
        System.out.println();

        System.out.println(vector2 + " + " + vector4);
        vector2.add(vector4);
        System.out.println("Результат: " + vector2);
        System.out.println();

        System.out.println(vector2 + " + " + vector2);
        vector2.add(vector2);
        System.out.println("Результат: " + vector2);
        System.out.println();

        System.out.println("Вычитание:");

        System.out.println(vector1 + " - " + vector3);
        vector1.subtract(vector3);
        System.out.println("Результат: " + vector1);
        System.out.println();

        System.out.println(vector3 + " - " + vector4);
        vector3.subtract(vector4);
        System.out.println("Результат: " + vector3);
        System.out.println();

        System.out.println(vector3 + " - " + vector3);
        vector3.subtract(vector3);
        System.out.println("Результат: " + vector3);
        System.out.println();

        System.out.println("Умножение на скаляр:");
        System.out.println(vector4 + " * 2");
        vector4.multiplyByScalar(2);
        System.out.println("Результат: " + vector4);
        System.out.println();

        System.out.println("Разворот вектора:");
        System.out.println(vector4);
        vector4.reverse();
        System.out.println("Результат: " + vector4);
        System.out.println();

        System.out.println("Получение длины вектора");
        System.out.println("Длина vector4: " + vector4.getLength());
        System.out.println("Значение по индексу 1: " + vector4.getComponent(1));
        System.out.println();

        System.out.println("Установка значения компоненты 28 по индексу 1:");
        System.out.println("vector4: " + vector4);
        vector4.setComponent(1, 28);
        System.out.println("Результат: " + vector4);
        System.out.println();

        Vector vector5 = new Vector(new double[]{24, 48.4, 82, 8.8});

        System.out.println("vector1.equals(vector2): " + vector1.equals(vector2));
        System.out.println("vector2.equals(vector1): " + vector2.equals(vector1));
        System.out.println("vector2.equals(vector5): " + vector2.equals(vector5));
        System.out.println("vector5.equals(vector2): " + vector5.equals(vector2));
        System.out.println();
        System.out.println("vector1.hashCode(): " + vector1.hashCode());
        System.out.println("vector2.hashCode(): " + vector2.hashCode());
        System.out.println("vector5.hashCode(): " + vector5.hashCode());
        System.out.println();

        System.out.println("Статические методы.");
        System.out.println("Сложение:");
        System.out.println(vector2 + " + " + vector4 + " = " + Vector.getSum(vector2, vector4));
        System.out.println(vector4 + " + " + vector2 + " = " + Vector.getSum(vector4, vector2));
        System.out.println(vector1 + " + " + vector3 + " = " + Vector.getSum(vector1, vector3));
        System.out.println(vector3 + " + " + vector1 + " = " + Vector.getSum(vector3, vector1));
        System.out.println();

        System.out.println("Вычитание:");
        System.out.println(vector2 + " - " + vector4 + " = " + Vector.getDifference(vector2, vector4));
        System.out.println(vector4 + " - " + vector2 + " = " + Vector.getDifference(vector4, vector2));
        System.out.println(vector1 + " - " + vector3 + " = " + Vector.getDifference(vector1, vector3));
        System.out.println(vector3 + " - " + vector1 + " = " + Vector.getDifference(vector3, vector1));
        System.out.println();

        System.out.println("Скалярное произведение:");
        System.out.println(vector1 + " * " + vector2 + " = " + Vector.getScalarProduct(vector1, vector2));
        System.out.println(vector2 + " * " + vector1 + " = " + Vector.getScalarProduct(vector2, vector1));
        System.out.println(vector4 + " * " + vector4 + " = " + Vector.getScalarProduct(vector4, vector4));
    }
}

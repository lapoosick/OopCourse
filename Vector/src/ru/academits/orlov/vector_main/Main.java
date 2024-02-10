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

        Vector vector3 = new Vector(new double[]{0.1, 0.2, 0.4, 0.4}, 7);

        System.out.println("vector3: " + vector3);
        System.out.println("Размерность vector3: " + vector3.getSize());
        System.out.println();

        Vector vector4 = new Vector(new double[]{10, 22, 33, 44, 58}, 3);

        System.out.println("vector4: " + vector4);
        System.out.println("Размерность vector4: " + vector4.getSize());
        System.out.println();

        System.out.println("Прибавление:");

        System.out.println(vector1 + " + " + vector2);
        vector1.addVector(vector2);
        System.out.println("Результат: " + vector1);
        System.out.println();

        System.out.println(vector2 + " + " + vector4);
        vector2.addVector(vector4);
        System.out.println("Результат: " + vector2);
        System.out.println();

        System.out.println(vector2 + " + " + vector2);
        vector2.addVector(vector2);
        System.out.println("Результат: " + vector2);
        System.out.println();

        System.out.println("Вычитание:");

        System.out.println(vector1 + " - " + vector3);
        vector1.subtractVector(vector3);
        System.out.println("Результат: " + vector1);
        System.out.println();

        System.out.println(vector3 + " - " + vector4);
        vector3.subtractVector(vector4);
        System.out.println("Результат: " + vector3);
        System.out.println();

        System.out.println(vector3 + " - " + vector3);
        vector3.subtractVector(vector3);
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

        System.out.println("Установка значения компоненты 23.6 по индексу 1:");
        System.out.println("vector4: " + vector4);
        vector4.setComponent(28, 1);
        System.out.println("Результат: " + vector4);
        System.out.println();

        Vector vector5 = new Vector(new double[]{24, 48.8, 72.6, 8.8});
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
        System.out.println(vector2 + " + " + vector4 + " = " + Vector.addVectors(vector2, vector4));
        System.out.println(vector4 + " + " + vector2 + " = " + Vector.addVectors(vector4, vector2));
        System.out.println(vector1 + " + " + vector3 + " = " + Vector.addVectors(vector1, vector3));
        System.out.println(vector3 + " + " + vector1 + " = " + Vector.addVectors(vector3, vector1));
        System.out.println();

        System.out.println("Вычитание:");
        System.out.println(vector2 + " - " + vector4 + " = " + Vector.subtractVectors(vector2, vector4));
        System.out.println(vector4 + " - " + vector2 + " = " + Vector.subtractVectors(vector4, vector2));
        System.out.println(vector1 + " - " + vector3 + " = " + Vector.subtractVectors(vector1, vector3));
        System.out.println(vector3 + " - " + vector1 + " = " + Vector.subtractVectors(vector3, vector1));
        System.out.println();

        System.out.println("Скалярное произведение:");
        System.out.println(vector1 + " * " + vector2 + " = " + Vector.multiplyVectors(vector1, vector2));
        System.out.println(vector2 + " * " + vector1 + " = " + Vector.multiplyVectors(vector2, vector1));
        System.out.println(vector4 + " * " + vector4 + " = " + Vector.multiplyVectors(vector4, vector4));
    }
}

package ru.academits.orlov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0. Передано значение: " + size);
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Вектор не может иметь размер 0. Передан массив нулевой длины.");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0. Передано значение: " + size);
        }

        if (components.length == 0) {
            throw new IllegalArgumentException("Вектор не может иметь размер 0. Передан массив нулевой длины.");
        }

        this.components = Arrays.copyOf(components, size);
    }

    public int getSize() {
        return components.length;
    }


    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }


    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double squaresSum = 0;

        for (double component : components) {
            squaresSum += component * component;
        }

        return Math.sqrt(squaresSum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размерности вектора. " +
                    "Допустимые значения: [0; " + (components.length - 1) + ']');
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размерности вектора. " +
                    "Допустимые значения: [0; " + (components.length - 1) + ']');
        }

        components[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector v = (Vector) o;

        return Arrays.equals(components, v.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        int lastComponentIndex = components.length - 1;

        for (int i = 0; i < lastComponentIndex; i++) {
            stringBuilder.append(components[i]).append(", ");
        }

        stringBuilder.append(components[lastComponentIndex]).append('}');

        return stringBuilder.toString();
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector sumVector = new Vector(vector1);
        sumVector.add(vector2);

        return sumVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector differenceVector = new Vector(vector1);
        differenceVector.subtract(vector2);

        return differenceVector;
    }

    public static double multiplyByScalar(Vector vector1, Vector vector2) {
        int minVectorSize = Math.min(vector1.components.length, vector2.components.length);

        double product = 0;

        for (int i = 0; i < minVectorSize; i++) {
            product += vector1.components[i] * vector2.components[i];
        }

        return product;
    }
}
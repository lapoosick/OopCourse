package ru.academits.orlov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;
    private int size;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Недопустимая размерность вектора.");
        }

        this.size = size;
        components = new double[size];
    }

    public Vector(Vector vector) {
        this.components = vector.components;
        this.size = vector.size;
    }

    public Vector(double[] components) {
        if (components == null || components.length < 1) {
            throw new IllegalArgumentException("Недопустимая размерность вектора.");
        }

        this.components = components;
        size = components.length;
    }

    public Vector(double[] components, int size) {
        if (components == null || components.length < 1 || size <= 0) {
            throw new IllegalArgumentException("Недопустимая размерность вектора.");
        }

        this.components = Arrays.copyOf(components, size);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void add(Vector vector) {
        double[] vectorComponents = vector.components;
        int vectorSize = vector.size;

        if (size == vectorSize) {
            for (int i = 0; i < size; i++) {
                components[i] += vectorComponents[i];
            }

            return;
        }

        double[] temp;

        if (size > vectorSize) {
            temp = Arrays.copyOf(vectorComponents, size);

            for (int i = 0; i < size; i++) {
                components[i] += temp[i];
            }
        } else {
            temp = Arrays.copyOf(components, vectorSize);
            size = vectorSize;

            for (int i = 0; i < size; i++) {
                temp[i] += vectorComponents[i];
            }

            components = temp;
        }
    }

    public void subtract(Vector vector) {
        double[] vectorComponents = vector.components;
        int vectorSize = vector.size;

        if (size == vectorSize) {
            for (int i = 0; i < size; i++) {
                components[i] -= vectorComponents[i];
            }

            return;
        }

        double[] temp;

        if (size > vectorSize) {
            temp = Arrays.copyOf(vectorComponents, size);

            for (int i = 0; i < size; i++) {
                components[i] -= temp[i];
            }
        } else {
            temp = Arrays.copyOf(components, vectorSize);
            size = vectorSize;

            for (int i = 0; i < size; i++) {
                temp[i] -= vectorComponents[i];
            }

            components = temp;
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < size; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        for (int i = 0; i < size; i++) {
            components[i] *= -1;
        }
    }

    public double getLength() {
        double squaresSum = 0;

        for (int i = 0; i < size; i++) {
            squaresSum += components[i] * components[i];
        }

        return Math.sqrt(squaresSum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс " + index + " выходит за пределы размерности вектора.");
        }

        return components[index];
    }

    public void setComponent(double value, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс " + index + " выходит за пределы размерности вектора.");
        }

        components[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != getClass()) return false;

        Vector v = (Vector) o;

        return size == v.size && Arrays.equals(components, v.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(components);
        hash = prime * hash + size;

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");

        for (int i = 0; i < size - 1; i++) {
            builder.append(components[i]);
            builder.append(", ");
        }

        builder.append(components[size - 1]);
        builder.append("}");

        return builder.toString();
    }

    public static Vector getSum(Vector a, Vector b) {
        double[] aComponents = a.components;
        double[] bComponents = b.components;
        int aSize = a.size;
        int bSize = b.size;

        Vector sum;
        double[] sumComponents;

        if (aSize < bSize) {
            sum = new Vector(bSize);
            sumComponents = Arrays.copyOf(aComponents, bSize);

            for (int i = 0; i < bSize; i++) {
                sumComponents[i] += bComponents[i];
            }
        } else {
            sum = new Vector(aSize);
            sumComponents = Arrays.copyOf(bComponents, aSize);

            for (int i = 0; i < aSize; i++) {
                sumComponents[i] += aComponents[i];
            }
        }

        sum.components = sumComponents;

        return sum;
    }

    public static Vector getDifference(Vector reduced, Vector subtracted) {
        double[] reducedComponents = reduced.components;
        double[] subtractedComponents = subtracted.components;
        int reducedSize = reduced.size;
        int subtractedSize = subtracted.size;

        Vector difference;
        double[] differenceComponents;

        if (reducedSize == subtractedSize) {
            difference = new Vector(reducedSize);
            differenceComponents = new double[reducedSize];

            for (int i = 0; i < reducedSize; i++) {
                differenceComponents[i] = reducedComponents[i] - subtractedComponents[i];
            }
        } else if (reducedSize < subtractedSize) {
            difference = new Vector(subtractedSize);
            differenceComponents = Arrays.copyOf(reducedComponents, subtractedSize);

            for (int i = 0; i < subtractedSize; i++) {
                differenceComponents[i] -= subtractedComponents[i];
            }
        } else {
            difference = new Vector(reducedSize);
            differenceComponents = new double[reducedSize];
            double[] temp = Arrays.copyOf(subtractedComponents, reducedSize);

            for (int i = 0; i < reducedSize; i++) {
                differenceComponents[i] = reducedComponents[i] - temp[i];
            }
        }

        difference.components = differenceComponents;

        return difference;
    }

    public static double multiply(Vector a, Vector b) {
        int tempLength = Math.min(a.size, b.size);
        double[] temp = new double[tempLength];

        for (int i = 0; i < tempLength; i++) {
            temp[i] = a.components[i] * b.components[i];
        }

        double multiplicationResult = 0;

        for (double e : temp) {
            multiplicationResult += e;
        }

        return multiplicationResult;
    }
}

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

    public void addVector(Vector otherVector) {
        double[] otherVectorComponents = otherVector.components;
        int otherVectorSize = otherVector.size;

        if (size == otherVectorSize) {
            for (int i = 0; i < size; i++) {
                components[i] += otherVectorComponents[i];
            }

            return;
        }

        double[] temp;

        if (size > otherVectorSize) {
            temp = Arrays.copyOf(otherVectorComponents, size);

            for (int i = 0; i < size; i++) {
                components[i] += temp[i];
            }
        } else {
            temp = Arrays.copyOf(components, otherVectorSize);
            size = otherVectorSize;

            for (int i = 0; i < size; i++) {
                temp[i] += otherVectorComponents[i];
            }

            components = temp;
        }
    }

    public void subtractVector(Vector otherVector) {
        double[] otherVectorComponents = otherVector.components;
        int otherVectorSize = otherVector.size;

        if (size == otherVectorSize) {
            for (int i = 0; i < size; i++) {
                components[i] -= otherVectorComponents[i];
            }

            return;
        }

        double[] temp;

        if (size > otherVectorSize) {
            temp = Arrays.copyOf(otherVectorComponents, size);

            for (int i = 0; i < size; i++) {
                components[i] -= temp[i];
            }
        } else {
            temp = Arrays.copyOf(components, otherVectorSize);
            size = otherVectorSize;

            for (int i = 0; i < size; i++) {
                temp[i] -= otherVectorComponents[i];
            }

            components = temp;
        }
    }

    public void multiplyByScalar(double scalar) {
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

    public static Vector addVectors(Vector vectorA, Vector vectorB) {
        double[] vectorAComponents = vectorA.components;
        double[] vectorBComponents = vectorB.components;
        int vectorASize = vectorA.size;
        int vectorBSize = vectorB.size;

        Vector sumVector;
        double[] sumVectorComponents;

        if (vectorASize < vectorBSize) {
            sumVector = new Vector(vectorBSize);
            sumVectorComponents = Arrays.copyOf(vectorAComponents, vectorBSize);

            for (int i = 0; i < vectorBSize; i++) {
                sumVectorComponents[i] += vectorBComponents[i];
            }
        } else {
            sumVector = new Vector(vectorASize);
            sumVectorComponents = Arrays.copyOf(vectorBComponents, vectorASize);

            for (int i = 0; i < vectorASize; i++) {
                sumVectorComponents[i] += vectorAComponents[i];
            }
        }

        sumVector.components = sumVectorComponents;

        return sumVector;
    }

    public static Vector subtractVectors(Vector reducedVector, Vector subtractedVector) {
        double[] reducedVectorComponents = reducedVector.components;
        double[] subtractedVectorComponents = subtractedVector.components;
        int reducedVectorSize = reducedVector.size;
        int subtractedVectorSize = subtractedVector.size;

        Vector subtractionVector;
        double[] subtractionVectorComponents;

        if (reducedVectorSize == subtractedVectorSize) {
            subtractionVector = new Vector(reducedVectorSize);
            subtractionVectorComponents = new double[reducedVectorSize];

            for (int i = 0; i < reducedVectorSize; i++) {
                subtractionVectorComponents[i] = reducedVectorComponents[i] - subtractedVectorComponents[i];
            }
        } else if (reducedVectorSize < subtractedVectorSize) {
            subtractionVector = new Vector(subtractedVectorSize);
            subtractionVectorComponents = Arrays.copyOf(reducedVectorComponents, subtractedVectorSize);

            for (int i = 0; i < subtractedVectorSize; i++) {
                subtractionVectorComponents[i] -= subtractedVectorComponents[i];
            }
        } else {
            subtractionVector = new Vector(reducedVectorSize);
            subtractionVectorComponents = new double[reducedVectorSize];
            double[] temp = Arrays.copyOf(subtractedVectorComponents, reducedVectorSize);

            for (int i = 0; i < reducedVectorSize; i++) {
                subtractionVectorComponents[i] = reducedVectorComponents[i] - temp[i];
            }
        }

        subtractionVector.components = subtractionVectorComponents;

        return subtractionVector;
    }

    public static double multiplyVectors(Vector vectorA, Vector vectorB) {
        int tempLength = Math.min(vectorA.size, vectorB.size);
        double[] temp = new double[tempLength];

        for (int i = 0; i < tempLength; i++) {
            temp[i] = vectorA.components[i] * vectorB.components[i];
        }

        double multiplicationResult = 0;

        for (double e : temp) {
            multiplicationResult += e;
        }

        return multiplicationResult;
    }
}

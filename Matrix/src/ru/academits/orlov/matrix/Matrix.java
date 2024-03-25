package ru.academits.orlov.matrix;

import ru.academits.orlov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private final Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount < 1) {
            throw new IllegalArgumentException("Матрица должна содержать не менее одной строки. Переданное значение: " + rowsCount);
        }

        if (columnsCount < 1) {
            throw new IllegalArgumentException("Матрица должна содержать не менее одного столбца. Переданное значение: " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] values) {
        rows = new Vector[values.length];

        int maxVectorSize = 1;

        for (int i = 1; i < values.length; i++) {
            maxVectorSize = Math.max(values[i].length, values[i - 1].length);
        }

        for (int i = 0; i < values.length; i++) {
            rows[i] = new Vector(maxVectorSize, values[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        rows = new Vector[vectors.length];

        int maxVectorSize = 1;

        for (int i = 1; i < vectors.length; i++) {
            maxVectorSize = Math.max(vectors[i].getSize(), vectors[i - 1].getSize());
        }

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxVectorSize);

            for (int j = 0; j < vectors[i].getSize(); j++) {
                rows[i].setComponent(j, vectors[i].getComponent(j));
            }
        }
    }

    public Vector[] getRows() {
        return rows;
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Допустимые значения для индекса: [0; " + (rows.length - 1) + "]. Передано значение: " + index);
        }

        return rows[index];
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Допустимые значения для индекса: [0; " + (rows.length - 1) + "]. Передано значение: " + index);
        }

        int validVectorSize = rows[0].getSize();

        if (vector.getSize() != validVectorSize) {
            throw new IllegalArgumentException("Допустимое значение для размера вектора: " + validVectorSize + ". Размер переданного вектора: " + vector.getSize());
        }

        for (int i = 0; i < validVectorSize; i++) {
            rows[index].setComponent(i, vector.getComponent(i));
        }
    }

    public Vector getColumn(int index) {
        int columnsCount = rows[0].getSize();

        if (index < 0 || index >= columnsCount) {
            throw new IndexOutOfBoundsException("Допустимые значения для индекса: [0; " + (columnsCount - 1) + "]. Передано значение: " + index);
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public Matrix transpose() {
        int rowsCount = rows[0].getSize();
        Matrix transposed = new Matrix(rowsCount, rows.length);

        for (int i = 0; i < rowsCount; i++) {
            Vector row = new Vector(rows.length);

            for (int j = 0; j < rows.length; j++) {
                row.setComponent(j, rows[j].getComponent(i));
            }

            transposed.setRow(i, row);
        }

        return transposed;
    }

    public void multiplyByScalar(double scalar) {
        if (scalar == 1) {
            return;
        }

        int rowLength = rows[0].getSize();

        for (Vector row : rows) {
            for (int i = 0; i < rowLength; i++) {
                row.setComponent(i, row.getComponent(i) * scalar);
            }
        }
    }

    public double getDeterminant() {
        if (rows.length != rows[0].getSize()) {
            throw new IllegalArgumentException("Невозможно вычислить определитель: переданная матрица размером "
                    + rows.length + "x" + rows[0].getSize() + " не является квадратной");
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }

        if (rows.length == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1)
                    - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        double determinant = rows[0].getComponent(0);

        for (int i = 0; i < rows.length - 1; i++) {
            for (int j = i + 1; j < rows.length; j++) {
                Vector subtractedRow = new Vector(rows[i]);

                if (rows[j].getComponent(i) != 0) {
                    if (rows[j].getComponent(i) != 1) {
                        subtractedRow.multiplyByScalar(rows[j].getComponent(i));
                    }

                    if (rows[i].getComponent(i) != 1) {
                        subtractedRow.multiplyByScalar(1 / rows[i].getComponent(i));
                    }

                    rows[j].subtract(subtractedRow);
                }
            }

            determinant *= rows[i + 1].getComponent(i + 1);
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        int vectorSize = vector.getSize();

        if (vectorSize != rows[0].getSize()) {
            throw new IllegalArgumentException("Невозможно выполнить умножение: количество столбцов в матрице (" + rows[0].getSize()
                    + ") не равно количеству строк в вектор-столбце (" + vectorSize + ").");
        }

        Vector product = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            double rowComponent = 0;

            for (int j = 0; j < vectorSize; j++) {
                rowComponent += rows[i].getComponent(j) * vector.getComponent(j);
            }

            product.setComponent(i, rowComponent);
        }

        return product;
    }

    public void add(Matrix matrix) {
        checkSizeMatch(matrix);

        int rowLength = rows[0].getSize();

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rowLength; j++) {
                rows[i].setComponent(j, rows[i].getComponent(j) + matrix.rows[i].getComponent(j));
            }
        }
    }

    public void subtract(Matrix matrix) {
        checkSizeMatch(matrix);

        int rowLength = rows[0].getSize();

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rowLength; j++) {
                rows[i].setComponent(j, rows[i].getComponent(j) - matrix.rows[i].getComponent(j));
            }
        }
    }

    private void checkSizeMatch(Matrix matrix) {
        if (rows.length != matrix.rows.length || rows[0].getSize() != matrix.rows[0].getSize()) {
            throw new IllegalArgumentException("Невозможно выполнить сложение/вычитание: размеры матриц не совпадают. " +
                    "В первой матрице: строк: " + rows.length + ", столбцов: " + rows[0].getSize() +
                    ". Во второй матрице: строк: " + matrix.rows.length + ", столбцов: " + matrix.rows[0].getSize());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) o;

        return Arrays.equals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rows);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        int j = 0;

        while (j < rows.length - 1) {
            stringBuilder.append(rows[j]).append(", ");

            j++;
        }

        stringBuilder.append(rows[j]);

        return "{" + stringBuilder + '}';
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix sumMatrix = new Matrix(matrix1);
        sumMatrix.add(matrix2);

        return sumMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix differenceMatrix = new Matrix(matrix1);
        differenceMatrix.subtract(matrix2);

        return differenceMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows[0].getSize() != matrix2.rows.length) {
            throw new IllegalArgumentException("Невозможно выполнить умножение: матрицы не согласованы. Количество столбцов первой матрицы: "
                    + matrix1.rows[0].getSize() + ". Количество строк второй матрицы: " + matrix2.rows.length);
        }

        int productColumnsCount = matrix2.rows[0].getSize();
        Matrix product = new Matrix(matrix1.rows.length, productColumnsCount);

        for (int i = 0; i < matrix1.rows.length; i++) {
            Vector productRow = new Vector(productColumnsCount);

            for (int j = 0; j < productColumnsCount; j++) {
                double rowComponent = 0;

                for (int k = 0; k < matrix2.rows.length; k++) {
                    rowComponent += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }

                productRow.setComponent(j, rowComponent);
            }

            product.setRow(i, productRow);
        }

        return product;
    }
}

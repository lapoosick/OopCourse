package ru.academits.orlov.matrix;

import ru.academits.orlov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

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
        if (values.length == 0) {
            throw new IllegalArgumentException("Нельзя создать матрицу размера 0. Передан массив нулевой длины.");
        }

        rows = new Vector[values.length];

        int maxVectorSize = 0;

        for (double[] row : values) {
            maxVectorSize = Math.max(maxVectorSize, row.length);
        }

        if (maxVectorSize == 0) {
            throw new IllegalArgumentException("Нельзя создать матрицу из массивов с нулевой длиной");
        }

        for (int i = 0; i < values.length; i++) {
            rows[i] = new Vector(maxVectorSize, values[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Нельзя создать матрицу размера 0. Передан массив векторов, у которого размер = 0.");
        }

        rows = new Vector[vectors.length];

        int maxVectorSize = 0;

        for (Vector vector : vectors) {
            maxVectorSize = Math.max(maxVectorSize, vector.getSize());
        }

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxVectorSize);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Допустимые значения для индекса строки: [0, "
                    + (rows.length - 1) + "]. Передано значение: " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Допустимые значения для индекса строки: [0, "
                    + (rows.length - 1) + "]. Передано значение: " + index);
        }

        int matrixColumnsCount = getColumnsCount();

        if (vector.getSize() != matrixColumnsCount) {
            throw new IllegalArgumentException("Размер переданного вектора (сейчас " + vector.getSize()
                    + ") должен быть равен количеству столбцов матрицы (сейчас " + matrixColumnsCount + ")");
        }

        for (int i = 0; i < matrixColumnsCount; i++) {
            rows[index].setComponent(i, vector.getComponent(i));
        }
    }

    public Vector getColumn(int index) {
        int columnsCount = getColumnsCount();

        if (index < 0 || index >= columnsCount) {
            throw new IndexOutOfBoundsException("Допустимые значения для индекса столбца: [0, "
                    + (columnsCount - 1) + "]. Передано значение: " + index);
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public void transpose() {
        Vector[] transposedRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            transposedRows[i] = getColumn(i);
        }

        rows = transposedRows;
    }

    public void multiplyByScalar(double scalar) {
        if (scalar == 1) {
            return;
        }

        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public Vector multiplyByVector(Vector vector) {
        int vectorSize = vector.getSize();

        if (vectorSize != getColumnsCount()) {
            throw new IllegalArgumentException("Невозможно выполнить умножение: количество столбцов в матрице (" + getColumnsCount()
                    + ") не равно количеству строк в векторе-столбце (" + vectorSize + ").");
        }

        Vector product = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            product.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return product;
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new IllegalStateException("Невозможно вычислить определитель: переданная матрица размером "
                    + rows.length + "x" + getColumnsCount() + " не является квадратной");
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }

        if (rows.length == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1)
                    - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        Matrix copy = new Matrix(this);
        double determinant = rows[0].getComponent(0);
        final double EPSILON = 1.0e-10;
        int lastRowIndex = rows.length - 1;

        for (int i = 0; i < lastRowIndex; i++) {
            for (int j = i + 1; j < rows.length; j++) {
                if (Math.abs(copy.rows[j].getComponent(i)) > EPSILON) {
                    Vector subtractedRow = copy.getRow(i);

                    if (Math.abs(copy.rows[j].getComponent(i) - 1) > EPSILON) {
                        subtractedRow.multiplyByScalar(copy.rows[j].getComponent(i));
                    }

                    if (Math.abs(copy.rows[i].getComponent(i) - 1) > EPSILON) {
                        subtractedRow.multiplyByScalar(1 / copy.rows[i].getComponent(i));
                    }

                    copy.rows[j].subtract(subtractedRow);
                }
            }

            determinant *= copy.rows[i + 1].getComponent(i + 1);
        }

        return determinant;
    }

    public void add(Matrix matrix) {
        checkMatricesSizesEquality(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesSizesEquality(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    private void checkMatricesSizesEquality(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Невозможно выполнить сложение/вычитание: размеры матриц не совпадают. " +
                    "В первой матрице: строк: " + rows.length + ", столбцов: " + getColumnsCount() +
                    ". Во второй матрице: строк: " + matrix.rows.length + ", столбцов: " + matrix.getColumnsCount());
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
        StringBuilder stringBuilder = new StringBuilder("{");
        int lastRowIndex = rows.length - 1;

        for (int i = 0; i < lastRowIndex; i++) {
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[lastRowIndex]).append('}');

        return stringBuilder.toString();
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.checkMatricesSizesEquality(matrix2);

        Matrix sumMatrix = new Matrix(matrix1);
        sumMatrix.add(matrix2);

        return sumMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.checkMatricesSizesEquality(matrix2);

        Matrix differenceMatrix = new Matrix(matrix1);
        differenceMatrix.subtract(matrix2);

        return differenceMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Невозможно выполнить умножение: количество столбцов первой матрицы (сейчас "
                    + matrix1.getColumnsCount() + ") должно быть равно количеству строк второй матрицы (сейчас " + matrix2.rows.length + ")");
        }

        int productColumnsCount = matrix2.getColumnsCount();
        Matrix product = new Matrix(matrix1.rows.length, productColumnsCount);

        for (int i = 0; i < matrix1.rows.length; i++) {
            Vector productRow = new Vector(productColumnsCount);

            for (int j = 0; j < productColumnsCount; j++) {
                productRow.setComponent(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }

            product.setRow(i, productRow);
        }

        return product;
    }
}

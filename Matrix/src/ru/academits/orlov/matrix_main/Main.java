package ru.academits.orlov.matrix_main;

import ru.academits.orlov.matrix.Matrix;
import ru.academits.orlov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 4);
        Matrix matrix2 = new Matrix(new double[][]{{2, 3}, {1, 3, 5}, {}, {2, 2, 3, 1}});
        Matrix matrix3 = new Matrix(matrix2);
        Matrix matrix4 = new Matrix(new double[][]{{1}, {}});

        Vector v1 = new Vector(new double[]{4});
        Vector v2 = new Vector(new double[]{1, 2});
        Vector v3 = new Vector(new double[]{1, 2, 3});
        Vector v4 = new Vector(new double[]{3, 1});

        Matrix matrix5 = new Matrix(new Vector[]{v1, v2, v3, v4});
        Matrix matrix51 = new Matrix(new Vector[]{v2, v3});
        Matrix matrix52 = new Matrix(new Vector[]{v2, v4});
        Matrix matrix6 = new Matrix(new double[][]{{4}, {}, {1}, {2, 3, 0, 1}});

        System.out.println("Матрица нулей: " + matrix1);
        System.out.println("Матрица из двумерного массива: " + matrix2);
        System.out.println("Копия матрицы из двумерного массива: " + matrix3);
        System.out.println("Матрица из двумерного массива: " + matrix4);
        System.out.println("Матрица из массива векторов-строк: " + matrix5);
        matrix5.transpose();
        System.out.println(matrix51);
        matrix51.transpose();
        System.out.println(matrix52);
        matrix52.transpose();
        System.out.println(matrix2 + " equals " + matrix6 + " - " + matrix2.equals(matrix6));
        System.out.println(matrix2 + " equals " + matrix3 + " - " + matrix2.equals(matrix3));
        System.out.println(matrix2 + " equals " + matrix4 + " - " + matrix2.equals(matrix4));
        System.out.println("hashCode " + matrix2 + ": " + matrix2.hashCode());
        System.out.println("hashCode " + matrix6 + ": " + matrix6.hashCode());
        System.out.println("hashCode " + matrix3 + ": " + matrix3.hashCode());
        System.out.println("hashCode " + matrix4 + ": " + matrix4.hashCode());
        System.out.println(matrix6 + " количество строк = " + matrix6.getRowsCount());
        System.out.println(matrix6 + " количество столбцов = " + matrix6.getColumnsCount());
        System.out.println("Вектор по индексу 2 матрицы " + matrix6 + ": " + matrix6.getRow(2));
        System.out.println("Первый столбец матрицы №6: " + matrix6.getColumn(0));
        System.out.println();
        System.out.println("matrix2:" + matrix2);
        System.out.println("Задание вектора " + v2 + " по индексу 0 матрицы №2:");

        Vector vector = new Vector(new double[]{1, 2, 3, 4});
        matrix2.setRow(0, vector);

        System.out.println();
        System.out.println("matrix2 исходная: " + matrix2);
        matrix2.transpose();
        System.out.println("matrix2 транспонированная: " + matrix2);
        System.out.println();

        matrix2.multiplyByScalar(2);

        System.out.println("Умножение матрицы №2 на 2: " + matrix2);

        matrix2.multiplyByScalar(0);

        System.out.println("Умножение матрицы №2 на 0: " + matrix2);
        System.out.println();

        matrix2.add(matrix6);

        Matrix matrix7 = new Matrix(new double[][]{{2, 3}, {3, 7}, {4, -10}, {0, 0, 0, 0}});

        System.out.println("Прибавление матрицы:");
        System.out.println("До прибавления:");
        System.out.println("matrix2: " + matrix2);
        System.out.println("matrix7: " + matrix7);

        matrix2.add(matrix7);

        System.out.println("После прибавления:");
        System.out.println("matrix2: " + matrix2);
        System.out.println("matrix7: " + matrix7);
        System.out.println();
        System.out.println("Вычитание матрицы:");
        System.out.println("До вычитания:");
        System.out.println("matrix2: " + matrix2);
        System.out.println("matrix7: " + matrix7);

        matrix2.subtract(matrix7);

        System.out.println("После вычитания:");
        System.out.println("matrix2: " + matrix2);
        System.out.println("matrix7: " + matrix7);
        System.out.println();
        System.out.println("Сложение матриц (статический метод):");
        System.out.println("matrix2 + matrix7 = " + Matrix.getSum(matrix2, matrix7));
        System.out.println("matrix2: " + matrix2);
        System.out.println("matrix7: " + matrix7);
        System.out.println();
        System.out.println("Вычитание матриц (статический метод):");
        System.out.println("matrix2 - matrix7 = " + Matrix.getDifference(matrix2, matrix7));
        System.out.println("matrix2: " + matrix2);
        System.out.println("matrix7: " + matrix7);
        System.out.println();
        System.out.println("Умножение матрицы на вектор:");
        System.out.println("matrix6: " + matrix6);
        System.out.println(matrix6 + " x " + vector + " = " + matrix6.multiplyByVector(vector));
        System.out.println();

        Matrix matrix8 = new Matrix(new double[][]{{2, 3, 1}, {2, 3, 5}});
        Matrix matrix9 = new Matrix(new double[][]{{3, 4, 6, 2}, {2, 1, 0, 2}, {7, 5, 3, 2}});

        System.out.println("Умножение матриц:");
        System.out.println(matrix8 + " x " + matrix9 + " = " + Matrix.getProduct(matrix8, matrix9));
        System.out.println();

        Vector v5 = new Vector(new double[]{1, 2, 1});
        Vector v6 = new Vector(new double[]{3, 1, 2});
        Vector v7 = new Vector(new double[]{2, 1, 0});
        Matrix matrix10 = new Matrix(new Vector[]{v5, v6, v7});

        System.out.println("matrix10: " + matrix10);
        System.out.println("Определитель матрицы №10 равен " + matrix10.getDeterminant());
    }
}
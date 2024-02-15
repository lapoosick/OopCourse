package ru.academits.orlov.shapes;

public class Triangle implements Shape {
    private static final double EPSILON = 1e-10;

    private final double x1;
    private final double y1;

    private final double x2;
    private final double y2;

    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (Math.abs((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3)) <= EPSILON) {
            throw new IllegalArgumentException("Точки лежат на одной прямой. Это не треугольник.");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return getMax(x1, x2, x3) - getMin(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getMax(y1, y2, y3) - getMin(y1, y2, y3);
    }

    @Override
    public double getArea() {
        return Math.abs((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3)) / 2;
    }

    @Override
    public double getPerimeter() {
        double aSideLength = getSideLength(x1, y1, x2, y2);
        double bSideLength = getSideLength(x2, y2, x3, y3);
        double cSideLength = getSideLength(x1, y1, x3, y3);

        return aSideLength + bSideLength + cSideLength;
    }

    private static double getMax(double number1, double number2, double number3) {
        return Math.max(Math.max(number1, number2), number3);
    }

    private static double getMin(double number1, double number2, double number3) {
        return Math.min(Math.min(number1, number2), number3);
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public String toString() {
        return "Треугольник: " +
                "координаты первой вершины = (" + x1 + ", " + y1 + ")" +
                ", координаты второй вершины = (" + x2 + ", " + y2 + ")" +
                ", координаты третьей вершины = (" + x3 + ", " + y3 + ")" +
                ", длина стороны, соединяющей первую и вторую вершины: " + getSideLength(x1, y1, x2, y2) +
                ", длина стороны, соединяющей вторую и третью вершины: " + getSideLength(x2, y2, x3, y3) +
                ", длина стороны, соединяющей первую и третью вершины: " + getSideLength(x1, y1, x3, y3) +
                ", ширина = " + getWidth() +
                ", высота = " + getHeight() +
                ", площадь = " + getArea() +
                ", периметр = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle t = (Triangle) o;

        return x1 == t.x1 && y1 == t.y1 && x2 == t.x2 && y2 == t.y2 && x3 == t.x3 && y3 == t.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }
}

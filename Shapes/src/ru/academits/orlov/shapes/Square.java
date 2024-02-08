package ru.academits.orlov.shapes;

public class Square implements Shape {
    private final double sideLength;

    public Square(double sideLength) {
        if (sideLength < 0) {
            throw new IllegalArgumentException("Сторона квадрата не должна быть < 0");
        }

        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 4;
    }

    @Override
    public String toString() {
        return "Квадрат: " +
                "длина стороны = " + getWidth() +
                ", площадь = " + getArea() +
                ", периметр = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != getClass()) return false;

        Square s = (Square) o;

        return sideLength == s.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(sideLength);

        return hash;
    }
}

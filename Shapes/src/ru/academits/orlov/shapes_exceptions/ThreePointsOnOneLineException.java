package ru.academits.orlov.shapes_exceptions;

public class ThreePointsOnOneLineException extends RuntimeException{
    public ThreePointsOnOneLineException(String message) {
        super(message);
    }

    public ThreePointsOnOneLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThreePointsOnOneLineException(Throwable cause) {
        super(cause);
    }
}

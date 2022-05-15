package by.teachmeskills.service.exception;

public class HabrDateTimeFormatException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "DateTime format is not valid!";

    public HabrDateTimeFormatException(){
        super(DEFAULT_MESSAGE);
    }
}
package model.exceptions;


import org.testng.internal.collections.Pair;

public class ExceptionWithError extends Exception {

    public Pair<String, String> error;

    public ExceptionWithError(String value) {
        this.error = new Pair<>("error",value);
    }
}

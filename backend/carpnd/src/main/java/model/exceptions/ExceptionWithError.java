package model.exceptions;

import javafx.util.Pair;

public class ExceptionWithError extends Exception {

    public Pair<String, String> error;

    public ExceptionWithError(String value) {
        this.error = new Pair<>("error",value);
    }
}

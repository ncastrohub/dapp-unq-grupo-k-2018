package model.exceptions;

import java.util.Map.Entry;

public class ExceptionWithError extends Exception {

    public Entry<String, String> error;

    public ExceptionWithError(String value) {
        this.error = new Entry<String, String>() {
            @Override
            public String getKey() {
                return "error";
            }

            @Override
            public String getValue() {
                return value;
            }

            @Override
            public String setValue(String value) {
                return null;
            }
        };
    }
}

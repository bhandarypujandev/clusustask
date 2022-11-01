package com.example.cp.exception;

public class OtherExceptions extends RuntimeException {
    private final String code = "13";
    public OtherExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public OtherExceptions(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }
}

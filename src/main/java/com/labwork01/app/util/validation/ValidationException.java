package com.labwork01.app.util.validation;
import java.util.Set;
public class ValidationException extends RuntimeException{
    public ValidationException(Set<String> errors) {
        super(String.join("\n", errors));
    }
}
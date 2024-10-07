package com.powerkr_teste.powerkr.controller;

import java.util.Arrays;
import java.util.List;

public class ResponseError {
    private List<String> errors;

    public ResponseError(List<String> errors){
        this.errors = errors;
    }
    public ResponseError(String messageErrors){
        this.errors = Arrays.asList(messageErrors);
    }

    public List<String> getErrors() {
        return errors;
    }
}

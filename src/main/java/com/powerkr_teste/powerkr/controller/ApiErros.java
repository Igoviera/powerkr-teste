package com.powerkr_teste.powerkr.controller;

import java.util.Arrays;
import java.util.List;

public class ApiErros {
    private List<String> errors;

    public ApiErros(List<String> errors){
        this.errors = errors;
    }
    public ApiErros(String messageErrors){
        this.errors = Arrays.asList(messageErrors);
    }

    public List<String> getErrors() {
        return errors;
    }
}

package com.powerkr_teste.powerkr.exceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(Long id){
        super("Recurso não encontrado com o id " + id);
    }
}

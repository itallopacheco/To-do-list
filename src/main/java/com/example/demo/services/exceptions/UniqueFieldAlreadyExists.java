package com.example.demo.services.exceptions;

public class UniqueFieldAlreadyExists extends RuntimeException{

    public UniqueFieldAlreadyExists(String msg){
        super(msg);
    }
}

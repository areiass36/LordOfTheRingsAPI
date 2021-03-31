package com.barretoareias.lotr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CharAlreadyCreatedException extends Exception{

    public CharAlreadyCreatedException(String name){
        super(String.format("char %s already created",name));
    }

    private static final long serialVersionUID = 1L;

    
    
}

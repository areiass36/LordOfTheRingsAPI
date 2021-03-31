package com.barretoareias.lotr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CharNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public CharNotFoundException(String name){
        super(String.format("could not find char named %s",name));
    }    

    public CharNotFoundException(Long id){
        super(String.format("could not find char with id %s",id));
    }
}

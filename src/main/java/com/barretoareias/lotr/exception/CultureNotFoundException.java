package com.barretoareias.lotr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CultureNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public CultureNotFoundException(String name){
        super(String.format("could not find culture named %s",name));
    }

    public CultureNotFoundException(Long id){
        super(String.format("could not find culture with id %s",id));
    }
    
}

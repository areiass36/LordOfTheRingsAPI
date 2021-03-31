package com.barretoareias.lotr.service;


import com.barretoareias.lotr.entity.Char;
import com.barretoareias.lotr.exception.CharAlreadyCreatedException;
import com.barretoareias.lotr.exception.CharNotFoundException;
import com.barretoareias.lotr.repository.CharRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CharService {

    private final CharRepository repository;
    
    public Flux<Char> findAll(){
        var list = repository.findAll();
        return Flux.fromIterable(list);
    }

    public Mono<Char> createChar(Char entity) throws CharAlreadyCreatedException{
        var entityName = entity.getName();
        if(ifExistsByName(entityName)){
            throw new CharAlreadyCreatedException(entityName);
        }
        repository.save(entity);
        var returned = repository.findById(entity.getId());
        return Mono.justOrEmpty(returned);
    }

    public Mono<Char> findByName(String name) throws CharNotFoundException{
        if(!ifExistsByName(name)){
            throw new CharNotFoundException(name);
        }
        var entity = repository.findByName(name);
        return Mono.justOrEmpty(entity);
    }

    public Mono<Char> findById(Long id) throws CharNotFoundException{
        if(!ifExistsById(id)){
            throw new CharNotFoundException(id);
        }
        var entity = repository.findById(id);
        return Mono.justOrEmpty(entity);
    }

    public Mono<Boolean> deleteById(Long id) throws CharNotFoundException{
        if(!ifExistsById(id)){
            throw new CharNotFoundException(id);
        }
        repository.deleteById(id);
        return Mono.just(true);
    }

    public Mono<Char> updateChar(Long id,Char entity) throws CharNotFoundException{
        if(!ifExistsById(id)){
            throw new CharNotFoundException(id);
        }
        entity.setId(id);
        repository.save(entity);
        var returned = repository.findById(id);
        return Mono.justOrEmpty(returned);
    }

    private boolean ifExistsByName(String name){
        var returned = repository.findByName(name);
        return !returned.isEmpty();
    }

    private boolean ifExistsById(Long id){
        var returned = repository.findById(id);
        return !returned.isEmpty();
    }
    
    
}

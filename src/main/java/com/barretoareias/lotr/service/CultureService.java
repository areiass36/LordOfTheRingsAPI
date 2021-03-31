package com.barretoareias.lotr.service;

import com.barretoareias.lotr.entity.Culture;
import com.barretoareias.lotr.exception.CultureNotFoundException;
import com.barretoareias.lotr.exception.CultureAlreadyCreatedException;
import com.barretoareias.lotr.repository.CultureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CultureService {
    
    private final CultureRepository repository;

    public Flux<Culture> findAll(){
        var list = repository.findAll();
        return Flux.fromIterable(list);
    }

    public Mono<Culture> createCulture(Culture entity) throws CultureAlreadyCreatedException{
        var entityName = entity.getName();
        if(ifExistsByName(entityName)){
            throw new CultureAlreadyCreatedException(entityName);
        }
        var returned = repository.save(entity);
        return Mono.justOrEmpty(returned);
    }

    public Mono<Culture> findByName(String name) throws CultureNotFoundException{
        if(!ifExistsByName(name)){
            throw new CultureNotFoundException(name);
        }
        var entity = repository.findByName(name);
        return Mono.justOrEmpty(entity);
    }

    public Mono<Culture> findById(Long id) throws CultureNotFoundException{
        if(!ifExistsById(id)){
            throw new CultureNotFoundException(id);
        }
        var entity = repository.findById(id);
        return Mono.justOrEmpty(entity);
    }

    public Mono<Boolean> deleteById(Long id) throws CultureNotFoundException{
        if(!ifExistsById(id)){
            throw new CultureNotFoundException(id);
        }
        repository.deleteById(id);
        return Mono.just(true);
    }

    public Mono<Culture> updateCulture(Long id,Culture entity) throws CultureNotFoundException{
        if(!ifExistsById(id)){
            throw new CultureNotFoundException(id);
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

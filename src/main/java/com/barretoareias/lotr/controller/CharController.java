package com.barretoareias.lotr.controller;

import com.barretoareias.lotr.service.CharService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barretoareias.lotr.entity.Char;
import com.barretoareias.lotr.exception.CharAlreadyCreatedException;
import com.barretoareias.lotr.exception.CharNotFoundException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/char")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CharController implements CharControllerDocs{
    
    private final CharService charService;

    @Override
    @PostMapping
    public Mono<Char> createChar(@RequestBody Char entity) throws CharAlreadyCreatedException{
        return charService.createChar(entity);
    }

    @Override
    @GetMapping
    public Flux<Char> findAll() {
        return charService.findAll();
    }

    @Override
    @GetMapping("/name/{name}")
    public Mono<Char> findByName(@PathVariable String name)throws CharNotFoundException {
        return charService.findByName(name);
    }

    @Override
    @GetMapping("/id/{id}")
    public Mono<Char> findById(@PathVariable Long id)throws CharNotFoundException {
        return charService.findById(id);
    }

    @Override
    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id)throws CharNotFoundException{
        charService.deleteById(id);
    }

    @Override
    @PutMapping("/id/{id}")
    public Mono<Char> updateChar(@PathVariable Long id,@RequestBody Char entity) throws CharNotFoundException {
        return charService.updateChar(id, entity);
    }

    

}

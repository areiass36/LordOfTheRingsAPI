package com.barretoareias.lotr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.barretoareias.lotr.entity.Culture;
import com.barretoareias.lotr.exception.CultureAlreadyCreatedException;
import com.barretoareias.lotr.exception.CultureNotFoundException;
import com.barretoareias.lotr.service.CultureService;

@RestController
@RequestMapping("api/v1/culture")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CultureController implements CultureControllerDocs{

    private final CultureService cultureService;

    @Override
    @PostMapping
    public Mono<Culture> createCulture(@RequestBody Culture entity) throws CultureAlreadyCreatedException{
        return cultureService.createCulture(entity);
    }

    @Override
    @GetMapping
    public Flux<Culture> findAll() {
        return cultureService.findAll();
    }

    @Override
    @GetMapping("/name/{name}")
    public Mono<Culture> findByName(@PathVariable String name) throws CultureNotFoundException{
        return cultureService.findByName(name);
    }

    @Override
    @GetMapping("/id/{id}")
    public Mono<Culture> findById(@PathVariable Long id)throws CultureNotFoundException {
        return cultureService.findById(id);
    }

    @Override
    @DeleteMapping("/id/{id}")
    public void deleteById(Long id) throws CultureNotFoundException{
        cultureService.deleteById(id);
    }

    @Override
    @PutMapping("/id/{id}")
    public Mono<Culture> updateCulture(@PathVariable Long id,@RequestBody Culture entity) throws CultureNotFoundException {
        return cultureService.updateCulture(id, entity);
    }
    
}

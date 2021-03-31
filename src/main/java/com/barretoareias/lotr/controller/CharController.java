package com.barretoareias.lotr.controller;

import com.barretoareias.lotr.service.CharService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.barretoareias.lotr.dto.CharDTO;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CharDTO> createChar(@RequestBody @Valid CharDTO dto) throws CharAlreadyCreatedException{
        return charService.createChar(dto);
    }

    @Override
    @GetMapping
    public Flux<CharDTO> findAll() {
        return charService.findAll();
    }

    @Override
    @GetMapping("/name/{name}")
    public Mono<CharDTO> findByName(@PathVariable String name)throws CharNotFoundException {
        return charService.findByName(name);
    }

    @Override
    @GetMapping("/id/{id}")
    public Mono<CharDTO> findById(@PathVariable Long id)throws CharNotFoundException {
        return charService.findById(id);
    }

    @Override
    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id)throws CharNotFoundException{
        charService.deleteById(id);
    }

    @Override
    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CharDTO> updateChar(@PathVariable Long id,@RequestBody @Valid CharDTO dto) throws CharNotFoundException {
        return charService.updateChar(id,dto);
    }

    

}

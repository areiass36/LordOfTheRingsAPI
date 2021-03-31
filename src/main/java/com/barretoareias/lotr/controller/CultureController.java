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

import javax.validation.Valid;

import com.barretoareias.lotr.dto.CultureDTO;
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
    public Mono<CultureDTO> createCulture(@RequestBody @Valid CultureDTO dto) throws CultureAlreadyCreatedException{
        return cultureService.createCulture(dto);
    }

    @Override
    @GetMapping
    public Flux<CultureDTO> findAll() {
        return cultureService.findAll();
    }

    @Override
    @GetMapping("/name/{name}")
    public Mono<CultureDTO> findByName(@PathVariable String name) throws CultureNotFoundException{
        return cultureService.findByName(name);
    }

    @Override
    @GetMapping("/id/{id}")
    public Mono<CultureDTO> findById(@PathVariable Long id)throws CultureNotFoundException {
        return cultureService.findById(id);
    }

    @Override
    @DeleteMapping("/id/{id}")
    public void deleteById(Long id) throws CultureNotFoundException{
        cultureService.deleteById(id);
    }

    @Override
    @PutMapping("/id/{id}")
    public Mono<CultureDTO> updateCulture(@PathVariable Long id,@RequestBody @Valid CultureDTO dto) throws CultureNotFoundException {
        return cultureService.updateCulture(id, dto);
    }
    
}

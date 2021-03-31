package com.barretoareias.lotr.service;

import java.util.stream.Collectors;

import com.barretoareias.lotr.dto.CultureDTO;
import com.barretoareias.lotr.exception.CultureNotFoundException;
import com.barretoareias.lotr.mapper.CultureMapper;
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
    private final CultureMapper mapper = CultureMapper.INSTANCE;

    public Flux<CultureDTO> findAll(){
        var list = repository.findAll();
        var listDTO = list.stream().map(mapper::toDTO).collect(Collectors.toList());
        return Flux.fromIterable(listDTO);
    }

    public Mono<CultureDTO> createCulture(CultureDTO dto) throws CultureAlreadyCreatedException{
        var entity = mapper.toEntity(dto);
        var entityName = entity.getName();
        if(ifExistsByName(entityName)){
            throw new CultureAlreadyCreatedException(entityName);
        }
        var newEntity = repository.save(entity);
        var returned = repository.findById(newEntity.getId());
        return Mono.justOrEmpty(mapper.toDTO(returned.get()));
    }

    public Mono<CultureDTO> findByName(String name) throws CultureNotFoundException{
        if(!ifExistsByName(name)){
            throw new CultureNotFoundException(name);
        }
        var entity = repository.findByName(name);
        return Mono.justOrEmpty(mapper.toDTO(entity.get()));
    }

    public Mono<CultureDTO> findById(Long id) throws CultureNotFoundException{
        if(!ifExistsById(id)){
            throw new CultureNotFoundException(id);
        }
        var entity = repository.findById(id);
        return Mono.justOrEmpty(mapper.toDTO(entity.get()));
    }

    public Mono<Boolean> deleteById(Long id) throws CultureNotFoundException{
        if(!ifExistsById(id)){
            throw new CultureNotFoundException(id);
        }
        repository.deleteById(id);
        return Mono.just(true);
    }

    public Mono<CultureDTO> updateCulture(Long id,CultureDTO dto) throws CultureNotFoundException{
        var entity = mapper.toEntity(dto);
        if(!ifExistsById(id)){
            throw new CultureNotFoundException(id);
        }
        entity.setId(id);
        repository.save(entity);
        var returned = repository.findById(id);
        return Mono.justOrEmpty(mapper.toDTO(returned.get()));
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

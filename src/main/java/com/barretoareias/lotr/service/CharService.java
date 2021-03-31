package com.barretoareias.lotr.service;

import java.util.stream.Collectors;

import com.barretoareias.lotr.dto.CharDTO;
import com.barretoareias.lotr.exception.CharAlreadyCreatedException;
import com.barretoareias.lotr.exception.CharNotFoundException;
import com.barretoareias.lotr.mapper.CharMapper;
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
    private final CharMapper mapper = CharMapper.INSTANCE;

    public Flux<CharDTO> findAll() {
        var list = repository.findAll();
        var listDTO = list.stream().map(mapper::toDTO).collect(Collectors.toList());
        return Flux.fromIterable(listDTO);
    }

    public Mono<CharDTO> createChar(CharDTO dto) throws CharAlreadyCreatedException {
        var entity = mapper.toEntity(dto);
        var entityName = entity.getName();
        if (ifExistsByName(entityName)) {
            throw new CharAlreadyCreatedException(entityName);
        }
        var newEntity = repository.save(entity);
        var returned = repository.findById(newEntity.getId());
        return Mono.justOrEmpty(mapper.toDTO(returned.get()));
    }

    public Mono<CharDTO> findByName(String name) throws CharNotFoundException {
        if (!ifExistsByName(name)) {
            throw new CharNotFoundException(name);
        }
        var entity = repository.findByName(name);
        return Mono.justOrEmpty(mapper.toDTO(entity.get()));
    }

    public Mono<CharDTO> findById(Long id) throws CharNotFoundException {
        if (!ifExistsById(id)) {
            throw new CharNotFoundException(id);
        }
        var entity = repository.findById(id);
        return Mono.justOrEmpty(mapper.toDTO(entity.get()));
    }

    public Mono<Boolean> deleteById(Long id) throws CharNotFoundException {
        if (!ifExistsById(id)) {
            throw new CharNotFoundException(id);
        }
        repository.deleteById(id);
        return Mono.just(true);
    }

    public Mono<CharDTO> updateChar(Long id, CharDTO dto) throws CharNotFoundException {
        var entity = mapper.toEntity(dto);
        if (!ifExistsById(id)) {
            throw new CharNotFoundException(id);
        }
        entity.setId(id);
        repository.save(entity);
        var returned = repository.findById(id);
        return Mono.justOrEmpty(mapper.toDTO(returned.get()));
    }

    private boolean ifExistsByName(String name) {
        var returned = repository.findByName(name);
        return !returned.isEmpty();
    }

    private boolean ifExistsById(Long id) {
        var returned = repository.findById(id);
        return !returned.isEmpty();
    }

}

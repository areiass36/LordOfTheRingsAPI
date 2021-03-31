package com.barretoareias.lotr.controller;

import com.barretoareias.lotr.dto.CharDTO;
import com.barretoareias.lotr.entity.Char;
import com.barretoareias.lotr.exception.CharAlreadyCreatedException;
import com.barretoareias.lotr.exception.CharNotFoundException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api("Manages Char Controller")
public interface CharControllerDocs {

    @ApiOperation(value = "creates a new char")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "successfully created"),
            @ApiResponse(code = 400, message = "missing required fields") })
    Mono<CharDTO> createChar(@RequestBody CharDTO dto) throws CharAlreadyCreatedException;

    @ApiOperation(value = "find all chars")
    @ApiResponses(value = @ApiResponse(code = 200, message = "all chars returned"))
    Flux<CharDTO> findAll();

    @ApiOperation(value = "find char by name")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "char found"),
            @ApiResponse(code = 404, message = "char not found") })
    Mono<CharDTO> findByName(@PathVariable String name) throws CharNotFoundException;

    @ApiOperation(value = "update char")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "char found"),
            @ApiResponse(code = 404, message = "char not found") })
    Mono<CharDTO> updateChar(@PathVariable Long id, @RequestBody CharDTO dto) throws CharNotFoundException;

    @ApiOperation(value = "find char by id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "char found"),
            @ApiResponse(code = 404, message = "char not found") })
    Mono<CharDTO> findById(@PathVariable Long id) throws CharNotFoundException;

    @ApiOperation(value = "delete char by id")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "char not found"),
            @ApiResponse(code = 204, message = "sucefully deleted") })
    void deleteById(@PathVariable Long id) throws CharNotFoundException;

}

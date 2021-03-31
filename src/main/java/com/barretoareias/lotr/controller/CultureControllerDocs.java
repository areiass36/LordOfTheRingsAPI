package com.barretoareias.lotr.controller;

import com.barretoareias.lotr.dto.CultureDTO;
import com.barretoareias.lotr.exception.CultureAlreadyCreatedException;
import com.barretoareias.lotr.exception.CultureNotFoundException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Manages culture")
public interface CultureControllerDocs {
    
    @ApiOperation(value = "creates a new culture")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "successfully created"),
            @ApiResponse(code = 400, message = "missing required fields") })
    Mono<CultureDTO> createCulture(@RequestBody CultureDTO dto) throws CultureAlreadyCreatedException;

    @ApiOperation(value = "find all cultures")
    @ApiResponses(value = @ApiResponse(code = 200, message = "all cultures returned"))
    Flux<CultureDTO> findAll();

    @ApiOperation(value = "find culture by name")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "culture found"),
            @ApiResponse(code = 404, message = "culture not found") })
    Mono<CultureDTO> findByName(@PathVariable String name)throws CultureNotFoundException;

    @ApiOperation(value = "find culture by id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "culture found"),
            @ApiResponse(code = 404, message = "culture not found") })
    Mono<CultureDTO> findById(@PathVariable Long id)throws CultureNotFoundException;

    @ApiOperation(value = "update culture")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "culture found"),
            @ApiResponse(code = 404, message = "culture not found") })
    Mono<CultureDTO> updateCulture(@PathVariable Long id, @RequestBody CultureDTO dto) throws CultureNotFoundException;

    @ApiOperation(value = "delete culture by id")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "culture not found"),
            @ApiResponse(code = 204, message = "sucefully deleted") })
    void deleteById(@PathVariable Long id)throws CultureNotFoundException;

}

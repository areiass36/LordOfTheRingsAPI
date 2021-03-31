package com.barretoareias.lotr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barretoareias.lotr.entity.Char;

import java.util.Optional;

public interface CharRepository extends JpaRepository<Char, Long>{
    
    Optional<Char> findByName(String name);
}

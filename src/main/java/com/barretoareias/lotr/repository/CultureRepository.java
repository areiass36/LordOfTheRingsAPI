package com.barretoareias.lotr.repository;

import java.util.Optional;

import com.barretoareias.lotr.entity.Culture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultureRepository extends JpaRepository<Culture, Long>{

    Optional<Culture> findByName(String name);
    
}

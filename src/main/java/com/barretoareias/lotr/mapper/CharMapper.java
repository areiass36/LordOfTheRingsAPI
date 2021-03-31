package com.barretoareias.lotr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.barretoareias.lotr.dto.CharDTO;
import com.barretoareias.lotr.entity.Char;
import com.barretoareias.lotr.mapper.CharMapper;

@Mapper
public interface CharMapper {
    
    CharMapper INSTANCE = Mappers.getMapper(CharMapper.class);

    Char toEntity(CharDTO dto);

    CharDTO toDTO(Char entity);

}

package com.barretoareias.lotr.mapper;

import com.barretoareias.lotr.dto.CultureDTO;
import com.barretoareias.lotr.entity.Culture;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CultureMapper {

    CultureMapper INSTANCE = Mappers.getMapper(CultureMapper.class);

    Culture toEntity(CultureDTO dto);

    CultureDTO toDTO(Culture entity);
}

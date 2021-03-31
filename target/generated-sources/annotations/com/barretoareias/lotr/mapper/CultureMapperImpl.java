package com.barretoareias.lotr.mapper;

import com.barretoareias.lotr.dto.CultureDTO;
import com.barretoareias.lotr.dto.CultureDTO.CultureDTOBuilder;
import com.barretoareias.lotr.entity.Culture;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-31T19:01:00-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (AdoptOpenJDK)"
)
public class CultureMapperImpl implements CultureMapper {

    @Override
    public Culture toEntity(CultureDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Culture culture = new Culture();

        culture.setId( dto.getId() );
        culture.setName( dto.getName() );

        return culture;
    }

    @Override
    public CultureDTO toDTO(Culture entity) {
        if ( entity == null ) {
            return null;
        }

        CultureDTOBuilder cultureDTO = CultureDTO.builder();

        cultureDTO.id( entity.getId() );
        cultureDTO.name( entity.getName() );

        return cultureDTO.build();
    }
}

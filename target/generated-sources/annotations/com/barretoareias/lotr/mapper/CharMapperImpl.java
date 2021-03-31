package com.barretoareias.lotr.mapper;

import com.barretoareias.lotr.dto.CharDTO;
import com.barretoareias.lotr.dto.CharDTO.CharDTOBuilder;
import com.barretoareias.lotr.entity.Char;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-31T19:01:01-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (AdoptOpenJDK)"
)
public class CharMapperImpl implements CharMapper {

    @Override
    public Char toEntity(CharDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Char char1 = new Char();

        char1.setBirth( dto.getBirth() );
        char1.setCulture( dto.getCulture() );
        char1.setDeath( dto.getDeath() );
        char1.setId( dto.getId() );
        char1.setName( dto.getName() );
        char1.setTitle( dto.getTitle() );

        return char1;
    }

    @Override
    public CharDTO toDTO(Char entity) {
        if ( entity == null ) {
            return null;
        }

        CharDTOBuilder charDTO = CharDTO.builder();

        charDTO.birth( entity.getBirth() );
        charDTO.culture( entity.getCulture() );
        charDTO.death( entity.getDeath() );
        charDTO.id( entity.getId() );
        charDTO.name( entity.getName() );
        charDTO.title( entity.getTitle() );

        return charDTO.build();
    }
}

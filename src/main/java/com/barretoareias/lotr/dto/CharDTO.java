package com.barretoareias.lotr.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.barretoareias.lotr.entity.Culture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharDTO {
    
    private Long id;

    @NotNull
    @Size(min = 1,max = 50)
    private String name;

    @NotNull
    @Size(min = 1,max = 100)
    private String title;

    @NotNull
    private LocalDate birth;

    private LocalDate death;

    @NotNull
    private Culture culture;
}

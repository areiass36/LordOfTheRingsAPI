package com.barretoareias.lotr.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CultureDTO {
    
    private Long id;

    @NotNull
    @Size(min=1,max=50)
    private String name;
}

package com.barretoareias.lotr.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Char {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50,unique = true)
    @NotNull
    private String name;

    @Column(nullable = false,length = 100)
    private String title;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = true)
    private LocalDate death;

    @ManyToOne
    @JoinColumn(name = "culture",referencedColumnName = "id",nullable = false)
    private Culture culture;
    
}

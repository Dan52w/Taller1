package com.example.taller1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Aeropuertos")
@Getter
@Setter
public class Aeropuerto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String ciudad;
    private String pais;

    @OneToMany(mappedBy = "idAeropuerto")
    private List<Aeropuerto> aeropuertos;
}

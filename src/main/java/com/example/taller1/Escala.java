package com.example.taller1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Escalas")
@Getter
@Setter
public class Escala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime tiempoEscala;

    @ManyToMany
    @JoinColumn(name = "idVuelo")
    private List<Vuelo> vuelos;

    @ManyToOne
    @JoinColumn(name = "idAeropuerto")
    private Aeropuerto aeropuerto;
}

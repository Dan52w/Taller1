package com.example.taller1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Aerolineas")
@Getter
@Setter
public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String codigoAerolinea;
    private String paisOrigen;

    @ManyToOne
    @JoinColumn(name = "idVuelos")
    private Vuelo vuelos;
}

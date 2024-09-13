package com.example.taller1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Vuelos")
@Getter
@Setter
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private LocalDateTime fechaSalida;

    @Column(nullable = false)
    private LocalDateTime horaLlegada;

    @Column(nullable = false)
    private LocalDateTime duracion;

    @Column(nullable = false)
    private int capacidad;

    @OneToMany(mappedBy = "vuelos")
    private List<Aeropuerto> aeropuertos;

    @OneToMany(mappedBy = "vuelos")
    private List<Aerolinea> aerolineas;

    @ManyToMany(mappedBy = "idReservas")
    private List<Reserva> reservas;
}

package com.example.taller1;

import  jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Pasajeros")
@Getter
@Setter
public class Pasajero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private int cedula;

    @Column(nullable = false)
    private LocalDate fechaVuelo;

    @OneToMany(mappedBy = "pasajeros")
    private List<Reserva> reservas;
}

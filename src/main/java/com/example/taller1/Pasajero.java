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

    private String nombre;
    private String apellido;
    private int edad;
    private String sexo;
    private int cedula;
    private LocalDate fechaVuelo;

    @OneToMany(mappedBy = "pasajeros")
    private List<Reserva> reservas;
}

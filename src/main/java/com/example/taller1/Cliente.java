package com.example.taller1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Clientes")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String apellido;
    private String dirreccion;
    private int telefono;
    private String email;
    private LocalDateTime fechaNacimiento;

    @OneToMany(mappedBy = "clientes")
    private List<Reserva> reservas;
}
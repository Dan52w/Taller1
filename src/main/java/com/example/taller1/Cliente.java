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

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String dirreccion;

    @Column(nullable = false)
    private int telefono;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime fechaNacimiento;

    @OneToMany(mappedBy = "clientes")
    private List<Reserva> reservas;
}

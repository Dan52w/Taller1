package com.example.taller1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reservas")
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idVuelo")
    private Vuelo vuelo;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    private LocalDateTime fechaReserva;
    private int numeroPasajeros;
}

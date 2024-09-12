package com.example.taller1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Reservas")
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente clientes;

    private LocalDateTime fechaReserva;
    private int numeroPasajeros;

    @ManyToOne
    @JoinColumn(name = "idPasajero")
    private Pasajero pasajeros;

    @ManyToMany
    @JoinTable(
            name = "ReservaVuelos",
            joinColumns = @JoinColumn(name = "idVuelo",  referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "idReserva", referencedColumnName = "id")
    )

    private List<Vuelo> vuelos;
}

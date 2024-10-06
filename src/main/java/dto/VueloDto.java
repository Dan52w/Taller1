package dto;

import java.time.LocalDateTime;

public record VueloDto(Long id,
                       String origen,
                       String destino,
                       LocalDateTime fechaSalida,
                       LocalDateTime horaLlegada,
                       LocalDateTime duracion,
                       int capacidad) {
}

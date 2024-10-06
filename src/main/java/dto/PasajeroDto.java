package dto;

import java.time.LocalDate;

public record PasajeroDto(Long id,
                          String nombre,
                          String apellido,
                          int edad,
                          String sexo,
                          int cedula,
                          LocalDate fechaVuelo) {
}

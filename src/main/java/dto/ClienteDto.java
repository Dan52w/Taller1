package dto;

import java.time.LocalDateTime;

public record ClienteDto(String nombre,
                         String apellido,
                         String dirreccion,
                         int telefono,
                         String email,
                         LocalDateTime fechaNacimiento) {
}

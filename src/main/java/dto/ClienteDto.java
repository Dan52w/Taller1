package dto;

import java.time.LocalDateTime;

public record ClienteDto(Long id,
                         String nombre,
                         String apellido,
                         String dirreccion,
                         int telefono,
                         String email,
                         LocalDateTime fechaNacimiento) {
}

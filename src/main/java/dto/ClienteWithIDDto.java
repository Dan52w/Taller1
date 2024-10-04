package dto;

import java.time.LocalDateTime;

public record ClienteWithIDDto(Long Id,
                               String nombre,
                               String apellido,
                               String dirreccion,
                               int telefono,
                               String email,
                               LocalDateTime fechaNacimiento) {
}

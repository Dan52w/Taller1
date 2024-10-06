package dto;

import java.time.LocalDateTime;

public record ReservaDto(Long id,
                         LocalDateTime fechaReserva,
                         int numeroPasajeros) {
}

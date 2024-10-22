package respositoy;

import com.example.taller1.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRespository extends JpaRepository<Role, Long> {
}

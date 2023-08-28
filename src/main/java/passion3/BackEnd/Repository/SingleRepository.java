package passion3.BackEnd.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import passion3.BackEnd.Entity.Single;

import java.util.Optional;

public interface SingleRepository extends JpaRepository<Single, Long> {
    Optional<Single> findByMenuName(String menuName);
}


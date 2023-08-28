package passion3.BackEnd.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import passion3.BackEnd.Entity.Setmenu;
import java.util.Optional;

public interface SetmenuRepository extends JpaRepository<Setmenu, Long> {
    Optional<Setmenu> findByMenuName(String menuName);
}


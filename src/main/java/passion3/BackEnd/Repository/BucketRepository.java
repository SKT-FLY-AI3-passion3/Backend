package passion3.BackEnd.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import passion3.BackEnd.Entity.Bucket;
import passion3.BackEnd.Entity.Setmenu;
import passion3.BackEnd.dto.MenuLookupDTO;

import java.util.List;
import java.util.Optional;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
    Optional<Bucket> findByMain(String main);

    @Query("SELECT SUM(b.price) FROM Bucket b")
    Integer getTotalPrice();

    @Query("SELECT new passion3.BackEnd.dto.MenuLookupDTO(t.main, t.count) FROM Bucket t")
    List<MenuLookupDTO> findMainAndCount();
}


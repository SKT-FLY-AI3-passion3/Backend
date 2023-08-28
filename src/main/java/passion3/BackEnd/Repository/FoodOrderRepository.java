package passion3.BackEnd.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import passion3.BackEnd.Entity.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
}
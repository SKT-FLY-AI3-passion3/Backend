package passion3.BackEnd.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passion3.BackEnd.Repository.FoodOrderRepository;

@Service
public class FoodOrderService {
    private final FoodOrderRepository foodOrderRepository;

    @Autowired
    public FoodOrderService(FoodOrderRepository foodOrderRepository) {
        this.foodOrderRepository = foodOrderRepository;
    }

    public void deleteAllRecords() {
        foodOrderRepository.deleteAll();
    }
}
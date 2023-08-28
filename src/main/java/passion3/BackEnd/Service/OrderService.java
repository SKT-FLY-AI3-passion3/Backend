package passion3.BackEnd.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passion3.BackEnd.Entity.Bucket;
import passion3.BackEnd.Entity.FoodOrder;
import passion3.BackEnd.Entity.Single;
import passion3.BackEnd.Repository.BucketRepository;
import passion3.BackEnd.Repository.FoodOrderRepository;
import passion3.BackEnd.Repository.SingleRepository;
import passion3.BackEnd.dto.FoodOrderDTO;
import passion3.BackEnd.dto.OrderRequestDTO;
import passion3.BackEnd.utils.JsonUtils;
import java.util.Optional;

import static passion3.BackEnd.utils.JsonUtils.parseRefinedJson;

@Service
public class OrderService {

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private SingleRepository singleRepository;

    public FoodOrderDTO parseOrder(String invalidJson) throws Exception {
        FoodOrderDTO dto = parseRefinedJson(invalidJson, FoodOrderDTO.class);
        return dto;
    }

    public void processSingleOrder(FoodOrderDTO orderDto) {
        if (orderDto == null) return;
        if (orderDto.getMenu().equals("null") && orderDto.getCount()==null) return;;
        FoodOrder foodOrder = new FoodOrder();

        foodOrder.setMenu(orderDto.getMenu());
        if(orderDto.getCount() == null) foodOrder.setCount(1);
        else foodOrder.setCount(orderDto.getCount());
        foodOrderRepository.save(foodOrder);

        Bucket bucket = new Bucket();

        Optional<Single> menuPriceOpt = singleRepository.findByMenuName(orderDto.getMenu());
        if (menuPriceOpt.isPresent()) {
            bucket.setPrice(menuPriceOpt.get().getPrice());
        } else {
            // 메뉴 가격 정보가 없을 경우 기본값 or 에러 처리
            bucket.setPrice(0);
        }
        bucket.setMain(orderDto.getMenu());
        bucket.setCount(foodOrder.getCount());
        if (orderDto.isSetMenu()) {
            bucket.setSide("프렌치프라이");
            bucket.setDrink("코카콜라");
            bucket.setPrice((bucket.getPrice()+2100+2000)* foodOrder.getCount());
        }
        else {
            bucket.setSide(null);
            bucket.setDrink(null);
            bucket.setPrice((bucket.getPrice())* foodOrder.getCount());
        }
        bucketRepository.save(bucket);
    }
}


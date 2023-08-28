package passion3.BackEnd.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passion3.BackEnd.Entity.Bucket;
import passion3.BackEnd.Entity.Setmenu;
import passion3.BackEnd.Repository.BucketRepository;
import passion3.BackEnd.Repository.SetmenuRepository;
import passion3.BackEnd.dto.ChangeRequestDTO;
import passion3.BackEnd.dto.FoodChangeDTO;


import java.util.Optional;

import static passion3.BackEnd.utils.JsonUtils.parseRefinedJson;

@Service
public class ChangeService {

    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private SetmenuRepository setmenuRepository;


    public FoodChangeDTO parseChange(String invalidJson) throws Exception {
        FoodChangeDTO dto = parseRefinedJson(invalidJson, FoodChangeDTO.class);
        return dto;
    }
//    public void processChange(ChangeRequestDTO orderRequest) {
//        Change(orderRequest.getOrder1());
//        Change(orderRequest.getOrder2());
//        Change(orderRequest.getOrder3());
//    }

    public void Change(FoodChangeDTO orderDto) {
        if (orderDto.getMenu() == null) return;

        Optional<Bucket> bucketOpt = bucketRepository.findByMain(orderDto.getMenu());
        bucketOpt.ifPresent(bucket -> {
            if (orderDto.getSide() != null) {
                bucket.setSide(orderDto.getSide());
                bucket.setPrice(bucket.getPrice()+(getPriceByMenuName(orderDto.getSide()))*bucket.getCount());
            }
            if (orderDto.getDrink() != null) {
                bucket.setDrink(orderDto.getDrink());
                bucket.setPrice(bucket.getPrice()+(getPriceByMenuName(orderDto.getDrink()))*bucket.getCount());
            }
            bucketRepository.save(bucket);
        });
    }

    public Integer getPriceByMenuName(String menuName) {
        return setmenuRepository.findByMenuName(menuName)
                .map(Setmenu::getPrice)
                .orElse(null); // 혹은 기본 가격을 반환할 수 있습니다.
    }
}


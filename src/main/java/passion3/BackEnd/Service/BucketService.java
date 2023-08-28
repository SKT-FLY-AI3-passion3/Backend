package passion3.BackEnd.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passion3.BackEnd.Repository.BucketRepository;

@Service
public class BucketService {
    private final BucketRepository bucketRepository;

    @Autowired
    public BucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    public void deleteAllRecords() {
        bucketRepository.deleteAll();
    }

    public Integer calculateTotalPrice() {
        return bucketRepository.getTotalPrice();
    }

}
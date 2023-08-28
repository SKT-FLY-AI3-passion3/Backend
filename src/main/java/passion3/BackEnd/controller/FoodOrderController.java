package passion3.BackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import passion3.BackEnd.Service.BucketService;
import passion3.BackEnd.Service.ChangeService;
import passion3.BackEnd.Service.FoodOrderService;
import passion3.BackEnd.Service.OrderService;
import passion3.BackEnd.dto.ChangeDTO;
import passion3.BackEnd.dto.RequestDTO;

@RestController
@RequestMapping("/food")
public class FoodOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ChangeService changeService;
    @Autowired
    private BucketService bucketService;
    @Autowired
    private FoodOrderService foodOrderService;

    @PostMapping("/orders")
    public ResponseEntity<?> placeOrders(@RequestBody RequestDTO request) {
        orderService.processOrder(request.getOrders());
        return ResponseEntity.ok("Orders processed");
    }

    @PostMapping("/change")
    public ResponseEntity<?> changeOrders(@RequestBody ChangeDTO request) {
        changeService.processChange(request.getOrders());
        return ResponseEntity.ok("Orders changed");
    }
    @GetMapping("/total")
    public ResponseEntity<Integer> getTotalPrice() {
        Integer totalPrice = bucketService.calculateTotalPrice();
        return ResponseEntity.ok(totalPrice);
    }
    @DeleteMapping("/clear")
    public ResponseEntity<?> clearAllTables() {
        bucketService.deleteAllRecords();
        foodOrderService.deleteAllRecords();
        return ResponseEntity.ok("Both Bucket and FoodOrder tables cleared");
    }
}



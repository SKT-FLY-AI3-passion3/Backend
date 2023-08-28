package passion3.BackEnd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import passion3.BackEnd.Service.BucketService;
import passion3.BackEnd.Service.ChangeService;
import passion3.BackEnd.Service.FoodOrderService;
import passion3.BackEnd.Service.OrderService;
import passion3.BackEnd.dto.ChangeDTO;
import passion3.BackEnd.dto.FoodOrderDTO;
import passion3.BackEnd.dto.OrderRequestDTO;
import passion3.BackEnd.dto.RequestDTO;

import java.util.Map;

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

    @PostMapping("/test")
    public ResponseEntity<String> printReceivedJson(@RequestBody String jsonPayload) {
        System.out.println(jsonPayload);
        return ResponseEntity.ok("JSON printed successfully");
    }

    @PostMapping("/orders")
    public ResponseEntity<String> processOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws Exception {
        FoodOrderDTO order1Detail = orderService.parseOrder(orderRequestDTO.getOrder1());
        FoodOrderDTO order2Detail = orderService.parseOrder(orderRequestDTO.getOrder2());
        FoodOrderDTO order3Detail = orderService.parseOrder(orderRequestDTO.getOrder3());

        if(order1Detail != null && !order1Detail.isInvalid()) {
            orderService.processSingleOrder(order1Detail);
        }

        if(order2Detail != null && !order2Detail.isInvalid()) {
            orderService.processSingleOrder(order2Detail);
        }

        if(order3Detail != null && !order3Detail.isInvalid()) {
            orderService.processSingleOrder(order3Detail);
        }

        return ResponseEntity.ok("order success.");
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



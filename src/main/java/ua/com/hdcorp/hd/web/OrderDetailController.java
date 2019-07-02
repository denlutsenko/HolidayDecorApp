package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.hdcorp.hd.data.Error;
import ua.com.hdcorp.hd.dto.OrderDetailDto;
import ua.com.hdcorp.hd.service.interf.IOrderDetailService;

@RestController
@CrossOrigin
public class OrderDetailController {

    private final IOrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(final IOrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    //Method saves order and details of order
    @CrossOrigin
    @PostMapping(value = "/account/orders/addOrder")
    public ResponseEntity<?> saveOrderDetails(@RequestBody OrderDetailDto orderDetailDto) {
        orderDetailService.saveOrderWithDetails(orderDetailDto);
        try {
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(400, "Не удается сохранить."));
        }
    }
}

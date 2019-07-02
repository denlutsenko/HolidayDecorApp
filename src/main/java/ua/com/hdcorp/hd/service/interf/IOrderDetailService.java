package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.dto.OrderDetailDto;
import ua.com.hdcorp.hd.model.OrderDetail;


public interface IOrderDetailService {
    void addOrderDetail(OrderDetail orderDetail);
    void saveOrderWithDetails(OrderDetailDto orderDetailDto);
}

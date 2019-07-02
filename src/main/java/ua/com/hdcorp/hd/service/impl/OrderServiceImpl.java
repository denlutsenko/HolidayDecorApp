package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.model.Order;
import ua.com.hdcorp.hd.repository.OrderRepository;
import ua.com.hdcorp.hd.service.interf.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order addOrder(Order order) {
      return orderRepository.save(order);
    }
}

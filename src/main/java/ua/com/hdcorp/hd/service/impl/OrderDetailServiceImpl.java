package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hdcorp.hd.dto.OrderDetailDto;
import ua.com.hdcorp.hd.dto.PostcardDto;
import ua.com.hdcorp.hd.model.*;
import ua.com.hdcorp.hd.repository.OrderDetailRepository;
import ua.com.hdcorp.hd.service.interf.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final IDiscountService discountService;
    private final IClientStoreService storeService;
    private final IClientService clientService;
    private final IOrderService orderService;
    private final IPostcardService postcardService;
    private final IRemainsService remainsService;


    @Autowired
    public OrderDetailServiceImpl(final OrderDetailRepository orderDetailRepository,
                                  final IDiscountService discountService,
                                  final IClientStoreService storeService,
                                  final IClientService clientService,
                                  final IOrderService orderService,
                                  final IPostcardService postcardService,
                                  final IRemainsService remainsService) {
        this.orderDetailRepository = orderDetailRepository;
        this.discountService = discountService;
        this.storeService = storeService;
        this.clientService = clientService;
        this.orderService = orderService;
        this.postcardService = postcardService;
        this.remainsService = remainsService;
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    @Transactional
    public void saveOrderWithDetails(OrderDetailDto orderDetailDto) {
        Date orderDate = new Date();
        BigDecimal orderSum = getOrderSum(orderDetailDto.getPostcards());
        Discount disc = discountService.getOrderDiscount(orderSum);
        BigDecimal sumWithDiscount = getOrderSumWithDiscount(orderSum, disc);
        Client client = clientService.getClient(orderDetailDto.getClientId());
        Order order = orderService.addOrder(new Order(orderDate, orderSum, sumWithDiscount, client, disc));

        for (PostcardDto pp : orderDetailDto.getPostcards()) {
            Postcard p = postcardService.findOne(pp.getPostcardId());
            addOrderDetail(new OrderDetail(p, pp.getQuantity(), order));
            pp.setQuantity(-Math.abs(pp.getQuantity()));
            Remain remain = remainsService.updateRemain(pp);
            remainsService.saveRemain(remain);
        }

    }


    private BigDecimal getOrderSum(List<PostcardDto> postcards) {
        return postcards.stream().map(PostcardDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * calculate order sum with discount, example: 100$-2% = 98 $.
     *
     * @param orderSum
     * @return
     */
    private BigDecimal getOrderSumWithDiscount(BigDecimal orderSum, Discount disc) {
        BigDecimal discSum = orderSum.multiply(BigDecimal.valueOf(disc.getDiscountPercent()))
                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        return orderSum.subtract(discSum).setScale(2, RoundingMode.HALF_UP);
    }

}

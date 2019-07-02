package ua.com.hdcorp.hd.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "orders")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Column(name = "order_sum", nullable = false)
    private BigDecimal orderSum;

    @ManyToOne()
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @Column(name = "order_sum_with_discount")
    private BigDecimal orderSumWithDiscount;

    @Column(name = "comment")
    private String comment;

    @Column(name = "order_date")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Order() {
    }

    public Order(Date orderDate, BigDecimal orderSum, BigDecimal orderSumWithDiscount, Client client, Discount discount) {
        this.orderSum = orderSum;
        this.discount = discount;
        this.orderSumWithDiscount = orderSumWithDiscount;
        this.orderDate = orderDate;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public BigDecimal getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public BigDecimal getOrderSumWithDiscount() {
        return orderSumWithDiscount;
    }

    public void setOrderSumWithDiscount(BigDecimal orderSumWithDiscount) {
        this.orderSumWithDiscount = orderSumWithDiscount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}

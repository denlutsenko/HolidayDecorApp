package ua.com.hdcorp.hd.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discount")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sum_barrier")
    private BigDecimal sumBarrier;

    @Column(name = "count_of_discount")
    private int discountPercent;

    @OneToMany(mappedBy = "discount")
    private List<Order> orderList = new ArrayList<>();

    public Discount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSumBarrier() {
        return sumBarrier;
    }

    public void setSumBarrier(BigDecimal sumBarrier) {
        this.sumBarrier = sumBarrier;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "sumBarrier=" + sumBarrier +
                ", discountPercent=" + discountPercent +
                '}';
    }
}

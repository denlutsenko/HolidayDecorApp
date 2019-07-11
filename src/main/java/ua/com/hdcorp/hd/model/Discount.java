package ua.com.hdcorp.hd.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "sum_barrier", nullable = false, unique = true)
    private BigDecimal sumBarrier;

    @Column(name = "discount_percentage")
    private Integer discountPercentage;
}
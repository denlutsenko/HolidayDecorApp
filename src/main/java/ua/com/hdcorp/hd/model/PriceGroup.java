package ua.com.hdcorp.hd.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "price_groups")
public class PriceGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;
}
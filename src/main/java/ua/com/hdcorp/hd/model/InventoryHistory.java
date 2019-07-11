package ua.com.hdcorp.hd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "inventory_history")
public class InventoryHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postcard_id")
    private Postcard postcard;

    @Column(name = "month_year", nullable = false)
    private Date monthYear;

    @Column(name = "curr_produced")
    private Integer currProduced;

    @Column(name = "curr_ordered")
    private Integer currOrdered;

    @Column(name = "first_day_remain")
    private Integer firstDayRemain;

    @Column(name = "last_day_remain")
    private Integer lastDayRemain;
}
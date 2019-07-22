package ua.com.hdcorp.hd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "prices")
@Getter
@Setter
public class Price implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postcard_id", nullable = false)
    private Postcard postcard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_group_id", nullable = false)
    private PriceGroup priceGroup;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
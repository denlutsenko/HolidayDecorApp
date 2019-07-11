package ua.com.hdcorp.hd.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inventories")
public class Inventory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postcard_id")
    private Postcard postcard;

    @Column(name = "remain", columnDefinition = "int default 0", nullable = false)
    private Integer remain;
}
package ua.com.hdcorp.hd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "postcards")
public class Postcard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "photo_location")
    private String photo_location;

    @Column(name = "photo_name", length = 30)
    private String photo_name;

    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private PostcardType type;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "postcard")
    private Inventory inventory;

    @Column(name = "active_status", columnDefinition="tinyint(1) default true", nullable = false)
    private Boolean activeStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postcard")
    private List<Price> prices = new ArrayList<>();
}
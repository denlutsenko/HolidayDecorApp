package ua.com.hdcorp.hd.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stores")
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "street", length = 50)
    private String street;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "active_status", columnDefinition="tinyint(1) default true", nullable = false)
    private Boolean activeStatus;
}
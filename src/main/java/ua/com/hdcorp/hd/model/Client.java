package ua.com.hdcorp.hd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "comment", length = 500)
    private String comment;

    @Column(name = "active_status", columnDefinition="tinyint(1) default true", nullable = false)
    private Boolean activeStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Store> store = new ArrayList<>();
}
package ua.com.hdcorp.hd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Size(max = 100)
    @Column(name = "company_name")
    private String companyName;

    @Size(max = 50)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 30)
    @Column(name = "phone")
    private String phone;

    @Size(max = 50)
    @Column(name = "email")
    private String email;

    @Size(max = 500)
    @Column(name = "comment")
    private String comment;

    @NotNull
    @Column(name = "active_status", columnDefinition = "tinyint(1) default true")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean activeStatus = true;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = {CascadeType.PERSIST})
    private List<Store> stores;
}
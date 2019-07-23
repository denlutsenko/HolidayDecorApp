package ua.com.hdcorp.hd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "stores")
@Getter
@Setter
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Size(max = 50)
    @Column(name = "country")
    private String country;

    @NotNull
    @Size(max = 50)
    @Column(name = "city")
    private String city;

    @Size(max = 50)
    @Column(name = "street")
    private String street;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
    @Column(name = "active_status", columnDefinition = "tinyint(1) default true")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean activeStatus = true;
}
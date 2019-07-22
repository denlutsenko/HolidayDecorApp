package ua.com.hdcorp.hd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "postcards")
@Getter
@Setter
public class Postcard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

    @Column(name = "active_status", columnDefinition = "tinyint(1) default true", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean activeStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postcard")
    private List<Price> prices = new ArrayList<>();
}
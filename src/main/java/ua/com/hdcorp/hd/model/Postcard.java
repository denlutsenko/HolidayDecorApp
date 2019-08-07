package ua.com.hdcorp.hd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String photoLocation;

    @Column(name = "photo_name", length = 30)
    private String photoName;

    @NotNull
    @Column(name = "vendor_code")
    private String vendorCode;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PostcardType type;

    @Column(name = "active_status", columnDefinition = "tinyint(1) default true", nullable = false)
    @JsonIgnore
    private Boolean activeStatus = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postcard")
    private List<Price> prices = new ArrayList<>();

    public Postcard(){}

    public Postcard(String photoLocation, String photoName, @NotNull String vendorCode, @NotNull PostcardType type) {
        this.photoLocation = photoLocation;
        this.photoName = photoName;
        this.vendorCode = vendorCode;
        this.type = type;
    }
}
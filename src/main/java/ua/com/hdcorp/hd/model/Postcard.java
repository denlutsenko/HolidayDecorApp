package ua.com.hdcorp.hd.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "postcards")
public class Postcard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "photo_location")
    private String photoLocation;

    @Column(name = "photo_name", length = 70)
    private String photoName;

    @NotNull
    @Column(name = "vendor_code")
    private String vendorCode;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PostcardType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postcard")
    private List<Price> prices = new ArrayList<>();

    public Postcard() {
    }

    public Postcard(String photoLocation, String photoName, String vendorCode, PostcardType type) {
        this.photoLocation = photoLocation;
        this.photoName = photoName;
        this.vendorCode = vendorCode;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public PostcardType getType() {
        return type;
    }

    public void setType(PostcardType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
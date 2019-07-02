package ua.com.hdcorp.hd.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "postcards")
public class Postcard  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "photo_location_path")
    private String photoLocationPath;

    @Transient
    private byte[] photo;

    @Column(name = "vendor_code")

    private String vendorCode;

    @Column(name = "photo_name", length = 30)
    private String photoName;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PostcardType postcardType;


    @OneToOne(mappedBy = "postcard")
    private Remain remain;


    @Column(columnDefinition="tinyint(1) default true", name = "active_status", nullable = false)
    private boolean activeStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "postcard", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "postcard", fetch = FetchType.LAZY)
    private List<PostcardProduction> postcardList = new ArrayList<>();

    public Postcard() {
    }

    public Postcard(Long id){
        this.id = id;
    }

    /**
     * Save full info of postcard
     */
    public Postcard(String photoLocationPath, String photoName, String vendorCode , BigDecimal price, PostcardType postcardType, boolean activeStatus) {
        this.photoLocationPath = photoLocationPath;
        this.vendorCode = vendorCode;
        this.photoName = photoName;
        this.price = price;
        this.postcardType = postcardType;
        this.activeStatus = activeStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoLocationPath() {
        return photoLocationPath;
    }

    public void setPhotoLocationPath(String photoLocationPath) {
        this.photoLocationPath = photoLocationPath;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PostcardType getPostcardType() {
        return postcardType;
    }

    public void setPostcardType(PostcardType postcardType) {
        this.postcardType = postcardType;
    }

    public Remain getRemain() {
        return remain;
    }

    public void setRemain(Remain remain) {
        this.remain = remain;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<PostcardProduction> getPostcardList() {
        return postcardList;
    }

    public void setPostcardList(List<PostcardProduction> postcardList) {
        this.postcardList = postcardList;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }



}

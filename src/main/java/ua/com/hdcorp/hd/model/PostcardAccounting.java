package ua.com.hdcorp.hd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "postcard_accounting")
public class PostcardAccounting implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "photo_location_path")
    private String photoLocationPath;

    @Column(name = "photo_name")
    private String photo_name;

    @Transient
    private byte[] photo;

    @Column(name = "vendor_code")
    private String vendorCode;

    @Column(name = "postcard_type")
    private String postcardType;

    @Column(name = "p_type_id")
    private int pTypeId;

    @Column(name = "curr_produced")
    private int currProduced;

    @Column(name = "curr_ordered")
    private int currOrdered;

    @Column(name = "first_day_remain")
    private int firstDayRemain;

    @Column(name = "current_remain")
    private int currRemain;

    public PostcardAccounting() {
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public int getCurrProduced() {
        return currProduced;
    }

    public void setCurrProduced(int currProduced) {
        this.currProduced = currProduced;
    }

    public int getCurrOrdered() {
        return currOrdered;
    }

    public void setCurrOrdered(int currOrdered) {
        this.currOrdered = currOrdered;
    }

    public int getFirstDayRemain() {
        return firstDayRemain;
    }

    public void setFirstDayRemain(int firstDayRemain) {
        this.firstDayRemain = firstDayRemain;
    }

    public int getCurrRemain() {
        return currRemain;
    }

    public void setCurrRemain(int currRemain) {
        this.currRemain = currRemain;
    }

    public String getVerndorCode() {
        return vendorCode;
    }

    public void setVerndorCode(String verndorCode) {
        this.vendorCode = verndorCode;
    }

    public String getPostcardType() {
        return postcardType;
    }

    public void setPostcardType(String postcardType) {
        this.postcardType = postcardType;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getpTypeId() {
        return pTypeId;
    }

    public void setpTypeId(int pTypeId) {
        this.pTypeId = pTypeId;
    }


}
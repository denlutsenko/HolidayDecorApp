package ua.com.hdcorp.hd.dto;

import java.math.BigDecimal;

public class PostcardDto {
    private Long postcardId;
    private String photoPath;
    private String photoName;
    private BigDecimal price;
    private String vendorCode;
    private int quantity;

    public PostcardDto(){}

    public Long getPostcardId() {
        return postcardId;
    }

    public void setPostcardId(Long postcardId) {
        this.postcardId = postcardId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PostcardDto{" +
                "id=" + postcardId +
                ", photoPath='" + photoPath + '\'' +
                ", photoName='" + photoName + '\'' +
                ", price=" + price +
                ", vendorCode='" + vendorCode + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

package ua.com.hdcorp.hd.model.dto;

public class ProductionDto {
    private Long postcardId;
    private Integer quantity;

    public ProductionDto() {
    }

    public Long getPostcardId() {
        return postcardId;
    }

    public void setPostcardId(Long postcardId) {
        this.postcardId = postcardId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

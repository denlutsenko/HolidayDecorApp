package ua.com.hdcorp.hd.dto;

import java.util.List;

public class OrderDetailDto {

    private Long clientId;

    private List<PostcardDto> postcards;

    public OrderDetailDto(){}

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<PostcardDto> getPostcards() {
        return postcards;
    }

    public void setPostcards(List<PostcardDto> postcards) {
        this.postcards = postcards;
    }

}

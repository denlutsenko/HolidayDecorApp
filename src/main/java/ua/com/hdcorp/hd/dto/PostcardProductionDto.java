package ua.com.hdcorp.hd.dto;

import java.util.List;

public class PostcardProductionDto {

    private Long userId;
    private List<PostcardDto> postcards;

    public PostcardProductionDto(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<PostcardDto> getPostcards() {
        return postcards;
    }

    public void setPostcards(List<PostcardDto> postcards) {
        this.postcards = postcards;
    }

    @Override
    public String toString() {
        return "PostcardProductionDto{" +
                "userId=" + userId +
                ", postcards=" + postcards +
                '}';
    }
}

package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.dto.PostcardProductionDto;

import java.util.Date;

public interface IPostcardProductionService {
    void updatePostcardProduction(PostcardProductionDto postcardProductionList);
    void addPostcardProduction(int count, Date date, Long postcardId, Long userId);
}

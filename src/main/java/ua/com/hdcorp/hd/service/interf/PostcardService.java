package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.model.Postcard;

import java.util.List;

public interface PostcardService {
    Postcard getPostcardById(Long postcardId);

    List<Postcard> findAllActivePostcards();
}

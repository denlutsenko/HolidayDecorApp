package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.model.PostcardType;

import java.util.List;

public interface IPostcardTypeService {
    List<PostcardType> getPostcardTypes();
    PostcardType getPostcardType(Long id);
}

package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.model.PostcardType;
import ua.com.hdcorp.hd.repository.PostcardTypeRepository;
import ua.com.hdcorp.hd.service.interf.IPostcardTypeService;

import java.util.List;

@Service
public class PostcardTypeServiceImpl implements IPostcardTypeService {
    private final PostcardTypeRepository postcardType;

    @Autowired
    public PostcardTypeServiceImpl(final PostcardTypeRepository postcardType) {
        this.postcardType = postcardType;
    }


    @Override
    public List<PostcardType> getPostcardTypes() {
        return postcardType.findAll();
    }


    @Override
    public PostcardType getPostcardType(Long id) {
        return postcardType.getOne(id);
    }
}

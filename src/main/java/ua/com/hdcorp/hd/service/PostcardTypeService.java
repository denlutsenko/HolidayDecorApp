package ua.com.hdcorp.hd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.com.hdcorp.hd.exception.NotFoundException;
import ua.com.hdcorp.hd.model.PostcardType;
import ua.com.hdcorp.hd.repository.PostcardTypeRepository;

import static ua.com.hdcorp.hd.exception.NotFoundException.Message.POSTCARD_TYPE_NOT_FOUND;

@Service
public class PostcardTypeService {
    private final PostcardTypeRepository postcardTypeRepository;

    @Autowired
    public PostcardTypeService(PostcardTypeRepository postcardTypeRepository) {
        this.postcardTypeRepository = postcardTypeRepository;
    }

    public boolean isPostcardTypeExists(Long postcardTypeId) {
        return !StringUtils.isEmpty(postcardTypeId) && postcardTypeRepository.existsById(postcardTypeId);
    }

    public PostcardType findById(Long postcardTypeId) {
        return postcardTypeRepository.findById(postcardTypeId).orElseThrow(() -> new NotFoundException(POSTCARD_TYPE_NOT_FOUND, "Couldn't find such postcard type by Id"));
    }


}

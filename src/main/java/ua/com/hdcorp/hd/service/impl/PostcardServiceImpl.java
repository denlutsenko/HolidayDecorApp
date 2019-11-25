package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.exception.BadRequestException;
import ua.com.hdcorp.hd.exception.NotFoundException;
import ua.com.hdcorp.hd.model.Postcard;
import ua.com.hdcorp.hd.model.Status;
import ua.com.hdcorp.hd.repository.PostcardRepository;
import ua.com.hdcorp.hd.service.interf.PostcardService;

import java.util.List;

import static ua.com.hdcorp.hd.utils.Constants.POSTCARD_NOT_FOUND;

@Service
public class PostcardServiceImpl implements PostcardService {
    private final PostcardRepository postcardRepository;

    @Autowired
    public PostcardServiceImpl(PostcardRepository postcardRepository) {
        this.postcardRepository = postcardRepository;
    }

    @Override
    public Postcard getPostcardById(Long postcardId) {
        return postcardRepository.findByIdAndActiveStatus(postcardId)
                .orElseThrow(()-> new BadRequestException(POSTCARD_NOT_FOUND));
    }

    @Override
    public List<Postcard> findAllActivePostcards() {
        return postcardRepository.findAllActivePostcards();
    }
}

package ua.com.hdcorp.hd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.model.Postcard;
import ua.com.hdcorp.hd.repository.PostcardRepository;

import java.util.List;

@Service
public class PostcardService {

    private final PostcardRepository postcardRepository;

    @Autowired
    public PostcardService(PostcardRepository postcardRepository) {
        this.postcardRepository = postcardRepository;
    }

    public List<Postcard> getPostcards() {
        return postcardRepository.findAll();
    }
}
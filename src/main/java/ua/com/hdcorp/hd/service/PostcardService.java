package ua.com.hdcorp.hd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.exception.NotFoundException;
import ua.com.hdcorp.hd.model.Postcard;
import ua.com.hdcorp.hd.repository.PostcardRepository;
import ua.com.hdcorp.hd.util.EntityPatchHelper;

import java.util.List;
import java.util.Map;

import static ua.com.hdcorp.hd.exception.NotFoundException.Message.POSTCARD_NOT_FOUND;

@Service
public class PostcardService {

    private final PostcardRepository postcardRepository;
    private final EntityPatchHelper entityPatchHelper;

    @Autowired
    public PostcardService(PostcardRepository postcardRepository, EntityPatchHelper entityPatchHelper) {
        this.postcardRepository = postcardRepository;
        this.entityPatchHelper = entityPatchHelper;
    }

    public List<Postcard> getPostcards() {
        return postcardRepository.findAll();
    }

    public Postcard findById(Long postcardId) {
        return postcardRepository.findById(postcardId).orElseThrow(() -> new NotFoundException(POSTCARD_NOT_FOUND, "Such postcard is not found by ID"));
    }

    //TODO Write logic.
    public Postcard save(Postcard postcard) {
        return new Postcard();
    }

    public Postcard update(Long postcardId, Map<String, String> postcardPatch) {
        Postcard postcard = findById(postcardId);
        entityPatchHelper.patch(postcard, postcardPatch);
        return save(postcard);
    }

    public Postcard deactivate(Long postcardId) {
        Postcard postcard = findById(postcardId);
        postcard.setActiveStatus(false);
        return save(postcard);
    }

}
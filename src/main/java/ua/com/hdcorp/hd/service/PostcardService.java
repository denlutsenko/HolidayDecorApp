package ua.com.hdcorp.hd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.com.hdcorp.hd.exception.NotFoundException;
import ua.com.hdcorp.hd.model.Postcard;
import ua.com.hdcorp.hd.model.PostcardType;
import ua.com.hdcorp.hd.repository.PostcardRepository;
import ua.com.hdcorp.hd.util.ImageHelper;

import java.util.List;

import static ua.com.hdcorp.hd.exception.NotFoundException.Message.POSTCARD_TYPE_NOT_FOUND;

@Service
public class PostcardService {

    private final PostcardRepository postcardRepository;
    private final PostcardTypeService postcardTypeService;
    private final ImageHelper imageHelper;


    @Autowired
    public PostcardService(PostcardRepository postcardRepository, PostcardTypeService postcardTypeService, ImageHelper imageHelper) {
        this.postcardRepository = postcardRepository;
        this.postcardTypeService = postcardTypeService;
        this.imageHelper = imageHelper;
    }

    public List<Postcard> getPostcards() {
        return postcardRepository.findAll();
    }

    public Postcard save(MultipartFile file, String vendorCode, Long postcardTypeId) {
        if (!postcardTypeService.isPostcardTypeExists(postcardTypeId)) {
            throw new NotFoundException(POSTCARD_TYPE_NOT_FOUND, "Could not find postcard category by Id");
        }
        imageHelper.saveImage(file, postcardTypeId);

        String imageFolderPath = imageHelper.concatenateFolderPath(postcardTypeId).toString();
        String imageName = imageHelper.getImageName(file);
        PostcardType postcardType = postcardTypeService.findById(postcardTypeId);

        Postcard postcard = new Postcard(imageFolderPath, imageName, vendorCode, postcardType);

        Postcard createdPostcard = postcardRepository.save(postcard);
        postcardRepository.refresh(createdPostcard);
        return createdPostcard;
    }
}
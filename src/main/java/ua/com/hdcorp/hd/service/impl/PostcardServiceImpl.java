package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.com.hdcorp.hd.model.Postcard;
import ua.com.hdcorp.hd.model.PostcardType;
import ua.com.hdcorp.hd.model.Remain;
import ua.com.hdcorp.hd.photo.PhotoHandler;
import ua.com.hdcorp.hd.repository.PostcardRepository;
import ua.com.hdcorp.hd.service.interf.IPostcardService;
import ua.com.hdcorp.hd.service.interf.IPostcardTypeService;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class PostcardServiceImpl implements IPostcardService {
    private final PostcardRepository postcardRepository;
    private final IPostcardTypeService pTypeService;
    private final PhotoHandler pHandler;

    @Autowired
    public PostcardServiceImpl(final PostcardRepository postcardRepository,
                               final IPostcardTypeService pTypeService,
                               final PhotoHandler pHandler) {
        this.postcardRepository = postcardRepository;
        this.pTypeService = pTypeService;
        this.pHandler = pHandler;
    }

    @Override
    public Postcard findOne(Long id) {
        Postcard pp = postcardRepository.findByIdAndActiveStatus(id, true);
        String path = pp.getPhotoLocationPath() + File.separator + pp.getPhotoName();
        pp.setPhoto(PhotoHandler.getPhotoBytes(path));
        return pp;
    }

    // костыль
    @Override
    public List<Postcard> getActivePostcard(Long id) {
        List<Postcard> pp = postcardRepository.findAllByIdAndActiveStatus(id, true);
        if (pp != null && !pp.isEmpty()) {
            String path = pp.get(0).getPhotoLocationPath() + File.separator + pp.get(0).getPhotoName();
            pp.get(0).setPhoto(PhotoHandler.getPhotoBytes(path));
        }
        return pp;
    }


    @Override
    public List<Postcard> getPostcardsWithRemain() {
        List<Postcard> postcards = postcardRepository.findByActiveStatus(true);
        for (Postcard pp : postcards) {
            if (pp.getRemain() == null) {
                Remain remain = new Remain();
                pp.setRemain(remain);
            }
            String path = pp.getPhotoLocationPath() + File.separator + pp.getPhotoName();
            pp.setPhoto(PhotoHandler.getPhotoBytes(path));
        }
        return postcards;
    }

    @Transactional
    @Override
    public void saveNewPostcard(MultipartFile file, String vendorCode, BigDecimal price, Long categoryId) {
        String pName = "";
        String pPath = "";
        if (null != file && !file.isEmpty()) {
            pHandler.savePhoto(file, categoryId);
            pName = pHandler.getPhotoName(file);
            pPath = pHandler.getPhotoLocationPath(categoryId);
        }
        if (null == price) {
            price = new BigDecimal(0.00);
        }
        PostcardType pType = pTypeService.getPostcardType(categoryId);
        Postcard postcard = new Postcard(pPath, pName, vendorCode, price, pType, true);
        postcardRepository.save(postcard);
    }

    @Override
    public void updatePostcard(MultipartFile file, Long postcardId, String vendorCode, BigDecimal price, Long newCategory) {
        Postcard postcard = postcardRepository.getOne(postcardId);
        Long categoryId = postcard.getPostcardType().getId();
        if (null != file && !file.isEmpty() && Objects.requireNonNull(file.getOriginalFilename()).length() > 0) {
            pHandler.savePhoto(file, categoryId);
            postcard.setPhotoName(pHandler.getPhotoName(file));
            postcard.setPhotoLocationPath(pHandler.getPhotoLocationPath(categoryId));
        }
        if (null != vendorCode && !vendorCode.isEmpty()) {
            postcard.setVendorCode(vendorCode);
        }
        if (null != price) {
            postcard.setPrice(price);
        }
        if (null != newCategory) {
            postcard.setPostcardType(pTypeService.getPostcardType(newCategory));
        }
        postcardRepository.save(postcard);
    }

    @Override
    public Postcard findPostcard(Long id) {
        return postcardRepository.getOne(id);
    }

    @Override
    public void deletePostcard(Long id) {
        postcardRepository.updateActiveStatus(false, id);
    }
}

package ua.com.hdcorp.hd.service.interf;

import org.springframework.web.multipart.MultipartFile;
import ua.com.hdcorp.hd.model.Postcard;

import java.math.BigDecimal;
import java.util.List;

public interface IPostcardService {
    Postcard findOne(Long id);

    List<Postcard> getActivePostcard(Long id);

    List<Postcard> getPostcardsWithRemain();

    Postcard findPostcard(Long id);

    void deletePostcard(Long id);

    void saveNewPostcard(MultipartFile file, String vendorCode, BigDecimal price, Long categoryId);

    void updatePostcard(MultipartFile file, Long postcardId, String vendorCode, BigDecimal price, Long categoryId);
}

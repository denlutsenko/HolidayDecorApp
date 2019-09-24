package ua.com.hdcorp.hd.repository;

import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.PostcardType;

@Repository
public interface PostcardTypeRepository extends RefreshableRepository<PostcardType, Long> {
}

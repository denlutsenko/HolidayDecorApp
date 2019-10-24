package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Postcard;

import java.util.Optional;

@Repository
public interface PostcardRepository extends RefreshableRepository<Postcard, Long> {

    @Query(value = "SELECT * FROM postcards WHERE id=?1 And status=?2", nativeQuery = true)
    Optional<Postcard> findByIdAndActiveStatus(Long id, String status);
}

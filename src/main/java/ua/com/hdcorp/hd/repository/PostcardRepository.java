package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Postcard;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostcardRepository extends RefreshableRepository<Postcard, Long> {

    @Query(value = "SELECT * FROM postcards WHERE id=?1 AND status='ACTIVE'", nativeQuery = true)
    Optional<Postcard> findByIdAndActiveStatus(Long id);

    @Query(value = "SELECT * FROM postcards WHERE status='ACTIVE'", nativeQuery = true)
    List<Postcard> findAllActivePostcards();
}

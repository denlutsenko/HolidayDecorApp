package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hdcorp.hd.model.Postcard;

import java.util.List;

@Repository
public interface PostcardRepository extends JpaRepository<Postcard, Long> {

    List<Postcard> findByActiveStatus(boolean status);

    @Transactional
    @Modifying
    @Query(value = "update postcards p  set p.active_status = ?1 where p.id = ?2", nativeQuery = true)
    void updateActiveStatus(boolean status, Long id);

    //TODO: костыль
    List<Postcard> findAllByIdAndActiveStatus(Long id, boolean flag);

    Postcard findByIdAndActiveStatus(Long id, boolean flag);

}

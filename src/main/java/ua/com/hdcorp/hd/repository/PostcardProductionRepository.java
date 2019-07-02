package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.PostcardProduction;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface PostcardProductionRepository extends JpaRepository<PostcardProduction, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO postcards_production(quantity, production_date, postcard_id, user_id) VALUES " +
            "(?, ?, ?, ?)", nativeQuery = true)
    void addProduction(int count, Date date, Long postcardId, Long userId);

}

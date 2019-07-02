package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Remain;

@Repository
public interface RemainRepository extends JpaRepository<Remain, Long> {

    @Query(value = "SELECT * FROM remains WHERE postcard_id=?", nativeQuery = true)
    Remain getRemainByPostcardId(Long postcardId);
}

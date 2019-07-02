package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    List<User> findByActiveStatus(boolean status);

    User findByIdAndActiveStatus(Long id, boolean flag);

    @Transactional
    @Modifying
    @Query(value = "update users u  set u.active_status = ?1 where u.id = ?2", nativeQuery = true)
    void updateActiveStatus(boolean status, Long id);
}

package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface RefreshableRepository<T, ID> extends JpaRepository<T, ID> {

    void refresh(T entity);
}
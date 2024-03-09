package com.jimmyss.slabel.repository;

import com.jimmyss.slabel.entity.Dataset;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface DatasetRepository extends JpaRepository<Dataset, Integer> {
    Page<Dataset> findByUserIdOrderByCreatedDateDesc(Integer userId, Pageable limit);

    Optional<Dataset> findByUserIdAndId(Integer userId, Integer id);
}

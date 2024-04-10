package com.jimmyss.slabel.repository;


import com.jimmyss.slabel.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ModelRepository extends JpaRepository<Model, Integer> {
    List<Model> findModelsByDatasetId(Integer datasetId);

    Optional<Model> findModelById(Integer modelId);
}

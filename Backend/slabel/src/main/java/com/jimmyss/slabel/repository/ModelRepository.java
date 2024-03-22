package com.jimmyss.slabel.repository;


import com.jimmyss.slabel.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ModelRepository extends JpaRepository<Model, Integer> {
    List<Model> findModelsByDatasetId(Integer datasetId);
}

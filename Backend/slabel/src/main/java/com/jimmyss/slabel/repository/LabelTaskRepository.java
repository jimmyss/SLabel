package com.jimmyss.slabel.repository;


import com.jimmyss.slabel.entity.LabelTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface LabelTaskRepository extends JpaRepository<LabelTask, Integer> {

}

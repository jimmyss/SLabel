package com.jimmyss.slabel.repository;

import com.jimmyss.slabel.entity.LabelTaskPersonalInfo;
import com.jimmyss.slabel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface TaskUserRepository extends JpaRepository<LabelTaskPersonalInfo, Integer> {
    List<LabelTaskPersonalInfo> findByUserId(Integer user_id);

    List<LabelTaskPersonalInfo> findByLabelTaskId(Integer labelTaskId);

    Optional<LabelTaskPersonalInfo> findByLabelTaskIdAndUserId(Integer labelTaskId, Integer userId);

}

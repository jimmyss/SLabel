package com.jimmyss.slabel.entity;

import com.jimmyss.slabel.service.LabelTaskService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Entity
@Data
@Table(name = "label_task_personal_info")
@AllArgsConstructor
@NoArgsConstructor
public class LabelTaskPersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "label_task_id")
    private LabelTask labelTask;

    @Column(name = "role")
    private Role role;


    public enum Role{
        Admin,
        Annotator
    }

    public LabelTaskPersonalInfo(User user, LabelTask labelTask){
        this.user=user;
        this.labelTask=labelTask;
    }
}

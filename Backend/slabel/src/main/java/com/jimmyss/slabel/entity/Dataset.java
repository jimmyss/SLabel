package com.jimmyss.slabel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "datasetInfo")
@AllArgsConstructor
public class Dataset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;
    private String datasetName;
    private String description;
    private Integer totalNum;
    private Integer labeledNum;
    private String saveDir;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    @OneToMany(mappedBy = "dataset", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<LabelTask> labelTasks;
    @OneToMany(mappedBy = "dataset", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Model> models;
    public Dataset(String datasetName,
                   String description,
                   User user,
                   Date createdDate) {
        this.datasetName=datasetName;
        this.description=description;
        this.user=user;
        this.createdDate=createdDate;
    }

    public Dataset() {

    }
}

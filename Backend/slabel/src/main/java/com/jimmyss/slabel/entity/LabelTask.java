package com.jimmyss.slabel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name="labelTask")
@AllArgsConstructor
public class LabelTask {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="dataset_id")
    private Dataset dataset;
    @OneToMany(mappedBy = "labelTask", cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
    private Set<LabelTaskPersonalInfo> labelTaskInfos;
    private String modelDir;
    private String title;
    private String description;
    private String direction;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name="createdDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    @Column(name="deadline")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date deadline;

    public LabelTask() {

    }

    public LabelTask(String title,
                     String description,
                     String direction,
                     Date deadline,
                     Date createdDate,
                     Status status){
        this.title=title;
        this.description=description;
        this.direction=direction;
        this.deadline=deadline;
        this.createdDate=createdDate;
        this.status=status;
    }


    public enum Status{
        Proceeding, Finished
    };

}

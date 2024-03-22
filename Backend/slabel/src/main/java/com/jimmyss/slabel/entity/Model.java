package com.jimmyss.slabel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "modelInfo")
@AllArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;
    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="dataset_id")
    private Dataset dataset;
    private String modelName;
    private String description;
    private Float modelIou;
    private Integer epoch;
    private Long trainTime;
    private Status status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String saveDir;

    public enum Status{
        Created, Proceeding, Paused, Stopped
    };


    //create a new empty model
    public Model(Dataset dataset, String modelName, String description){
        this.dataset=dataset;
        this.modelName=modelName;
        this.description=description;
        this.status=Status.Created;
        this.createdDate=new Date();
    }

    public Model() {

    }
}

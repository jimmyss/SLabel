package com.jimmyss.slabel.vo;

import com.jimmyss.slabel.entity.LabelTask;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelTaskVO {
    @Nullable
    private Integer num;

    @Nullable
    private String title;

    @Nullable
    private String description;

    @Nullable
    private String direction;

    @Nullable
    private Date deadline;

    @Nullable
    private String confirmInfo;

    @Nullable
    private LabelTask.Status status;

    @Nullable
    private List<String> userNameList;

}

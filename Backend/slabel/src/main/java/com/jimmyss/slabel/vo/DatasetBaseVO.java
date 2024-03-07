package com.jimmyss.slabel.vo;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatasetBaseVO {
    @Nullable
    private Integer num;
    @Nullable
    private String confirmInfo;
    @Nullable
    private String uploadDir;
    @Nullable
    private String datasetName;
    @Nullable
    private String description;
    @Nullable
    private String downloadDir;
}

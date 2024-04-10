package com.jimmyss.slabel.vo;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelVO {
    @NotEmpty
    private String modelName;
    @Nullable
    private String description;
    @NotNull
    private Integer datasetId;
}

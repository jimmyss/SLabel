package com.jimmyss.slabel.service;

import com.jimmyss.slabel.component.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface ModelService {

    BaseResponse getModels(HttpServletRequest request);

    BaseResponse createModel(HttpServletRequest request, String modelName, String description, Integer datasetId);

    BaseResponse deleteModel(HttpServletRequest request, Integer modelId);

    BaseResponse updateModel(HttpServletRequest request, String newModelName, String description);

    BaseResponse updataModelStatus(HttpServletRequest request, Integer statusId);
}

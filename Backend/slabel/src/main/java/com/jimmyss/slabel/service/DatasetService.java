package com.jimmyss.slabel.service;

import com.jimmyss.slabel.component.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface DatasetService {
    BaseResponse getDatasets(Integer number, HttpServletRequest request);

    BaseResponse downloadDataset(Integer userId, Integer datasetId, String downloadDir);

    BaseResponse deleteDatasetByDatasetId(Integer userId, String confirmInfo);
    BaseResponse addDataset(HttpServletRequest request, String datasetName, String description);
}

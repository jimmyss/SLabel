package com.jimmyss.slabel.service;

import com.jimmyss.slabel.component.BaseResponse;

public interface DatasetService {
    BaseResponse getDatasetByUserIdService(Integer userId, Integer number);
    BaseResponse downloadDataset(Integer userId, String downloadDir);
    BaseResponse deleteDatasetByDatasetId(Integer userId, String confirmInfo);
    BaseResponse addDataset(Integer userId, String uploadDir, String datasetName, String description);
}

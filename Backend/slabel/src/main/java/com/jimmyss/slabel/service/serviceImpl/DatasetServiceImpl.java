package com.jimmyss.slabel.service.serviceImpl;

import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.entity.Dataset;
import com.jimmyss.slabel.repository.DatasetRepository;
import com.jimmyss.slabel.service.DatasetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DatasetServiceImpl implements DatasetService {
    @Autowired
    private DatasetRepository datasetRepository;

    @Override
    public BaseResponse getDatasetByUserIdService(Integer userId, Integer number) {
        Pageable limit = (Pageable) PageRequest.of(0, number);
        Page<Dataset> datasetList=datasetRepository.findByUserIdOrderByCreatedDateDesc(userId, limit);
        return BaseResponse.success("获取数据库成功", datasetList);
    }

    @Override
    public BaseResponse downloadDataset(Integer userId, Integer datasetId, String downloadDir) {
        // get related dataset dir saved in remote server
        Optional<Dataset> dataset=datasetRepository.findByUserIdAndId(userId, datasetId);
        String saveDir=dataset
                .map(Dataset::getSaveDir)
                .orElse(null);
        String zipFileName=dataset
                .map(Dataset::getDatasetName)
                .orElse("default_dataset");
        // use sftp request
        return null;
    }

    @Override
    public BaseResponse deleteDatasetByDatasetId(Integer userId, String confirmInfo) {
        // verify confirmation information

        // delete Dataset according to the directory path of this dataset

        // remove the information saved in the database
        return null;
    }

    @Override
    public BaseResponse addDataset(Integer userId, String uploadDir, String datasetName, String description) {
        //
        return null;
    }


}

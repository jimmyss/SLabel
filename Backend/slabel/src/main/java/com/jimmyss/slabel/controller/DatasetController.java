package com.jimmyss.slabel.controller;

import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.entity.Dataset;
import com.jimmyss.slabel.service.DatasetService;
import com.jimmyss.slabel.vo.DatasetBaseVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user/{userId}/dataset")
public class DatasetController {
    @Autowired
    private DatasetService datasetService;

    @GetMapping
    BaseResponse getDatasetsByUserId(@PathVariable Integer userId, @RequestBody @Valid DatasetBaseVO datasetBaseVO){
        return datasetService.getDatasetByUserIdService(userId, datasetBaseVO.getNum());
    }

    @GetMapping
    BaseResponse downloadDataset(@PathVariable Integer userId, @RequestBody @Valid DatasetBaseVO datasetBaseVO){
        return datasetService
    }

    @DeleteMapping("/{datasetId}")
    BaseResponse deleteDatasetByDatasetId(@PathVariable Integer userId, @PathVariable Integer datasetId, @RequestBody @Valid DatasetBaseVO datasetBaseVO){
        return datasetService
    }

    @PostMapping
    BaseResponse addDataset(@PathVariable Integer userId, @RequestBody @Valid DatasetBaseVO datasetBaseVO){
        return datasetService
    }
}

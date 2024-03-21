package com.jimmyss.slabel.controller;

import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.component.response.DatasetResponse;
import com.jimmyss.slabel.service.DatasetService;
import com.jimmyss.slabel.vo.DatasetBaseVO;
import com.jimmyss.slabel.vo.LabelTaskVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user/dataset")
public class DatasetController {
    @Autowired
    private DatasetService datasetService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("")
    BaseResponse getDatasetsByUserId(@RequestParam(name="num", required=false, defaultValue = "10") Integer num){
        return datasetService.getDatasets(num, request);
    }

    @GetMapping("/download")
    BaseResponse downloadDataset(@PathVariable Integer userId, @RequestBody @Valid DatasetBaseVO datasetBaseVO){
        return null;
    }

    @DeleteMapping("/{datasetId}")
    BaseResponse deleteDatasetByDatasetId(@PathVariable Integer userId, @PathVariable Integer datasetId, @RequestBody @Valid DatasetBaseVO datasetBaseVO){
        return null;
    }

    @PostMapping("")
    BaseResponse addDataset(@RequestBody @Valid DatasetBaseVO datasetBaseVO){
        return datasetService.addDataset(
                request,
                datasetBaseVO.getDatasetName(),
                datasetBaseVO.getDescription()
        );
    }
}

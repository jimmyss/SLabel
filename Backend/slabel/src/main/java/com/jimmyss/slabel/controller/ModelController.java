package com.jimmyss.slabel.controller;


import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.service.serviceImpl.ModelServiceImpl;
import com.jimmyss.slabel.vo.ModelVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user/dataset/model")
public class ModelController {

    @Autowired
    private ModelServiceImpl modelService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("")
    public BaseResponse getAllModels(){
        return modelService.getModels(request);
    }

    @PostMapping("")
    public BaseResponse createModel(@RequestBody @Valid ModelVO modelVO){
        return modelService.createModel(
                request,
                modelVO.getModelName(),
                modelVO.getDescription(),
                modelVO.getDatasetId());
    }
}

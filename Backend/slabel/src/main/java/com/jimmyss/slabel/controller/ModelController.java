package com.jimmyss.slabel.controller;


import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.service.serviceImpl.ModelServiceImpl;
import com.jimmyss.slabel.vo.CreateModelVO;
import com.jimmyss.slabel.vo.DelModelVO;
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
    public BaseResponse createModel(@RequestBody @Valid CreateModelVO createModelVO){
        return modelService.createModel(
                request,
                createModelVO.getModelName(),
                createModelVO.getDescription(),
                createModelVO.getDatasetId());
    }

    @DeleteMapping("")
    public BaseResponse deleteModel(@RequestBody @Valid DelModelVO delModelVO){
        return modelService.deleteModel(
                request,
                delModelVO.getModelId()
        );
    }
}

package com.jimmyss.slabel.service.serviceImpl;


import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.component.response.ModelResponse;
import com.jimmyss.slabel.entity.Dataset;
import com.jimmyss.slabel.entity.Model;
import com.jimmyss.slabel.repository.DatasetRepository;
import com.jimmyss.slabel.repository.ModelRepository;
import com.jimmyss.slabel.repository.UserRepository;
import com.jimmyss.slabel.service.ModelService;
import com.jimmyss.slabel.util.JwtToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ModelServiceImpl implements ModelService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private DatasetRepository datasetRepository;

    @Override
    public BaseResponse getModels(HttpServletRequest request) {
        // get userid from token
        String token= JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);

        // get datasetId from userId and find all related modelId
        List<Dataset> datasetList=datasetRepository.findByUserId(userId);
        List<Model> modelList=new ArrayList<>();
        for(var dataset: datasetList){
            List<Model> tempModel=modelRepository.findModelsByDatasetId(dataset.getId());
            for(var model: tempModel){
                modelList.add(model);
            }
        }

        ModelResponse modelResponse=new ModelResponse(modelList);

        return BaseResponse.success("获取模型成功", modelResponse);
    }

    @Override
    public BaseResponse createModel(HttpServletRequest request, String modelName, String description, Integer datasetId) {
        // get userid from token
        String token= JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);

        // get datasetId from userId and datasetName
        Optional<Dataset> dataset=datasetRepository.findByUserIdAndId(userId, datasetId);
        if(dataset.isPresent()){
            // get all related models
            Model model=new Model(dataset.get(), modelName, description);
            modelRepository.save(model);
            return BaseResponse.success("创建模型任务成功");
        }
        return BaseResponse.error("找不到数据集");
    }

    @Override
    public BaseResponse deleteModel(HttpServletRequest request, Integer modelId) {
        // get userid from token
        String token= JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);

        // find related dataset
        Optional<Model> model=modelRepository.findModelById(modelId);
        if(model.isPresent()){
            Integer uId=model.get().getDataset().getUser().getId();
            if(uId.equals(userId)) {
                modelRepository.delete(model.get());
                return BaseResponse.success("删除模型任务成功");
            }return BaseResponse.error("无法删除训练任务");
        }return BaseResponse.error("找不到模型训练任务");
    }

    @Override
    public BaseResponse updateModel(HttpServletRequest request, String newModelName, String description) {
        return null;
    }

    @Override
    public BaseResponse updataModelStatus(HttpServletRequest request, Integer statusId) {
        return null;
    }


}

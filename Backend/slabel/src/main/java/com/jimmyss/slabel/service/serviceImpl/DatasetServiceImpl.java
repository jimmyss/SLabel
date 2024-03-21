package com.jimmyss.slabel.service.serviceImpl;

import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.component.response.DatasetResponse;
import com.jimmyss.slabel.component.response.TaskResponse;
import com.jimmyss.slabel.entity.Dataset;
import com.jimmyss.slabel.entity.LabelTask;
import com.jimmyss.slabel.entity.LabelTaskPersonalInfo;
import com.jimmyss.slabel.entity.User;
import com.jimmyss.slabel.repository.DatasetRepository;
import com.jimmyss.slabel.repository.UserRepository;
import com.jimmyss.slabel.service.DatasetService;
import com.jimmyss.slabel.util.JwtToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DatasetServiceImpl implements DatasetService {
    @Autowired
    private DatasetRepository datasetRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseResponse<DatasetResponse> getDatasets(Integer number, HttpServletRequest request) {
        // get userid from token
        String token= JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);

        // get related datasets by user id
        List<Dataset> datasets=datasetRepository.findByUserId(userId);
        DatasetResponse datasetResponse=new DatasetResponse(datasets);

        return BaseResponse.success("数据集查询成功",datasetResponse);
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
    public BaseResponse addDataset(HttpServletRequest request, String datasetName, String description) {
        if(datasetName==null){
            return BaseResponse.error("数据集名称是必填项");
        }

        // get userid from token
        String token= JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);
        Optional<User> user=userRepository.findUserById(userId);
        if(!user.isPresent()){
            return BaseResponse.error("找不到用户");
        }

        Dataset newDataset=new Dataset(datasetName, description, user.get(), new Date());
        datasetRepository.save(newDataset);

        return BaseResponse.success("标注任务创建成功");
    }


}

package com.jimmyss.slabel.service;

import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.entity.LabelTask;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface LabelTaskService {

    BaseResponse<Set<LabelTask>> getLabelTasks(Integer number, HttpServletRequest request);

    BaseResponse<LabelTask> createLabelTask(HttpServletRequest request, String title, String description, String direction, Date deadline);

    BaseResponse deleteLabelTask(HttpServletRequest request, String comfirmInfo, Integer taskId);

    BaseResponse updateTask(HttpServletRequest request, Integer taskId, String title, String description, String direction, Date deadline, LabelTask.Status status);

    BaseResponse addMember(Integer taskId, List<String> userNameList);

    BaseResponse deleteMember(HttpServletRequest request, Integer taskId, String userName);
}

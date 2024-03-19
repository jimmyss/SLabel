package com.jimmyss.slabel.controller;

import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.service.LabelTaskService;
import com.jimmyss.slabel.service.serviceImpl.LabelTaskServiceImpl;
import com.jimmyss.slabel.vo.LabelTaskVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.BitSet;

@RestController
@RequestMapping("/v1/annotation-tasks")
public class LabelTaskController {

    @Resource
    LabelTaskServiceImpl labelTaskService;

    @Autowired
    HttpServletRequest request;

    @GetMapping("")
    BaseResponse getTasks(@RequestParam(name="num", required=false, defaultValue = "10") Integer num){
        return labelTaskService.getLabelTasks(num, request);
    }

    @PostMapping("")
    BaseResponse createTask(@RequestBody @Valid LabelTaskVO labelTaskVO){
        return labelTaskService.createLabelTask(
                request,
                labelTaskVO.getTitle(),
                labelTaskVO.getDescription(),
                labelTaskVO.getDirection(),
                labelTaskVO.getDeadline());
    }

    @DeleteMapping("/{taskId}")
    BaseResponse deleteTask(@RequestBody @Valid LabelTaskVO labelTaskVO, @PathVariable Integer taskId){
        return labelTaskService.deleteLabelTask(
                request,
                labelTaskVO.getConfirmInfo(),
                taskId
        );
    }

    @PostMapping("/{taskId}")
    BaseResponse updateTask(@RequestBody @Valid LabelTaskVO labelTaskVO, @PathVariable Integer taskId){
        return labelTaskService.updateTask(
                request,
                taskId,
                labelTaskVO.getTitle(),
                labelTaskVO.getDescription(),
                labelTaskVO.getDirection(),
                labelTaskVO.getDeadline(),
                labelTaskVO.getStatus()
        );
    }

    @PostMapping("/{taskId}/user")
    BaseResponse addMember(@RequestBody @Valid LabelTaskVO labelTaskVO, @PathVariable Integer taskId){
        return labelTaskService.addMember(
                request,
                taskId,
                labelTaskVO.getUserNameList()
        );
    }

    @DeleteMapping("/{taskId}/user/{userName}")
    BaseResponse deleteMember(@RequestBody @Valid LabelTaskVO labelTaskVO,
                              @PathVariable Integer taskId,
                              @PathVariable String userName){
        return labelTaskService.deleteMember(
                request,
                taskId,
                userName
        );
    }
}

package com.jimmyss.slabel.service.serviceImpl;

import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.entity.LabelTask;
import com.jimmyss.slabel.entity.LabelTaskPersonalInfo;
import com.jimmyss.slabel.entity.User;
import com.jimmyss.slabel.repository.TaskUserRepository;
import com.jimmyss.slabel.repository.LabelTaskRepository;
import com.jimmyss.slabel.repository.UserRepository;
import com.jimmyss.slabel.service.LabelTaskService;
import com.jimmyss.slabel.util.JwtToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.jimmyss.slabel.entity.LabelTaskPersonalInfo.Role;

@Service
@Slf4j
public class LabelTaskServiceImpl implements LabelTaskService {

    @Autowired
    private LabelTaskRepository labelTaskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskUserRepository taskUserRepository;

    @Override
    public BaseResponse<Set<LabelTask>> getLabelTasks(Integer number, HttpServletRequest request) {
        // get userid from token
        String token=JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);

        List<LabelTaskPersonalInfo> taskUserInfo=taskUserRepository.findByUserId(userId);

        // get related LabelTasks by user id
        Set<LabelTask> labelTasks=taskUserInfo.stream()
                .map(LabelTaskPersonalInfo::getLabelTask)
                .collect(Collectors.toSet());

        return BaseResponse.success("标注任务查询成功",labelTasks);
    }

    @Override
    public BaseResponse<LabelTask> createLabelTask(HttpServletRequest request, String title, String description, String direction, Date deadline){
        if(title==null){
            return BaseResponse.error("标题是必填项");
        }

        LabelTask newLabelTask = new LabelTask(title, description, direction, deadline, new Date(), LabelTask.Status.Proceeding);

        String token=JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);
        Optional<User> user=userRepository.findUserById(userId);
        if(!user.isPresent()){
            return BaseResponse.error("找不到用户");
        }

        labelTaskRepository.save(newLabelTask);
        LabelTaskPersonalInfo taskUserInfo=new LabelTaskPersonalInfo(user.get(), newLabelTask);
        taskUserInfo.setRole(LabelTaskPersonalInfo.Role.Admin);
        taskUserRepository.save(taskUserInfo);

        return BaseResponse.success("标注任务创建成功",newLabelTask);
    }

    @Override
    public BaseResponse deleteLabelTask(HttpServletRequest request, String comfirmInfo, Integer taskId){
        // get userid from token
        String token=JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);
        Optional<User> user=userRepository.findUserById(userId);

        //find task to delete
        List<LabelTaskPersonalInfo> taskUserInfo=taskUserRepository.findByUserId(user.get().getId());
        Optional<LabelTaskPersonalInfo> toDeleteTaskInfo=taskUserInfo.stream()
                .filter(labelTaskPersonalInfo -> taskId.equals(labelTaskPersonalInfo.getLabelTask().getId()))
                .findFirst();
        if(!toDeleteTaskInfo.isPresent()) return BaseResponse.error("找不到任务");
        if(toDeleteTaskInfo.get().getRole()!= Role.Annotator) return BaseResponse.error("没有权限删除任务");
        if(!toDeleteTaskInfo.get().getLabelTask().getTitle().equals(comfirmInfo)) return BaseResponse.error("确认信息错误");

        // delete taskinfo
        taskUserRepository.delete(toDeleteTaskInfo.get());
        List<LabelTaskPersonalInfo> taskUserInfos=taskUserRepository.findByLabelTaskId(taskId);
        if(!taskUserInfos.isEmpty()){
            taskUserRepository.deleteAllInBatch(taskUserInfos);
        }
        labelTaskRepository.deleteById(taskId);
        return BaseResponse.success("任务删除成功");
    }

    @Override
    public BaseResponse updateTask(HttpServletRequest request, Integer taskId, String title, String description, String direction, Date deadline, LabelTask.Status status) {
        // get userid from token
        String token=JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);
        Optional<User> user=userRepository.findUserById(userId);
        if(!user.isPresent()) return BaseResponse.error("用户不存在");

        // 根据id找到任务
        Optional<LabelTask> updateTask=labelTaskRepository.findById(taskId);
        if(!updateTask.isPresent()) return BaseResponse.error("找不到任务");

        //判断用户权限
        Optional<LabelTaskPersonalInfo> taskUserInfo=taskUserRepository.findByLabelTaskIdAndUserId(taskId, userId);
        Boolean isAuthorized=taskUserInfo.get().getRole().equals(Role.Admin)? true:false;
        if(!isAuthorized) return BaseResponse.error("没有权限修改");

        if(title!=null) updateTask.get().setTitle(title);
        if(description!=null) updateTask.get().setDescription(description);
        if(direction!=null) updateTask.get().setDirection(direction);
        if(deadline!=null) updateTask.get().setDeadline(deadline);
        if(status!=null) updateTask.get().setStatus(status);

        labelTaskRepository.save(updateTask.get());
        return BaseResponse.success("标注任务更新成功", taskId);
    }

    @Override
    public BaseResponse addMember(Integer taskId, List<String> userNameList) {
        Optional<LabelTask> labelTask=labelTaskRepository.findById(taskId);
        if(!labelTask.isPresent()) return BaseResponse.error("任务不存在");

        List<LabelTaskPersonalInfo> taskUserInfo=taskUserRepository.findByLabelTaskId(taskId);
        List<String> notFoundUsers=new ArrayList<>();
        for(String userName : userNameList){
            User user=userRepository.findUserByUsername(userName);
            boolean isAlreadyAdded = taskUserInfo.stream()
                    .anyMatch(info->info.getUser().equals(user));
            if(user==null && !isAlreadyAdded){
                taskUserInfo.add(new LabelTaskPersonalInfo(user, labelTask.get()));
            }else{
                notFoundUsers.add(userName);
            }
        }
        taskUserRepository.saveAll(taskUserInfo);
        if(notFoundUsers.size()==0){
            return BaseResponse.success("成功添加用户", userNameList);
        }else {
            return BaseResponse.error("找不到部分用户", notFoundUsers);
        }
    }

    @Override
    public BaseResponse deleteMember(HttpServletRequest request, Integer taskId, String userName) {
        //确认用户的权限

        return null;
    }


}

package com.jimmyss.slabel.service.serviceImpl;

import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.component.response.TaskResponse;
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
    public BaseResponse<TaskResponse> getLabelTasks(Integer number, HttpServletRequest request) {
        // get userid from token
        String token=JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);

        //get taskUserInfo
        Optional<User> user=userRepository.findUserById(userId);
        List<LabelTaskPersonalInfo> taskUserInfo=taskUserRepository.findByUserId(user.get().getId());

        // get related LabelTasks by user id
        Set<LabelTask> labelTasks=taskUserInfo.stream()
                .map(LabelTaskPersonalInfo::getLabelTask)
                .collect(Collectors.toSet());
        TaskResponse taskResponse=new TaskResponse(labelTasks);

        return BaseResponse.success("标注任务查询成功",taskResponse);
    }

    @Override
    public BaseResponse createLabelTask(HttpServletRequest request, String title, String description, String direction, Date deadline){
        // precheck the title
        if(title==null){
            return BaseResponse.error("标题是必填项");
        }

        // label task to be added
        LabelTask newLabelTask = new LabelTask(title, description, direction, deadline, new Date(), LabelTask.Status.Proceeding);

        // get user
        String token=JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);
        Optional<User> user=userRepository.findUserById(userId);
        if(!user.isPresent()){
            return BaseResponse.error("找不到用户");
        }

        // save label task to be added
        labelTaskRepository.save(newLabelTask);

        // update taskUserInfo
        LabelTaskPersonalInfo taskUserInfo=new LabelTaskPersonalInfo(user.get(), newLabelTask);
        taskUserInfo.setRole(LabelTaskPersonalInfo.Role.Admin);
        taskUserRepository.save(taskUserInfo);

        return BaseResponse.success("标注任务创建成功");
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

        // find task according to taskId
        Optional<LabelTask> updateTask=labelTaskRepository.findById(taskId);
        if(!updateTask.isPresent()) return BaseResponse.error("找不到任务");

        // judge permission by taskUserInfo
        Optional<LabelTaskPersonalInfo> taskUserInfo=taskUserRepository.findByLabelTaskIdAndUserId(taskId, userId);
        Boolean isAuthorized=taskUserInfo.get().getRole().equals(Role.Admin)? true:false;
        if(!isAuthorized) return BaseResponse.error("没有权限修改");

        // update task
        if(title!=null) updateTask.get().setTitle(title);
        if(description!=null) updateTask.get().setDescription(description);
        if(direction!=null) updateTask.get().setDirection(direction);
        if(deadline!=null) updateTask.get().setDeadline(deadline);
        if(status!=null) updateTask.get().setStatus(status);
        labelTaskRepository.save(updateTask.get());

        return BaseResponse.success("标注任务更新成功", taskId);
    }

    @Override
    public BaseResponse addMember(HttpServletRequest request, Integer taskId, List<String> userNameList) {
        // judge if the task exists
        Optional<LabelTask> labelTask=labelTaskRepository.findById(taskId);
        if(!labelTask.isPresent()) return BaseResponse.error("任务不存在");

        // get userId
        String token=JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);
        Optional<User> operateUser=userRepository.findUserById(userId);
        if(!operateUser.isPresent()) return BaseResponse.error("用户不存在");

        // judge permission by taskUserInfo
        Optional<LabelTaskPersonalInfo> taskUserInfo=taskUserRepository.findByLabelTaskIdAndUserId(taskId, userId);
        Boolean isAuthorized=taskUserInfo.get().getRole().equals(Role.Admin)? true:false;
        if(!isAuthorized) return BaseResponse.error("没有权限增加参与角色");

        // add member
        List<String> notFoundUsers=new ArrayList<>();
        List<LabelTaskPersonalInfo> newTaskUserInfo=new ArrayList<>();
        for(String userName : userNameList){
            User user=userRepository.findUserByUsername(userName);
            boolean isAlreadyAdded = taskUserInfo.stream()
                    .anyMatch(info->info.getUser().equals(user));
            if(user!=null && !isAlreadyAdded){
                newTaskUserInfo.add(new LabelTaskPersonalInfo(user, labelTask.get()));
            }else{
                notFoundUsers.add(userName);
            }
        }
        if(notFoundUsers.size()==0){
            taskUserRepository.saveAll(newTaskUserInfo);
            return BaseResponse.success("成功添加用户", userNameList);
        }else {
            return BaseResponse.error("找不到部分用户，请再次确认！", notFoundUsers);
        }
    }

    @Override
    public BaseResponse deleteMember(HttpServletRequest request, Integer taskId, String userName) {
        // judge if the task exists
        Optional<LabelTask> labelTask=labelTaskRepository.findById(taskId);
        if(!labelTask.isPresent()) return BaseResponse.error("任务不存在");

        // get userId
        String token=JwtToken.getToken(request);
        Integer userId=JwtToken.getUserId(token);
        Optional<User> operateUser=userRepository.findUserById(userId);
        if(!operateUser.isPresent()) return BaseResponse.error("用户不存在");

        // judge permission by taskUserInfo
        Optional<LabelTaskPersonalInfo> taskUserInfo=taskUserRepository.findByLabelTaskIdAndUserId(taskId, userId);
        Boolean isAuthorized=taskUserInfo.get().getRole().equals(Role.Admin)? true:false;
        if(!isAuthorized) return BaseResponse.error("没有权限删除角色");

        // delete member
        boolean isAlreadyAdded=false;
        User user=null;
        try{
            user=userRepository.findUserByUsername(userName);
            isAlreadyAdded=taskUserInfo.stream()
                    .anyMatch(info->info.getUser().getUsername().equals(userName));
        }catch (Exception e){
            return BaseResponse.error("找不到用户");
        }
        try{
            if(isAlreadyAdded){
                Optional<LabelTaskPersonalInfo> deleteTaskUserInfo=taskUserRepository.findByLabelTaskIdAndUserId(taskId, user.getId());
                taskUserRepository.delete(deleteTaskUserInfo.get());
            }
        }catch (Exception e){
            return BaseResponse.error("系统错误，删除失败");
        }

        return BaseResponse.success("成功将用户移除本任务", userName);
    }
}

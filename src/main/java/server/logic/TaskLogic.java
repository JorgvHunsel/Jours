package server.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.dto.TaskDTO;
import server.service.CompanyService;
import server.service.CompanyUserService;
import server.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskLogic {

    private final TaskService taskService;

    @Autowired
    public TaskLogic(TaskService taskService){
        this.taskService = taskService;
    }

    public List<TaskDTO> filterActiveTasks(List<TaskDTO> tasks) {
        return tasks.stream().filter(taskDTO -> !taskDTO.getStatus().equals("hidden")).collect(Collectors.toList());
    }

    public String determineNewStatus(String oldStatus, boolean direction) {
        switch (oldStatus) {
            case "to do":
                return "doing";
            case "doing":
                if (direction) {
                    return "done";
                }
                return "to do";
            case "done":
                if (direction) {
                    return "hidden";
                }
                return "doing";
            default:
                return "error";
        }
    }

    public void disableTask(int projectId) {
        taskService.disableTask(projectId, "hidden");
    }
}

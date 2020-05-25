package server.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.dto.TaskDTO;
import server.entity.DAOUser;
import server.entity.Project;
import server.entity.Task;
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

    public Task createTask(String name, String description, int projectId, List<DAOUser> usersFromTask) {
        Task newTask = new Task(name, description, "to do", new Project(projectId), usersFromTask);
        taskService.save(newTask);
        return newTask;
    }

    public String changeTaskStatus(int taskId, String currentStatus, boolean direction) {
        String newStatus = determineNewStatus(currentStatus, direction);
        taskService.updateTaskStatus(taskId, newStatus);
        return newStatus;
    }
}

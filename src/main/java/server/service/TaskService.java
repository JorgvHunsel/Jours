package server.service;

import org.springframework.stereotype.Service;
import server.dto.TaskDTO;
import server.entity.Task;
import server.repository.TaskRepo;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo){this.taskRepo = taskRepo;}

    public void disableTask(int projectId, String newStatus){this.taskRepo.disableTask(projectId, newStatus);}
    public void updateTaskStatus(int taskId, String newStatus){this.taskRepo.updateTaskStatus(taskId, newStatus);}

    public void save(Task newTask) {
        taskRepo.save(newTask);
    }

    public TaskDTO findTaskById(int taskId){return this.taskRepo.findTaskById(taskId);}
}

package server.logic;

import server.dto.TaskDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TaskLogic {
    public List<TaskDTO> filterActiveTasks(List<TaskDTO> tasks) {
        return tasks.stream().filter(taskDTO -> !taskDTO.getStatus().equals("hidden")).collect(Collectors.toList());
    }
}

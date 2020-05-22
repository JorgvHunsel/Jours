package server.logic;

import server.dto.TaskDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TaskLogic {
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
}

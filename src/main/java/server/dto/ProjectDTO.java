package server.dto;

import server.entity.Project;
import server.entity.Task;
import server.entity.Work;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ProjectDTO {
    private int id;
    private String name;
    private Date endDate;

    private String currentUserRole;

    private List<TaskDTO> tasks;
    private List<WorkDTO> workList;

    public ProjectDTO(int id, String name, Date endDate) {
        this.id = id;
        this.name = name;
        this.endDate = endDate;
    }

    public ProjectDTO(Task task){
        workList = new ArrayList<>();

        for(Work work: task.getWork()){
            workList.add(new WorkDTO(work));
        }
    }

    public ProjectDTO(Project project){
        workList = new ArrayList<>();
        tasks = new ArrayList<>();
        for(Task task: project.getTasks()){
            for(Work work: task.getWork()){
                workList.add(new WorkDTO(work));
            }
            tasks.add(new TaskDTO(task));
        }
    }


}

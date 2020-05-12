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
    private int companyId;

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
        this.name = project.getName();
        this.endDate = project.getEndDate();
        workList = new ArrayList<>();
        tasks = new ArrayList<>();
        for(Task task: project.getTasks()){
            for(Work work: task.getWork()){
                workList.add(new WorkDTO(work));
            }
            tasks.add(new TaskDTO(task));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCurrentUserRole() {
        return currentUserRole;
    }

    public void setCurrentUserRole(String currentUserRole) {
        this.currentUserRole = currentUserRole;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public List<WorkDTO> getWorkList() {
        return workList;
    }

    public void setWorkList(List<WorkDTO> workList) {
        this.workList = workList;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}

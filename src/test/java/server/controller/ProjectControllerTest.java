package server.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import server.dto.CompanyDTO;
import server.dto.ProjectDTO;
import server.entity.*;
import server.logic.CompanyLogic;
import server.logic.ProjectLogic;
import server.logic.TaskLogic;

import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {
    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectLogic projectLogic;

    @Mock
    TaskLogic taskLogic;

    @Mock
    CompanyLogic companyLogic;

    @BeforeEach
    void setUp() throws ParseException {
        Company company = new Company(1);
        CompanyDTO companyDTO = new CompanyDTO(company.getId(), "company");

        Task task =  new Task(1, "doing");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        Project project1 = new Project("projectName", new Date(), new Date(), company, true);
        project1.setId(1);
        project1.setTasks(taskList);

        ProjectDTO projectDTO = new ProjectDTO(project1.getId(), project1.getName(), project1.getEndDate(), true);


        List<ProjectDTO> projectDTOS = new ArrayList<>();
        projectDTOS.add(projectDTO);


        lenient().when(projectLogic.getProject(1)).thenReturn(projectDTO);
        lenient().when(projectLogic.getProjectsFromCompany(1)).thenReturn(projectDTOS);
        lenient().when(projectLogic.saveProject("projectName", new Date().toString(), 1, companyDTO)).thenReturn(project1);
        lenient().when(companyLogic.findCompanyById(1)).thenReturn(companyDTO);
        project1.setActive(false);
        lenient().when(projectLogic.disableProject(1)).thenReturn(project1);

    }

    @Test
    void getProjectsFromCompany() {
        HttpStatus status = projectController.getProjectsFromCompany(1).getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void getProject() {
        HttpStatus status = projectController.getProject(1).getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void saveProject(){
        Map<String, String> map = new HashMap<>();
        map.put("projectName", "projectName");
        map.put("endDate", new Date().toString());
        map.put("companyId", "1");
        map.put("projectId", "1");

        HttpStatus status = projectController.saveProject(map).getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void disableProject(){
        HttpStatus status = projectController.disableProject(1).getStatusCode();
        assertEquals(200, status.value());
    }

}
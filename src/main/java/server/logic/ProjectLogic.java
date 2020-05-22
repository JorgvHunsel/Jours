package server.logic;

import server.dto.CompanyDTO;
import server.dto.ProjectDTO;
import server.entity.Company;
import server.entity.Project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class ProjectLogic {

    public Project createProject(String projectName, String endDate, CompanyDTO company) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = sdf.parse(endDate);

        return new Project(projectName, date, new Company(company), true);
    }

    public List<ProjectDTO> filterActiveProjects(List<ProjectDTO> projectDTOS) {
        return projectDTOS.stream().filter(ProjectDTO::isActive).collect(Collectors.toList());
    }
}

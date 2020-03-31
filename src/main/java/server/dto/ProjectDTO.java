package server.dto;

import java.util.Date;

public class ProjectDTO {
    private int id;
    private String name;
    private Date endDate;

    public ProjectDTO(int id, String name, Date endDate) {
        this.id = id;
        this.name = name;
        this.endDate = endDate;
    }

    public ProjectDTO() {
    }
}

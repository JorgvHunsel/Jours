package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import server.dto.WorkDTO;
import server.repository.WorkRepo;

import java.util.Date;

public class WorkService {
    private final WorkRepo workRepo;

    @Autowired
    public WorkService(WorkRepo workRepo){this.workRepo = workRepo;}

    public void updateWork(int id, Date endDate){this.workRepo.updateWork(id, endDate);}
    public WorkDTO findWorkByUserWithoutEndDate(int id){return this.workRepo.findWorkByUserWithoutEndDate(id);}
}

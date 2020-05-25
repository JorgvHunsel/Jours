package server.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.dto.WorkDTO;
import server.entity.DAOUser;
import server.entity.Task;
import server.entity.Work;
import server.service.TaskService;
import server.service.WorkService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class WorkLogic {

    private final WorkService workService;

    @Autowired
    public WorkLogic(WorkService workService){
        this.workService = workService;
    }

    public Work getWorkInstance(int userId, int taskId, String begin, String end) {
        Date beginDate = new Date();
        Date endDate = new Date();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            beginDate = sdf.parse(begin);
            if(!end.equals("")){
                endDate = sdf.parse(end);
            }
            else {
                endDate = null;
            }
        }catch (Exception e){
            //ignore
        }

       return new Work(beginDate, endDate, new DAOUser(userId), new Task(taskId));
    }

    public Work addWork(int userId, int taskId, String beginDate, String endDate) {
        Work newWork = getWorkInstance(userId, taskId, beginDate, endDate);

        workService.save(newWork);
        return newWork;
    }

    public WorkDTO findWorkByUserWithoutEndDate(int userId) {
        return workService.findWorkByUserWithoutEndDate(userId);
    }

    public void updateWork(int workId, Date date) {
        workService.updateWork(workId, date);
    }
}

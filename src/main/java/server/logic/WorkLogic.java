package server.logic;

import server.entity.DAOUser;
import server.entity.Task;
import server.entity.Work;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class WorkLogic {
    public Work addWork(int userId, int taskId, String begin, String end) {
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
}

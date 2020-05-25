package server.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import server.controller.TaskController;
import server.dto.TaskDTO;
import server.entity.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskLogicTest {


    TaskLogic taskLogic = new TaskLogic();

    List<TaskDTO> taskList;
    @BeforeEach
    void setUp(){
        taskList = new ArrayList<>();
        taskList.add(new TaskDTO(1, "name", "desc", "to do"));
        taskList.add(new TaskDTO(2, "name1", "desc1", "doing"));
        taskList.add(new TaskDTO(3, "name2", "desc2", "done"));
        taskList.add(new TaskDTO(4, "name3", "desc3", "hidden"));
    }

    @Test
    void testIfActiveTasksAreNotNull() {
        List<TaskDTO> taskDTOList = taskLogic.filterActiveTasks(taskList);

        assertNotEquals(null, taskDTOList);
    }

    @Test
    void testIfActiveTasksAreFiltered() {
     List<TaskDTO> taskDTOList = taskLogic.filterActiveTasks(taskList);

     assertEquals(3, taskDTOList.size());
    }

    @Test
    void testIfFilteredTaskStatusIsHidden(){
        List<TaskDTO> taskDTOList = taskLogic.filterActiveTasks(taskList);

        for(TaskDTO taskDTO: taskDTOList){
            assertNotEquals("hidden", taskDTO.getStatus());
        }
    }

    @Test
    void testIfNewNextStatusIsDetermined() {
        String result = taskLogic.determineNewStatus("to do", true);
        assertEquals("doing", result);

        result = taskLogic.determineNewStatus("doing", true);
        assertEquals("done", result);

        result = taskLogic.determineNewStatus("done", true);
        assertEquals("hidden", result);
    }

    @Test
    void testIfStatusIsNotChanged(){
        String result = taskLogic.determineNewStatus("to do", false);
        assertEquals("to do", result);
    }

    @Test
    void testIfStatusIsWrong(){
        String result = taskLogic.determineNewStatus("wrong input", true);
        assertEquals("error", result);
    }

}
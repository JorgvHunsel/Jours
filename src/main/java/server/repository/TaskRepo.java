package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.entity.Task;

import java.util.Date;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Task t set t.status = ?2 WHERE t.id = ?1")
    void updateTaskStatus(int taskId, String newStatus);
}

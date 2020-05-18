package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.dto.CompanyDTO;
import server.dto.UserDTO;
import server.dto.WorkDTO;
import server.entity.Work;


import java.util.Date;

@Repository
public interface WorkRepo extends JpaRepository<Work, Integer> {
    @Query("SELECT new server.dto.WorkDTO(w.id, w.beginDate, w.endDate) FROM Work w WHERE w.user.id = ?1 AND w.endDate IS NULL")
    WorkDTO findWorkByUserWithoutEndDate(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Work w set w.endDate = ?2 WHERE w.id = ?1")
    void updateWork(int id, Date endDate);



}

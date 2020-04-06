package server.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompanyUserKey implements Serializable {

    @Column(name = "company_id")
    int studentId;

    @Column(name = "user_id")
    int userId;

    public CompanyUserKey() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyUserKey companyUserKey = (CompanyUserKey) o;
        return studentId == companyUserKey.studentId &&
                userId == companyUserKey.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, userId);
    }
}

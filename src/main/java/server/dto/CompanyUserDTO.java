package server.dto;

public class CompanyUserDTO {

    private int userId;
    private String role;

    public CompanyUserDTO() {
    }

    public CompanyUserDTO(int userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

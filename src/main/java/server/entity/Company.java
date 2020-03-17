package server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name ="`company`")
@Data
public class Company {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany
            @JoinTable(
                    name = "company_user",
                    joinColumns = @JoinColumn(name = "companyId"),
                    inverseJoinColumns = @JoinColumn(name = "userId")
            )
    List<User> users;
}

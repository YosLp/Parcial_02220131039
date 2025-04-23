package co.edu.udes.activity.backend.demo.models;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer() {}
}






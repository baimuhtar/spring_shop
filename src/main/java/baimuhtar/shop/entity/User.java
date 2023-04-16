package baimuhtar.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private Role role;
    private String login;
    private String password;
    private String name;
    private String surname;
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "user")
    private List<Feedback> userFeedbacks;
}

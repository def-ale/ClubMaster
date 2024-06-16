package br.vianna.edu.clubmaster.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;

}

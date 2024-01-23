package com.capgemini.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    @Size(max = 100)
    private String fullName;

   
    @Column(name = "mobile_number", nullable = false)
    @Size(max = 100)
    private String mobileNumber;
    
    
    @Column(name = "address", nullable = false)
    @Size(max = 100)
    private String address;

    @Column(nullable = false)
    @Size(max = 100)
    private String email;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;
}

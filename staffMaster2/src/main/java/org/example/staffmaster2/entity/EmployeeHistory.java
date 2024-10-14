package org.example.staffmaster2.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "employee_history")
public class EmployeeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long history_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Employee employee;

    private String oldName;

    private String oldEmail;

    private String oldPassword;

    private String oldPhoneNumber;

    private String oldSalaire;

    private String oldNumChilds;

    private String oldSoldeConge;

    private String oldDepartement;

    private Date oldPoste;

    @Temporal(TemporalType.TIMESTAMP)
    private Date changeDate;


    // Getters et setters
}


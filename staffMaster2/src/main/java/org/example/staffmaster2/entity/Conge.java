package org.example.staffmaster2.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "conge")
public class Conge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String motif;

    @Temporal(TemporalType.DATE)
    private Date dateDebutConge;

    @Temporal(TemporalType.DATE)
    private Date dateFinConge;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Conge(Long id, String motif, Date dateDebutConge, Date dateFinConge, Boolean status, Employee employee) {
        this.id = id;
        this.motif = motif;
        this.dateDebutConge = dateDebutConge;
        this.dateFinConge = dateFinConge;
        this.status = status;
        this.employee = employee;
    }

    public Conge() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }
    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Date getDateDebutConge() {
        return dateDebutConge;
    }
    public void setDateDebutConge(Date dateDebutConge) {
        this.dateDebutConge = dateDebutConge;
    }

    public Date getDateFinConge() {
        return dateFinConge;
    }
    public void setDateFinConge(Date dateFinConge) {
        this.dateFinConge = dateFinConge;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}


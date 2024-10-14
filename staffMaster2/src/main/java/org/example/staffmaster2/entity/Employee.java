package org.example.staffmaster2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "employee")

public class Employee extends User {

    private String cnss;

    @Temporal(TemporalType.DATE)
    private Date dateEmbauche;

    private Double salaire;

    private Integer numChilds;

    private Integer soldeConge;

    private String departement;

    private String poste;



    public Employee(Long userId, String name, String email, String password, Role role, Date birthday,
                    String cnss, Date dateEmbauche, Double salaire, Integer numChilds,
                    Integer soldeConge, String departement, String poste) {
        super(userId, name, email, password, role, birthday);
        this.cnss = cnss;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
        this.numChilds = numChilds;
        this.soldeConge = soldeConge;
        this.departement = departement;
        this.poste = poste;
    }

    public Employee() {

    }

    public void setCnss(String cnss) {
        this.cnss = cnss;
    }
    public String getCnss() {
        return cnss;
    }
    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }
    public Double getSalaire() {
        return salaire;
    }

    public void setNumChilds(Integer numChilds) {
        this.numChilds = numChilds;
    }
    public Integer getNumChilds() {
        return numChilds;
    }

    public void setSoldeConge(Integer soldeConge) {
        this.soldeConge = soldeConge;
    }
    public Integer getSoldeConge() {
        return soldeConge;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
    public String getDepartement() {
        return departement;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
    public String getPoste() {
        return poste;
    }

}

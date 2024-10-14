package org.example.staffmaster2.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "offre")
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offre_id;

    private String title;

    private String description;

    private Boolean status;

    @Temporal(TemporalType.DATE)
    private Date dateValidite;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    public Offre(Long id , String title, String description, Boolean status, Date dateValidite, Date dateFin){
        this.offre_id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dateValidite = dateValidite;
        this.dateFin = dateFin;
    }

    public Offre() {}

    public Long getOffre_id() {
        return offre_id;
    }
    public void setOffre_id(Long offre_id) {
        this.offre_id = offre_id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDateValidite() {
        return dateValidite;
    }
    public void setDateValidite(Date dateValidite) {
        this.dateValidite = dateValidite;
    }

    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
}


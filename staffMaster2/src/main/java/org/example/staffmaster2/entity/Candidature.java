package org.example.staffmaster2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "candidature")
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long condidature_id;

    private Long offreId;

    private String email;

    private String competance;

    private Boolean status;

    public Candidature(Long id, Long offreId, String email, String competance, Boolean status) {
        this.condidature_id = id;
        this.offreId = offreId;
        this.email = email;
        this.competance = competance;
        this.status = status;
    }

    public Candidature() {}

    public Long getCondidature_id() {
        return condidature_id;
    }
    public void setCondidature_id(Long condidature_id) {
        this.condidature_id = condidature_id;
    }

    public Long getOffre() {
        return offreId;
    }
    public void setOffre(Long offreId) {
        this.offreId = offreId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompetance() {
        return competance;
    }
    public void setCompetance(String competance) {
        this.competance = competance;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }

}


package org.example.staffmaster2.service;

import org.example.staffmaster2.dao.CondidatureDao;
import org.example.staffmaster2.entity.Candidature;
import org.example.staffmaster2.util.EmailSender;

import jakarta.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

public class CandidatureService {
    private CondidatureDao condidatureDao;

    public CandidatureService() {
        this.condidatureDao = new CondidatureDao();
    }

    public void addCandidature(Candidature candidature) {
        condidatureDao.addCondidature(candidature);
    }

    public void confirmCandidature(Long candidatureId) throws MessagingException {
        Candidature candidature = condidatureDao.getCandidatureById(candidatureId);

        if (candidature != null) {
            candidature.setStatus(true);
            condidatureDao.updateCandidature(candidature);

            // Envoyer l'email de confirmation
            EmailSender.sendEmail(candidature.getEmail(), "Confirmation de votre candidature",
                    "Félicitations, votre candidature a été confirmée !");
        }
    }

    public List<Candidature> getPendingCandidatures(String competanceFilter) {
        List<Candidature> candidatures = condidatureDao.getCandidatures();

        // Filtrer les candidatures par statut et compétence
        return candidatures.stream()
                .filter(c -> !c.getStatus())
                .filter(c -> competanceFilter == null || c.getCompetance().toLowerCase().contains(competanceFilter.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Candidature getCandidatureById(Long candidatureId) {
        return condidatureDao.getCandidatureById(candidatureId);
    }
}
package org.example.staffmaster2.service;

import org.example.staffmaster2.dao.CondidatureDao;
import org.example.staffmaster2.entity.Candidature;
import org.example.staffmaster2.util.EmailSender;

import jakarta.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

public class CandidatureService {
    private CondidatureDao condidatureDao;
    private EmailSender emailSender;

    public CandidatureService(CondidatureDao condidatureDao, EmailSender emailSender) {
        this.condidatureDao = condidatureDao;
        this.emailSender = emailSender;
    }

    public void addCandidature(Candidature candidature) {
        if (candidature == null) {
            throw new IllegalArgumentException("Candidature cannot be null");
        }
        if (candidature.getEmail() == null || candidature.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Candidature email cannot be null or empty");
        }
        condidatureDao.addCondidature(candidature);
    }

    public boolean confirmCandidature(Long candidatureId) throws MessagingException {
        if (candidatureId == null) {
            throw new IllegalArgumentException("Candidature ID cannot be null");
        }

        Candidature candidature = condidatureDao.getCandidatureById(candidatureId);
        if (candidature == null) {
            throw new IllegalArgumentException("Candidature not found for ID: " + candidatureId);
        }

        candidature.setStatus(true);
        condidatureDao.updateCandidature(candidature);

        // Try sending the email, if it fails, the method should handle the exception gracefully
        try {
            emailSender.sendEmail(candidature.getEmail(), "Confirmation de votre candidature",
                    "Félicitations, votre candidature a été confirmée !");
            return true;
        } catch (MessagingException e) {
            throw new MessagingException("Failed to send confirmation email for candidature ID: " + candidatureId);
        }
    }

    public List<Candidature> getPendingCandidatures(String competanceFilter) {
        List<Candidature> candidatures = condidatureDao.getCandidatures();

        if (candidatures == null) {
            throw new IllegalStateException("No candidatures found in the database");
        }

        // Filter the candidatures by status and competence
        return candidatures.stream()
                .filter(c -> !c.getStatus())
                .filter(c -> competanceFilter == null || c.getCompetance().toLowerCase().contains(competanceFilter.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Candidature getCandidatureById(Long candidatureId) {
        if (candidatureId == null) {
            throw new IllegalArgumentException("Candidature ID cannot be null");
        }

        Candidature candidature = condidatureDao.getCandidatureById(candidatureId);
        if (candidature == null) {
            throw new IllegalArgumentException("Candidature not found for ID: " + candidatureId);
        }

        return candidature;
    }
}

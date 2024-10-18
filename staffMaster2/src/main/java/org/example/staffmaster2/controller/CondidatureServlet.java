package org.example.staffmaster2.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.staffmaster2.dao.CondidatureDao;
import org.example.staffmaster2.entity.Candidature;
import org.example.staffmaster2.service.CandidatureService;
import org.example.staffmaster2.util.EmailSender;

import java.io.IOException;
import java.util.List;

@WebServlet("/condidature")
public class CondidatureServlet extends HttpServlet {
    private CandidatureService candidatureService;

    @Override
    public void init() throws ServletException {
        // Create instances of CondidatureDao and EmailSender
        CondidatureDao condidatureDao = new CondidatureDao(); // or use dependency injection framework to manage this
        EmailSender emailSender = new EmailSender(); // or use dependency injection framework to manage this

        // Pass them to the CandidatureService constructor
        candidatureService = new CandidatureService(condidatureDao, emailSender);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "listCandidature":
                listCandidature(request, response);
                break;
            case "confirm":
                confirmerCandidature(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non trouvée");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addCandidature(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non trouvée");
        }
    }

    private void addCandidature(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String competance = request.getParameter("competance");
        Long offreId = Long.parseLong(request.getParameter("offreId"));

        Candidature candidature = new Candidature(null, offreId, email, competance, false);
        candidatureService.addCandidature(candidature);

        response.sendRedirect("offre?action=listOffres");
    }

    private void confirmerCandidature(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long candidatureId = Long.parseLong(request.getParameter("id"));

        try {
            candidatureService.confirmCandidature(candidatureId);
            response.sendRedirect(request.getContextPath() + "/condidature?action=listCandidature");
        } catch (MessagingException e) {
            throw new ServletException("Erreur lors de l'envoi de l'email", e);
        }
    }

    private void listCandidature(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filterCompetance = request.getParameter("competance");
        List<Candidature> candidatures = candidatureService.getPendingCandidatures(filterCompetance);

        request.setAttribute("candidatures", candidatures);
        request.getRequestDispatcher("/view/listCandidatures.jsp").forward(request, response);
    }
}
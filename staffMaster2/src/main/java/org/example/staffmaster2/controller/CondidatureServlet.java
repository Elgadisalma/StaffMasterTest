package org.example.staffmaster2.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.staffmaster2.dao.CondidatureDao;
import org.example.staffmaster2.entity.Candidature;
import org.example.staffmaster2.util.EmailSender;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/condidature")
public class CondidatureServlet extends HttpServlet {
    private CondidatureDao condidatureDao;

    @Override
    public void init() throws ServletException {
        condidatureDao = new CondidatureDao();
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
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addCandidature(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found");
        }
    }

    public void addCandidature(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String competance = request.getParameter("competance");
        String offreIdStr = request.getParameter("offreId");

        Long offreId = Long.parseLong(offreIdStr);
        Boolean status = false;

        Candidature candidature = new Candidature(null, offreId, email, competance, status);

        condidatureDao.addCondidature(candidature);

        response.sendRedirect("offre?action=listOffres");
    }

    public void confirmerCandidature(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("salma");
        String candidatureIdStr = request.getParameter("id");
        Long candidatureId = Long.parseLong(candidatureIdStr);

        Candidature candidature = condidatureDao.getCandidatureById(candidatureId);

        if (candidature != null) {
            candidature.setStatus(true);
            condidatureDao.updateCandidature(candidature);

            try {
                EmailSender.sendEmail(candidature.getEmail(), "Confirmation de votre candidature",
                        "Félicitations, votre candidature a été confirmée !");
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            response.sendRedirect(request.getContextPath() + "/condidature?action=listCandidature");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Candidature non trouvée");
        }
    }

    public void listCandidature(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Candidature> candidatures = condidatureDao.getCandidatures();
        candidatures = candidatures.stream()
                .filter(c -> !c.getStatus())
                .collect(Collectors.toList());

        String filterCompetance = request.getParameter("competance");

        if (filterCompetance != null && !filterCompetance.isEmpty()) {
            candidatures = candidatures.stream()
                    .filter(c -> c.getCompetance().toLowerCase().contains(filterCompetance.toLowerCase()))
                    .collect(Collectors.toList());
        }

        request.setAttribute("candidatures", candidatures);

        request.getRequestDispatcher("/view/listCandidatures.jsp").forward(request, response);
    }
}

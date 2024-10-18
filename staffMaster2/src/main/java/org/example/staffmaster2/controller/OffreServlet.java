package org.example.staffmaster2.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.staffmaster2.entity.Offre;
import org.example.staffmaster2.service.OffreService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet("/offre")
public class OffreServlet extends HttpServlet {
    private OffreService offreService;

    @Override
    public void init() throws ServletException {
        offreService = new OffreService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "ajoutOffre":
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addOffre.jsp");
                dispatcher.forward(request, response);
                break;
            case "listOffres":
                List<Offre> offres = offreService.getAllOffres();
                request.setAttribute("offres", offres);
                request.getRequestDispatcher("/view/listOffres.jsp").forward(request, response);
                break;
            case "postule":
                long offreId = Long.parseLong(request.getParameter("id"));
                Offre offre = offreService.getOffreById(offreId);
                request.setAttribute("offre", offre);
                request.getRequestDispatcher("/view/candidature.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Offre offre = offreService.createOffreFromRequest(
                    request.getParameter("title"),
                    request.getParameter("description"),
                    request.getParameter("dateValidite"),
                    request.getParameter("dateFin")
            );

            offreService.addOffre(offre);
            response.sendRedirect("index.jsp");
        } catch (ParseException e) {
            throw new ServletException("Error parsing dates", e);
        }
    }
}
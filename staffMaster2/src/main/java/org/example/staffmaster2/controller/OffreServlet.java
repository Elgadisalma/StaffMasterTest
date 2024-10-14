package org.example.staffmaster2.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.staffmaster2.dao.OffreDao;
import org.example.staffmaster2.entity.Offre;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/offre")
public class OffreServlet extends HttpServlet {
    private OffreDao offreDao;

    @Override
    public void init() throws ServletException {
        offreDao = new OffreDao();
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
                List<Offre> offres = offreDao.getOffres();
                System.out.println(offres);
                request.setAttribute("offres", offres);
                request.getRequestDispatcher("/view/listOffres.jsp").forward(request, response);
                break;
            case "postule":
                String id = request.getParameter("id");
                long offreId = Long.parseLong(id);
                Offre offre = offreDao.getOffreById(offreId);
                request.setAttribute("offre", offre);

                request.getRequestDispatcher("/view/candidature.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateValidite = null;
        Date dateFin = null;
        try {
            dateValidite = dateFormat.parse(request.getParameter("dateValidite"));
            dateFin = dateFormat.parse(request.getParameter("dateFin"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Boolean status = true;

        Offre offre = new Offre(null, title, description, status, dateValidite, dateFin);

        offreDao.addOffre(offre);

        response.sendRedirect("index.jsp");
    }


}

package org.example.staffmaster2.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.staffmaster2.dao.CongeDao;
import org.example.staffmaster2.entity.Conge;

import java.io.IOException;
import java.util.List;

@WebServlet("/conge")
public class CongeServlet extends HttpServlet {

    private CongeDao congeDao;

    @Override
    public void init() throws ServletException {
        congeDao = new CongeDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "ajoutConge":
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addConge.jsp");
                dispatcher.forward(request, response);
                break;
            case "listConges":
                List<Conge> conges = congeDao.getConges();
                System.out.println(conges);
                request.setAttribute("conges", conges);
                request.getRequestDispatcher("/view/listConges.jsp").forward(request, response);
                break;
            case "accept":
                String id = request.getParameter("id");
                long congeId = Long.parseLong(id);
                Conge conge = congeDao.getCongeById(congeId);
                request.setAttribute("conge", conge);

                request.getRequestDispatcher("/view/candidature.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found");
        }
    }


}

package org.example.staffmaster2.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.example.staffmaster2.entity.User;
import org.example.staffmaster2.service.AuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private AuthService authService;

    @Override
    public void init() {
        authService = new AuthService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = authService.authenticate(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
//            System.out.println(user);
            response.sendRedirect("view/addOffre.jsp");
        } else {
            request.setAttribute("error", "Email ou mot de passe incorrect");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
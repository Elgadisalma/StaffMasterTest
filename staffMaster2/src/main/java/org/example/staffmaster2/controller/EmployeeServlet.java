package org.example.staffmaster2.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.staffmaster2.entity.Employee;
import org.example.staffmaster2.service.EmployeeService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "edit":
                editEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            case "ajoutEmployee":
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addEmployee.jsp");
                dispatcher.forward(request, response);
                break;
            case "listEmployees":
                List<Employee> employees = employeeService.getAllEmployees();
                request.setAttribute("employees", employees);
                request.getRequestDispatcher("/view/listEmployees.jsp").forward(request, response);
                break;
            case "calcul":
                calculEmployee(request, response);
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
                addEmployee(request, response);
                break;
            case "update":
                updateEmployee(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found");
        }
    }

    public void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Employee employee = employeeService.createEmployeeFromRequest(
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("password"),
                    request.getParameter("role"),
                    request.getParameter("cnss"),
                    Double.parseDouble(request.getParameter("salaire")),
                    Integer.parseInt(request.getParameter("numChilds")),
                    Integer.parseInt(request.getParameter("soldeConge")),
                    request.getParameter("departement"),
                    request.getParameter("poste"),
                    request.getParameter("birthday"),
                    request.getParameter("dateEmbauche")
            );

            employeeService.addEmployee(employee);
            response.sendRedirect("index.jsp");
        } catch (ParseException e) {
            throw new ServletException("Error parsing dates", e);
        }
    }

    public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            Employee employee = employeeService.createEmployeeFromRequest(
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("password"),
                    request.getParameter("role"),
                    request.getParameter("cnss"),
                    Double.parseDouble(request.getParameter("salaire")),
                    Integer.parseInt(request.getParameter("numChilds")),
                    Integer.parseInt(request.getParameter("soldeConge")),
                    request.getParameter("departement"),
                    request.getParameter("poste"),
                    request.getParameter("birthday"),
                    request.getParameter("dateEmbauche")
            );
            employee.setId(id);

            employeeService.updateEmployee(employee);
            response.sendRedirect("index.jsp");
        } catch (ParseException e) {
            throw new ServletException("Error parsing dates", e);
        }
    }

    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long employeeId = Long.parseLong(request.getParameter("id"));
        employeeService.deleteEmployee(employeeId);
        response.sendRedirect("index.jsp");
    }

    public void editEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long employeeId = Long.parseLong(request.getParameter("id"));
        Employee employee = employeeService.getEmployeeById(employeeId);
        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/view/updateEmployee.jsp").forward(request, response);
    }

    public void calculEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long employeeId = Long.parseLong(request.getParameter("id"));
        Employee employee = employeeService.getEmployeeById(employeeId);

        int nEnfants = employee.getNumChilds();
        double salaire = employee.getSalaire();

        double allocations = employeeService.calculateAllocationsFamiliales(nEnfants, salaire);

        request.setAttribute("employee", employee);
        request.setAttribute("allocations", allocations);

        request.getRequestDispatcher("/view/allocations.jsp").forward(request, response);
    }
}
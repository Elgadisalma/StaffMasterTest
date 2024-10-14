package org.example.staffmaster2.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.staffmaster2.dao.EmployeeDao;
import org.example.staffmaster2.entity.Employee;
import org.example.staffmaster2.entity.Role;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDao employeeDao;

    @Override
    public void init() throws ServletException {
        employeeDao = new EmployeeDao();
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
                List<Employee> employees = employeeDao.getEmployees();
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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String roleStr = request.getParameter("role");
        Role role = Role.valueOf(roleStr);
        String cnss = request.getParameter("cnss");
        double salaire = Double.parseDouble(request.getParameter("salaire"));
        int numChilds = Integer.parseInt(request.getParameter("numChilds"));
        int soldeConge = Integer.parseInt(request.getParameter("soldeConge"));
        String departement = request.getParameter("departement");
        String poste = request.getParameter("poste");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        Date dateEmbauche = null;
        try {
            birthday = dateFormat.parse(request.getParameter("birthday"));
            dateEmbauche = dateFormat.parse(request.getParameter("dateEmbauche"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Employee employee = new Employee(null,name, email, password, role, birthday, cnss, dateEmbauche, salaire, numChilds, soldeConge, departement, poste);

        employeeDao.addEmployee(employee);

        response.sendRedirect("index.jsp");
    }

    public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String roleStr = request.getParameter("role");
        Role role = Role.valueOf(roleStr);
        String cnss = request.getParameter("cnss");
        double salaire = Double.parseDouble(request.getParameter("salaire"));
        int numChilds = Integer.parseInt(request.getParameter("numChilds"));
        int soldeConge = Integer.parseInt(request.getParameter("soldeConge"));
        String departement = request.getParameter("departement");
        String poste = request.getParameter("poste");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        Date dateEmbauche = null;
        try {
            birthday = dateFormat.parse(request.getParameter("birthday"));
            dateEmbauche = dateFormat.parse(request.getParameter("dateEmbauche"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Employee employee = new Employee(id, name, email, password, role, birthday, cnss, dateEmbauche, salaire, numChilds, soldeConge, departement, poste);

        employeeDao.updateEmployee(employee);

        response.sendRedirect("index.jsp");
    }

    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            long employeeId = Long.parseLong(id);
            employeeDao.deleteEmployee(employeeId);
        }
        response.sendRedirect("index.jsp");
    }

    public void editEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            long employeeId = Long.parseLong(id);
            Employee employee = employeeDao.getEmployeeById(employeeId);
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("/view/updateEmployee.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    public void calculEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id != null) {
            Employee employee = employeeDao.getEmployeeById(Integer.parseInt(id));

            int nEnfants = employee.getNumChilds();
            double salaire = employee.getSalaire();

            double allocations = calculerAllocationsFamiliales(nEnfants, salaire);

            request.setAttribute("employee", employee);
            request.setAttribute("allocations", allocations);

            request.getRequestDispatcher("/view/allocations.jsp").forward(request, response);
        }
    }

    private double calculerAllocationsFamiliales(int nEnfants, double salaire) {
        double allocations = 0;

        if (salaire < 6000) {
            for (int i = 1; i <= nEnfants && i <= 6; i++) {
                if (i <= 3) {
                    allocations += 300;
                } else {
                    allocations += 150;
                }
            }
        }

        else if (salaire > 8000) {
            for (int i = 1; i <= nEnfants && i <= 6; i++) {
                if (i <= 3) {
                    allocations += 200;
                } else {
                    allocations += 110;
                }
            }
        }
        return allocations;
    }

}

package org.example.staffmaster2.service;

import org.example.staffmaster2.dao.EmployeeDao;
import org.example.staffmaster2.entity.Employee;
import org.example.staffmaster2.entity.Role;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeService() {
        this.employeeDao = new EmployeeDao();
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getEmployees();
    }

    public Employee getEmployeeById(long id) {
        return employeeDao.getEmployeeById(id);
    }

    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(long id) {
        employeeDao.deleteEmployee(id);
    }

    public double calculateAllocationsFamiliales(int nEnfants, double salaire) {
        double allocations = 0;

        if (salaire < 6000) {
            for (int i = 1; i <= nEnfants && i <= 6; i++) {
                allocations += (i <= 3) ? 300 : 150;
            }
        } else if (salaire > 8000) {
            for (int i = 1; i <= nEnfants && i <= 6; i++) {
                allocations += (i <= 3) ? 200 : 110;
            }
        }

        return allocations;
    }

    public Employee createEmployeeFromRequest(String name, String email, String password, String roleStr, String cnss,
                                              double salaire, int numChilds, int soldeConge, String departement,
                                              String poste, String birthdayStr, String dateEmbaucheStr) throws ParseException {
        if (isNullOrEmpty(name, email, password, roleStr, birthdayStr, dateEmbaucheStr)) {
            throw new IllegalArgumentException("Missing required employee details");
        }

        Role role = validateRole(roleStr);

        if (employeeDao.emailExists(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse(birthdayStr);
        Date dateEmbauche = dateFormat.parse(dateEmbaucheStr);

        if (birthday.after(dateEmbauche)) {
            throw new IllegalArgumentException("La date de naissance ne peut pas être postérieure à la date d'embauche.");
        }

        return new Employee(null, name, email, password, role, birthday, cnss, dateEmbauche, salaire, numChilds,
                soldeConge, departement, poste);
    }

    private Role validateRole(String roleStr) {
        try {
            return Role.valueOf(roleStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role value: " + roleStr);
        }
    }

    private boolean isNullOrEmpty(String... values) {
        for (String value : values) {
            if (value == null || value.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}

package org.example.staffmaster2.service;

import org.example.staffmaster2.dao.EmployeeDao;
import org.example.staffmaster2.entity.User;

public class AuthService {
    private EmployeeDao employeeDao;

    public AuthService() {
        this.employeeDao = new EmployeeDao();
    }

    public User authenticate(String email, String password) {
        User user = employeeDao.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
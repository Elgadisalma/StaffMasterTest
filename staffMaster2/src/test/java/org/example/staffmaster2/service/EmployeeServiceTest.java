package org.example.staffmaster2.service;

import org.example.staffmaster2.dao.EmployeeDao;
import org.example.staffmaster2.entity.Employee;
import org.example.staffmaster2.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee(1L, "John Doe", "john.d@test.com", "password123", Role.employee,
                new Date(), "N12345", new Date(), 5000.0, 2, 10, "HR", "Manager");
    }

    @Test
    public void testAddEmployee_Success() {
        doNothing().when(employeeDao).addEmployee(employee);

        employeeService.addEmployee(employee);

        verify(employeeDao).addEmployee(employee);
    }

    @Test
    public void testAddEmployee_WithNullValues_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.createEmployeeFromRequest(null, "test@test.com", "password", "employee",
                    "N12345", 5000.0, 2, 10, "HR", "Manager", "1990-01-01", "2023-01-01");
        });

        assertEquals("Missing required employee details", exception.getMessage());
    }

    @Test
    public void testAddEmployee_InvalidRole_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.createEmployeeFromRequest("John", "john@test.com", "password", "INVALID_ROLE",
                    "N12345", 5000.0, 2, 10, "HR", "Manager", "1990-01-01", "2023-01-01");
        });

        assertEquals("Invalid role value: INVALID_ROLE", exception.getMessage());
    }

    @Test
    public void testAddEmployee_BirthdateAfterHiringDate_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.createEmployeeFromRequest("John", "jon@tst.com", "password", "employee",
                    "N12345", 5000.0, 2, 10, "HR", "Manager", "2024-01-01", "2023-01-01");
        });

        assertEquals("La date de naissance ne peut pas être postérieure à la date d'embauche.", exception.getMessage());
    }

    @Test
    public void testUpdateEmployee_Success() {
        employee.setName("Jane Doe");
        doNothing().when(employeeDao).updateEmployee(employee);

        employeeService.updateEmployee(employee);

        verify(employeeDao).updateEmployee(employee);
    }

    @Test
    public void testDeleteEmployee_Success() {
        long employeeId = 1L;
        doNothing().when(employeeDao).deleteEmployee(employeeId);

        employeeService.deleteEmployee(employeeId);

        verify(employeeDao).deleteEmployee(employeeId);
    }
}

package org.example.staffmaster2.service;

import org.example.staffmaster2.dao.EmployeeDao;
import org.example.staffmaster2.entity.Employee;
import org.example.staffmaster2.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    // Test cases for createEmployeeFromRequest
    @Test
    public void testCreateEmployeeFromRequest_Success() throws ParseException {
        when(employeeDao.emailExists("john.d@test.com")).thenReturn(false);

        Employee result = employeeService.createEmployeeFromRequest("John Doe", "john.d@test.com", "password123",
                "employee", "N12345", 5000.0, 2, 10, "HR", "Manager", "1990-01-01", "2023-01-01");

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john.d@test.com", result.getEmail());
        assertEquals(Role.employee, result.getRole());
    }

    @Test
    public void testCreateEmployeeFromRequest_NullValues_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.createEmployeeFromRequest(null, "test@test.com", "password", "employee",
                    "N12345", 5000.0, 2, 10, "HR", "Manager", "1990-01-01", "2023-01-01");
        });
        assertEquals("Missing required employee details", exception.getMessage());
    }

    @Test
    public void testCreateEmployeeFromRequest_InvalidRole_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.createEmployeeFromRequest("John", "john@test.com", "password", "INVALID_ROLE",
                    "N12345", 5000.0, 2, 10, "HR", "Manager", "1990-01-01", "2023-01-01");
        });
        assertEquals("Invalid role value: INVALID_ROLE", exception.getMessage());
    }

    @Test
    public void testCreateEmployeeFromRequest_EmailAlreadyExists_ShouldThrowException() {
        when(employeeDao.emailExists("john.d@test.com")).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.createEmployeeFromRequest("John Doe", "john.d@test.com", "password123",
                    "employee", "N12345", 5000.0, 2, 10, "HR", "Manager", "1990-01-01", "2023-01-01");
        });
        assertEquals("Email already exists: john.d@test.com", exception.getMessage());
    }

    @Test
    public void testCreateEmployeeFromRequest_BirthdateAfterHiringDate_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.createEmployeeFromRequest("John", "john@test.com", "password", "employee",
                    "N12345", 5000.0, 2, 10, "HR", "Manager", "2024-01-01", "2023-01-01");
        });
        assertEquals("La date de naissance ne peut pas être postérieure à la date d'embauche.", exception.getMessage());
    }

    // Test cases for calculateAllocationsFamiliales
    @Test
    public void testCalculateAllocationsFamiliales_SalaryBelow6000() {
        double allocations = employeeService.calculateAllocationsFamiliales(3, 5000);
        assertEquals(900, allocations);
    }

    @Test
    public void testCalculateAllocationsFamiliales_SalaryAbove8000() {
        double allocations = employeeService.calculateAllocationsFamiliales(3, 9000);
        assertEquals(600, allocations);
    }

    @Test
    public void testCalculateAllocationsFamiliales_NoChildren() {
        double allocations = employeeService.calculateAllocationsFamiliales(0, 5000);
        assertEquals(0, allocations);
    }

    // Test cases for isNullOrEmpty
    @Test
    public void testIsNullOrEmpty_AllNullValues() {
        assertTrue(employeeService.isNullOrEmpty(null, null, null));
    }

    @Test
    public void testIsNullOrEmpty_AllEmptyValues() {
        assertTrue(employeeService.isNullOrEmpty("", "   ", ""));
    }

    @Test
    public void testIsNullOrEmpty_MixedValues() {
        assertTrue(employeeService.isNullOrEmpty("value", null, ""));
    }

    @Test
    public void testIsNullOrEmpty_AllNonEmptyValues() {
        assertFalse(employeeService.isNullOrEmpty("value", "test", "123"));
    }

    // Test cases for validateRole
    @Test
    public void testValidateRole_ValidRole() {
        Role role = employeeService.validateRole("employee");
        assertEquals(Role.employee, role);
    }

    @Test
    public void testValidateRole_InvalidRole_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.validateRole("invalid_role");
        });
        assertEquals("Invalid role value: invalid_role", exception.getMessage());
    }

    // Test CRUD operations
    @Test
    public void testAddEmployee_Success() {
        doNothing().when(employeeDao).addEmployee(employee);
        employeeService.addEmployee(employee);
        verify(employeeDao).addEmployee(employee);
    }

    @Test
    public void testUpdateEmployee_Success() {
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

    @Test
    public void testGetEmployeeById_Success() {
        when(employeeDao.getEmployeeById(1L)).thenReturn(employee);
        Employee result = employeeService.getEmployeeById(1L);
        assertEquals(employee, result);
    }

    @Test
    public void testGetAllEmployees_Success() {
        List<Employee> employees = List.of(employee);
        when(employeeDao.getEmployees()).thenReturn(employees);
        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(employees, result);
    }
}

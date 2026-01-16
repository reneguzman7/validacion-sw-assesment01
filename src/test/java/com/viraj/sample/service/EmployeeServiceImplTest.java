package com.viraj.sample.service;

import com.viraj.sample.entity.Employee;
import com.viraj.sample.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    private static final String NOMBRE_EMPLEADO = "Rene Guzman";
    private static final String DESCRIPCION_EMPLEADO = "Desarrollador";

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee();
        employee.setEmployeeName(NOMBRE_EMPLEADO);
        employee.setEmployeeDescription(DESCRIPCION_EMPLEADO);
    }

    @Test
    public void testGuardarEmpleado() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee resultado = employeeService.saveEmployee(employee);

        assertNotNull(resultado);
        assertEquals(NOMBRE_EMPLEADO, resultado.getEmployeeName());
        verify(employeeRepository).save(employee);
    }

    @Test
    public void testObtenerEmpleadoPorId() {
        // Arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Act
        Employee resultado = employeeService.getEmployee(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(NOMBRE_EMPLEADO, resultado.getEmployeeName());
        verify(employeeRepository).findById(1L);
    }
}

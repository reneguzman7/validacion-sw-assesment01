package com.viraj.sample.integration;

import com.viraj.sample.entity.Employee;
import com.viraj.sample.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    public void testEndpointHello() throws Exception {
        mockMvc.perform(get("/employee/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello boot"));
    }

    @Test
    public void testGuardarYObtenerEmpleado() throws Exception {
        String jsonEmpleado = "{\"employeeName\":\"Test\",\"employeeDescription\":\"Desc\"}";

        mockMvc.perform(post("/employee/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEmpleado))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Test"));
    }
}

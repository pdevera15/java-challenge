package jp.co.axa.apidemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiDemoApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private EmployeeService employeeService;
	
  @Test
  public void test_get_all_employees_OK() throws Exception {
    mvc.perform(get("/api/v1/employees"))
        .andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andReturn();
  }

	@Test
  public void test_get_employee_by_id_OK() throws Exception {
		Long employeeId = 1L;

		Employee toBeRegEmployee = new Employee();
		toBeRegEmployee.setName("name01");
		toBeRegEmployee.setSalary(1100);
		toBeRegEmployee.setDepartment("dept01");
		employeeService.saveOrUpdateEmployee(toBeRegEmployee);

    mvc.perform(get("/api/v1/employees/{employeeId}", employeeId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("name01")))
        .andExpect(jsonPath("$.salary", is(1100)))
        .andExpect(jsonPath("$.department", is("dept01")))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andReturn();
  }

	@Test
  public void test_get_employee_by_id_not_found() throws Exception {
		Long employeeId = 9999L;

    mvc.perform(get("/api/v1/employees/{employeeId}", employeeId))
        .andExpect(status().isNotFound())
				.andExpect(content().string("Employee not found"))
        .andReturn();
  }

	@Test
  public void test_add_employee_OK() throws Exception {
		Employee toBeRegEmployee = new Employee();
		toBeRegEmployee.setName("name01");
		toBeRegEmployee.setSalary(1100);
		toBeRegEmployee.setDepartment("dept01");

    mvc.perform(post("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(toBeRegEmployee)))
        .andExpect(status().isOk())
				.andExpect(content().string("Employee Saved Successfully"))
        .andReturn();
  }
}

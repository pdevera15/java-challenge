package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Constructor
     * @param employeeService
     */
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Retrieves a list of all employees.
     *
     * @return employee
     */
    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    /**
     * Retrieves an employee.
     *
     * @return employee
     */
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<?> getEmployee(@PathVariable("employeeId")Long employeeId) {
        
    	Employee employee = employeeService.getEmployee(employeeId);
    	if (employee == null) {
    		return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Save an employee
     * 
     * @param employee
     * @return
     */
    @PostMapping("/employees")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee){
        employeeService.saveOrUpdateEmployee(employee);
        return new ResponseEntity<>("Employee Saved Successfully" , HttpStatus.OK);
    }

    /**
     * Delete an employee
     * 
     * @param employeeId
     * @return
     */
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee Deleted Successfully" , HttpStatus.OK);
    }

    /**
     * Edit an employee
     * 
     * @param employee
     * @param employeeId
     * @return
     */
    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee,
                               @PathVariable("employeeId")Long employeeId){
        Employee existingEmployee = employeeService.getEmployee(employeeId);
        if(existingEmployee != null){
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setName(employee.getName());
            existingEmployee.setSalary(employee.getSalary());
            employeeService.saveOrUpdateEmployee(existingEmployee);
            return new ResponseEntity<>("Employee Updated Successfully", HttpStatus.OK);            
        }

        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);  
    }

}

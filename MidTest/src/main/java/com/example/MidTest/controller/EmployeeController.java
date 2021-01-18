package com.example.MidTest.controller;

import com.example.MidTest.ResourceNotFoundException;
import com.example.MidTest.entity.Employee;
import com.example.MidTest.entity.EmployeeUpdateDto;
import com.example.MidTest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api")
@Validated
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/{emp_no}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "emp_no") String emp_No) {
        Employee employee = employeeRepository.findById(emp_No)
                .orElseThrow(() -> new ResourceNotFoundException("Employee tidak ditemukan " + emp_No));
        return ResponseEntity.ok().body(employee);

    }

    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        Random random = new Random();
        String randNum = String.format("%04d", random.nextInt(5000));
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("ddMMyyyy");
        String dateNow = DateFor.format(date);
        employee.setEmp_no(employee.getDept_no()+dateNow+randNum);
        return employeeRepository.save(employee);
    }

    @PutMapping("/employee/edit/{emp_no}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "emp_no") String emp_No,
                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(emp_No)
                .orElseThrow(() -> new ResourceNotFoundException("Employee tidak ditemukan" + emp_No));

        employee.setFirst_name(employeeDetails.getFirst_name());
        employee.setLast_name(employeeDetails.getLast_name());
        employee.setGender(employeeDetails.getGender());
        employee.setBirth_date(employeeDetails.getBirth_date());
        final Employee updatedEmployee = employeeRepository.save(employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employee/del/{emp_no}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("emp_no") String emp_No) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(emp_No)
                .orElseThrow(() -> new ResourceNotFoundException("Employee tidak ditemukan" + emp_No));

        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/employee/up/{emp_no}")
    public ResponseEntity<Void> updatePerson(@Valid @PathVariable("emp_no") String emp_No,
                                             @RequestBody EmployeeUpdateDto employeeUpdateDto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(emp_No);
        if (!employeeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = employeeOptional.get();
        if (employeeUpdateDto.getFirst_name() != null) {
            employee.setFirst_name(employeeUpdateDto.getFirst_name());
        }
        if (employeeUpdateDto.getLast_name() != null) {
            employee.setLast_name(employeeUpdateDto.getLast_name());
        }
        if (employeeUpdateDto.getGender() != null) {
            employee.setGender(employeeUpdateDto.getGender());
        }
        if (employeeUpdateDto.getBirth_date() != null) {
            employee.setBirth_date(employeeUpdateDto.getBirth_date());
        }
        if (employeeUpdateDto.getDept_no() != null) {
            employee.setDept_no(employeeUpdateDto.getDept_no());
        }

        employeeRepository.save(employee);

        return ResponseEntity.noContent().build();
    }
}


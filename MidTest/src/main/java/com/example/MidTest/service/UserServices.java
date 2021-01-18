package com.example.MidTest.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.MidTest.entity.Employee;
import com.example.MidTest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> listAll() {
        return employeeRepository.findAll(Sort.by("gender").ascending());
    }

}
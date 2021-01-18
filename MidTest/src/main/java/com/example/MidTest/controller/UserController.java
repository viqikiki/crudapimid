package com.example.MidTest.controller;

import com.example.MidTest.entity.Employee;
import com.example.MidTest.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

    @Controller
    public class UserController {

        @Autowired
        private UserServices service;


        @GetMapping("/employee/download")
        public void exportToCSV(HttpServletResponse response) throws IOException {
            response.setContentType("text/csv");
            DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=first_name_" + currentDateTime + ".csv";
            response.setHeader(headerKey, headerValue);

            List<Employee> listEmployee = service.listAll();

            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"Employee No", "First Name", "Last Name", "Gender", "Birth Date", "Dept No"};
            String[] nameMapping = {"emp_no", "first_name", "last_name", "gender", "birth_date", "dept_no"};

            csvWriter.writeHeader(csvHeader);

            for (Employee employee : listEmployee) {
                csvWriter.write(employee, nameMapping);
            }

            csvWriter.close();

        }

    }

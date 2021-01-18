package com.example.MidTest.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class EmployeeReadDto {
    private String emp_no;
    private String first_name;
    private String last_name;
    private String gender;
    private Date birth_date;
    private String dept_no;

    public EmployeeReadDto(String emp_no, String first_name, String last_name, String gender, Date birth_date, String dept_no) {
        this.emp_no = emp_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.dept_no = dept_no;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public String getDept_no() {
        return dept_no;
    }
}

package com.example.MidTest.entity;

import java.util.Date;

public class EmployeeUpdateDto {
    private String emp_no;
    private String first_name;
    private String last_name;
    private String gender;
    private Date birth_date;
    private String dept_no;

    protected EmployeeUpdateDto(){

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

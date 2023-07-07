package com.visit.program.ReservationProgram.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * employee 테이블 매핑 클래스
 * */
@Getter
@NoArgsConstructor
public class Employee {
    private Long id;
    private String loginId;
    private String part_name;
    private Boolean is_admin;
    private String employee_name;
    private String password;
    private String phone_number;

    public Employee(String loginId, String part_name, Boolean is_admin, String employee_name, String password, String phone_number) {
        this.loginId = loginId;
        this.part_name = part_name;
        this.is_admin = is_admin;
        this.employee_name = employee_name;
        this.password = password;
        this.phone_number = phone_number;
    }
}

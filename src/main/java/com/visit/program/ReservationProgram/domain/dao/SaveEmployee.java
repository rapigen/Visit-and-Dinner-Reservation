package com.visit.program.ReservationProgram.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@Setter
public class SaveEmployee {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String employee_name;
    @NotEmpty
    private String part_name;

//    @Pattern(regexp = "^0([0-9]{1,2})([0-9]{7,8})$")
    private String phone_number;

}


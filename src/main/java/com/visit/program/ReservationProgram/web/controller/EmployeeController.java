package com.visit.program.ReservationProgram.web.controller;

import com.visit.program.ReservationProgram.domain.dao.Employee;
import com.visit.program.ReservationProgram.domain.dao.SaveEmployee;
import com.visit.program.ReservationProgram.domain.dao.session.SessionConst;
import com.visit.program.ReservationProgram.domain.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
/**
 * 직원 정보 관련 Controller 클래스
 * Repository -> Service -> *Controller
 * */
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reservation/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/home")
    public String homePage(HttpSession session){
        if(session.getAttribute(SessionConst.DINNER_PROGRAM)!=null) {
            return "redirect:/dinner/info/all";
        }
        return "redirect:/reservation/info/all";
    }

    @GetMapping("/save")
    public String saveEmployee(@ModelAttribute("employee")SaveEmployee employee){
        return "employee/SaveForm";
    }


    @PostMapping("/send")
    @ResponseBody
    public HashMap<String,Object> send(@RequestBody HashMap<String,Object> sendDTO){
        String loginId = String.valueOf(sendDTO.get("loginId"));
        Employee employee = employeeService.findByLoginId(loginId);
        sendDTO.replace("loginId",employee.getLoginId());
        return sendDTO;
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee")SaveEmployee employee, BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()){
            return "employee/SaveForm";
        }
        employeeService.saveEmployee(employee);
        if(session.getAttribute(SessionConst.DINNER_PROGRAM)!=null) {
            return "redirect:/dinner/info/dateOfQty";
        }
        return "redirect:/reservation/info/save";
    }
//
//    @GetMapping("/update/{id}")
//    public String pwUpdate(@PathVariable("id")Long id, @ModelAttribute("employee") UpdateEmployeeDTO employee){
//        setEmployee(id,employee);
//        return "view/UpdatePassword";
//    }
//
//    private void setEmployee(Long id,UpdateEmployeeDTO employee){
//        Employee employeeInfo = employeeService.findById(id);
//        employee.setId(employeeInfo.getId());
//        employee.setBeforePassword(employeeInfo.getPassword());
//        employee.setEmployee_name(employeeInfo.getEmployee_name());
//        employee.setLoginId(employeeInfo.getLoginId());
//    }
//    @PostMapping("/update/{employeeId}")
//    public String pwUpdate2( @PathVariable("employeeId")Long id,@Valid @ModelAttribute("employee")UpdateEmployeeDTO employee, BindingResult bindingResult){
//        updateCheck(employee,bindingResult);
//        if(bindingResult.hasErrors()){
//            return "visit/UpdatePassword";
//        }
//        employeeService.updatePassword(employee.getPassword(), id);
//        return "redirect:/reservation/info/all";
//    }
//
//    private void updateCheck(UpdateEmployeeDTO employee,BindingResult bindingResult){
//        if(!employee.getPassword().equals(employee.getPasswordCheck())){
//            bindingResult.reject("globalError","비밀번호가 일치하지 않습니다.");
//        }
//    }


}

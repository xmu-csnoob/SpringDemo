package com.example.staff.controller;

import com.example.privilege.aop.Audit;
import com.example.staff.model.PostStaffVo;
import com.example.staff.service.StaffService;
import com.example.staff.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/", produces = "application/json;charset=UTF-8")
public class StaffController {
    @Autowired
    StaffService staffService;
    @GetMapping("/staff/{id}")
    @Audit(authName = "查看所有人信息")
    public Object getStaff(@RequestHeader String Authorization,@PathVariable Long id){
        return staffService.getStaff(id);
    }
    @Audit(authName = "新增用户")
    @PostMapping("/staff")
    public Object insertStaff(@RequestHeader String Authorization,@RequestBody @Validated PostStaffVo staffVo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ReturnObject(507L,"数据格式不合法");
        }
        return staffService.insertStaff(staffVo);
    }
    @Audit(authName = "删除用户")
    @DeleteMapping("/staff/{id}")
    public Object delStaff(@RequestHeader String Authorization,@PathVariable Long id){
        return staffService.delStaff(id);
    }
    @Audit(authName = "修改用户")
    @PutMapping("/staff/{id}")
    public Object updateStaff(@RequestHeader String Authorization,@RequestBody @Validated PostStaffVo staffVo, BindingResult bindingResult, @PathVariable Long id){
        if(bindingResult.hasErrors()){
            return new ReturnObject(507L,"数据格式不合法");
        }
        staffVo.setId(id);
        return staffService.updateStaff(staffVo);
    }
}

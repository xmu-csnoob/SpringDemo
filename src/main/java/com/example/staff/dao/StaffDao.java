package com.example.staff.dao;

import com.example.staff.mapper.StaffMapper;
import com.example.staff.model.StaffBo;
import com.example.staff.model.StaffPo;
import com.example.staff.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class StaffDao {
    @Autowired
    StaffMapper staffMapper;
    public ReturnObject<StaffBo> getStaff(Long id){
        try {
            StaffPo staffPo = staffMapper.getStaffByPrimaryKey(id);
            if(staffPo==null){
                return new ReturnObject<>(404L,"员工id不存在");
            }
            StaffBo staffBo = getBoFromPo(staffPo);
            return new ReturnObject<StaffBo>(staffBo);
        }catch(Exception e){
            return new ReturnObject<StaffBo>(500L,e.getMessage());
        }
    }
    public ReturnObject insertStaff(StaffBo staffBo){
        try{
            StaffPo staffPo=getPoFromBo(staffBo);
            staffPo.setGmtRegister(LocalDateTime.now());
            staffMapper.insertStaff(staffPo);
            return new ReturnObject<StaffBo>(0L,"成功");
        }catch (Exception e){
            return new ReturnObject<StaffBo>(500L,e.getMessage());
        }
    }
    public ReturnObject delStaff(Long id){
        try{
            int ret=staffMapper.delStaffByPrimaryKey(id);
            if(ret==1){
                return new ReturnObject(0L,"成功");
            }else{
                return new ReturnObject(404L,"员工id不存在");
            }
        }catch (Exception e){
            return new ReturnObject(500L,e.getMessage());
        }
    }
    public ReturnObject updateStaff(StaffBo staffBo){
        try{
            StaffPo staffPo=new StaffPo();
            if(staffBo.getName()!=null) {
                staffPo.setName(staffBo.getName());
            }
            if(staffBo.getSalary()!=0){
                staffPo.setSalary(staffBo.getSalary());
            }
            staffPo.setId(staffBo.getId());
            staffPo.setGmtModified(LocalDateTime.now());
            int ret=staffMapper.updateStaffByExample(staffPo);
            if(ret==1){
                return new ReturnObject(0L,"成功");
            }else{
                return new ReturnObject(404L,"员工id不存在");
            }
        } catch (Exception e) {
            return new ReturnObject(500L,e.getMessage());
        }
    }
    private StaffBo getBoFromPo(StaffPo staffPo){
        StaffBo staffBo=new StaffBo();
        staffBo.setId(staffPo.getId());
        staffBo.setName(staffPo.getName());
        staffBo.setSalary(staffPo.getSalary());
        return staffBo;
    }
    private StaffPo getPoFromBo(StaffBo staffBo){
        StaffPo staffPo=new StaffPo();
        if(staffPo.getName()!=null){
            staffPo.setName(staffBo.getName());
        }
        if(staffPo.getSalary()!=0){
            staffPo.setSalary(staffBo.getSalary());
        }
        return staffPo;
    }
}

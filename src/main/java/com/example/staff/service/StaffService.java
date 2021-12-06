package com.example.staff.service;

import com.example.staff.dao.StaffDao;
import com.example.staff.model.GetStaffVo;
import com.example.staff.model.StaffBo;
import com.example.staff.model.PostStaffVo;
import com.example.staff.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    @Autowired
    StaffDao staffDao;
    public ReturnObject getStaff(Long id){
        ReturnObject<StaffBo> ret=staffDao.getStaff(id);
        StaffBo staffBo= ret.getData();
        if(staffBo!=null) {
            GetStaffVo staffVo = new GetStaffVo();
            staffVo.setName(staffBo.getName());
            staffVo.setSalary(staffBo.getSalary());
            return new ReturnObject<GetStaffVo>(staffVo);
        }else{
            return ret;
        }
    }
    public ReturnObject insertStaff(PostStaffVo staffVo){
        StaffBo staffBo=new StaffBo();
        staffBo.setName(staffVo.getName());
        staffBo.setSalary(staffVo.getSalary());
        staffDao.insertStaff(staffBo);
        return new ReturnObject(0L,"插入成功");
    }
    public ReturnObject delStaff(Long id){
        return staffDao.delStaff(id);
    }
    public ReturnObject updateStaff(PostStaffVo staffVo){
        StaffBo staffBo=new StaffBo();
        staffBo.setId(staffVo.getId());
        staffBo.setName(staffVo.getName());
        staffBo.setSalary(staffVo.getSalary());
        return staffDao.updateStaff(staffBo);
    }
}

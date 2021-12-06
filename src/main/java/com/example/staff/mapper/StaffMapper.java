package com.example.staff.mapper;

import com.example.staff.model.StaffPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Wenfei Wang
 */
@Mapper
public interface StaffMapper {
    int insertStaff(StaffPo staffPo);
    StaffPo getStaffByPrimaryKey(Long id);
    int delStaffByPrimaryKey(Long id);
    int updateStaffByExample(StaffPo staffPo);
}

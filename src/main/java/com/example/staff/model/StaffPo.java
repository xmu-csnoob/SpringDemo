package com.example.staff.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StaffPo {
    Long id;
    String name;
    int salary;
    LocalDateTime gmtRegister;
    LocalDateTime gmtModified;
}

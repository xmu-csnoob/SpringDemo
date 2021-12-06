package com.example.staff.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Data
public class PostStaffVo {
    Long id;
    String name;
    int salary;
}

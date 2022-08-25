package com.example.itviec.model;

import com.example.itviec.repository.EmployerRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor

public class Job {
    private String id;
    private String title;
    private String companyId;
    private String companyName;
    private List<Skill> skills;
    private List<Location> locations;
    private int minSalary;
    private int maxSalary;
    private String description;


}

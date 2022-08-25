package com.example.itviec.request;

import com.example.itviec.model.Location;
import com.example.itviec.model.Skill;
import com.example.itviec.repository.EmployerRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {
    private String title;
    private String companyId;
    private String companyName;
    private List<Skill> skills;
    private List<Location> locations;
    private int minSalary;
    private int maxSalary;
    private String description;


}

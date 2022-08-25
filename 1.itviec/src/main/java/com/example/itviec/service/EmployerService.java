package com.example.itviec.service;

import com.example.itviec.model.CompanyType;
import com.example.itviec.model.Employer;
import com.example.itviec.model.Location;
import com.example.itviec.repository.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployerService {
    @Autowired
    EmployerRepo employerRepo;

    public List<Employer> findByKeyWord(String keyword){
       return employerRepo.findByKeyWord(keyword);
    }


    public List<Employer> filterByType(List<Employer> employers,String type) {
        List<Employer> newEmployers = employers.stream().filter(item -> item.getCompanyType().name().equals(type)).collect(Collectors.toList());
        return newEmployers;
    }

    public List<Employer> filterByLocation(List<Employer> employers, String location) {
        List<Employer> newEmployers = employers.stream().
                filter(item -> item.getLocations().stream().anyMatch(location1 -> location1.name().equals(location)))
                .collect(Collectors.toList());
        return newEmployers;
    }



}

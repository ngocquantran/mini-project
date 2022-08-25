package com.example.itviec.service;

import com.example.itviec.model.Employer;
import com.example.itviec.model.Job;
import com.example.itviec.repository.EmployerRepo;
import com.example.itviec.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    JobRepo jobRepo;

    public List<Job> findByKeyWord(String keyword){
        return jobRepo.findByKeyWord(keyword);
    }


    public List<Job> filterBySkill(List<Job> jobs,String skill) {
        List<Job> newJobs = jobs.stream().
                filter(item -> item.getSkills().stream().anyMatch(skill1  -> skill1.name().equals(skill)))
                .collect(Collectors.toList());
        return newJobs;
    }

    public List<Job> filterByLocation(List<Job> jobs, String location) {
        List<Job> newJobs = jobs.stream().
                filter(item -> item.getLocations().stream().anyMatch(skill1  -> skill1.name().equals(location)))
                .collect(Collectors.toList());
        return newJobs;
    }
}

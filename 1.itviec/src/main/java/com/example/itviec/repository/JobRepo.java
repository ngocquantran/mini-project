package com.example.itviec.repository;

import com.example.itviec.model.*;
import com.example.itviec.request.EmployerRequest;
import com.example.itviec.request.JobRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class JobRepo {


    private ConcurrentHashMap<String, Job> jobs;
    public JobRepo() {
        jobs=new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, Job> getAllJobs(){
        return jobs;
    }

    public ConcurrentHashMap<String, Job> addJob(JobRequest jobRequest){
        String id= UUID.randomUUID().toString();
        jobs.put(id,new Job(id,jobRequest.getTitle(), jobRequest.getCompanyId(), jobRequest.getCompanyName(), jobRequest.getSkills(),jobRequest.getLocations(), jobRequest.getMinSalary(), jobRequest.getMaxSalary(), jobRequest.getDescription()));
        return jobs;
    }

    public ConcurrentHashMap<String, Job> deleteJob(String id){
        jobs.remove(id);
        return jobs;
    }

    public List<Job> findByKeyWord( String keyword){
        List<Job> list=getAllJobs().values().stream().
                filter(item -> item.getTitle().toLowerCase().contains(keyword.toLowerCase())  || item.getCompanyName().toLowerCase().contains(keyword.toLowerCase()) || item.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return list;
    }
}

package com.example.itviec.repository;

import com.example.itviec.model.Applicant;
import com.example.itviec.request.ApplicantRequest;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ApplicantRepo {
    private ConcurrentHashMap<String, Applicant> applicants;

    public ApplicantRepo(){
        applicants=new ConcurrentHashMap<>();
        String id= UUID.randomUUID().toString();
        applicants.put(id,new Applicant(id,"Trần Văn Mạnh","manh@gmail.com","0965486529","HTML, CSS, Java, Spring boot","1234567"));
    }

    public ConcurrentHashMap<String, Applicant> getAllApplicants(){
        return applicants;
    }

    public ConcurrentHashMap<String, Applicant> addApplicant(ApplicantRequest request){
        String id= UUID.randomUUID().toString();
        applicants.put(id,new Applicant(id, request.getName(), request.getEmail(), request.getPhone(), request.getSkill(), request.getJobId()));
        return applicants;
    }

    public ConcurrentHashMap<String, Applicant> deleteApplicant(String id){
        applicants.remove(id);
        return applicants;
    }

}

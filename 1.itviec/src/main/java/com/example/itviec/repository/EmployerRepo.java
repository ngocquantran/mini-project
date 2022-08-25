package com.example.itviec.repository;

import com.example.itviec.model.CompanyType;
import com.example.itviec.model.Employer;
import com.example.itviec.model.Location;
import com.example.itviec.request.EmployerRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class EmployerRepo {
    private ConcurrentHashMap<String, Employer> employers;

    public EmployerRepo(){
        employers=new ConcurrentHashMap<>();
        String id1= UUID.randomUUID().toString();
        employers.put(id1,new Employer(id1,"/upload_img/vincere.jpg","Vincere", CompanyType.product, List.of(Location.hochiminh),"quan31794@gmail.com"));
        String id2= UUID.randomUUID().toString();
        employers.put(id2,new Employer(id2,"/upload_img/bluebottle.png","BlueBottle Digital Việt Nam", CompanyType.product, List.of(Location.hanoi),"quan31794@gmail.com"));
        String id3= UUID.randomUUID().toString();
        employers.put(id3,new Employer(id3,"/upload_img/momo.jpg","M_Service (MoMo)", CompanyType.product, List.of(Location.hochiminh,Location.danang),"quan31794@gmail.com"));
        String id4= UUID.randomUUID().toString();
        employers.put(id4,new Employer(id4,"/upload_img/tyme.png","Tyme", CompanyType.outsource, List.of(Location.hochiminh,Location.hanoi),"quan31794@gmail.com"));
    }

    public ConcurrentHashMap<String, Employer> getEmployers(){
        return employers;
    }

    public ConcurrentHashMap<String, Employer> addEmployers(EmployerRequest employerRequest){
        String id= UUID.randomUUID().toString();
        employers.put(id,new Employer(id, employerRequest.getLogoPath(), employerRequest.getCompanyName(), employerRequest.getCompanyType(),employerRequest.getLocations(), employerRequest.getEmail()));
        return employers;
    }

    public ConcurrentHashMap<String, Employer> deleteEmployer(String id){
       employers.remove(id);
        return employers;
    }

    public List<Employer> findByKeyWord( String keyword){
        List<Employer> list=getEmployers().values().stream().
                filter(item -> item.getCompanyName().toLowerCase().contains(keyword.toLowerCase())  || item.getLocations().contains(keyword))
                .collect(Collectors.toList());
        return list;
    }
}

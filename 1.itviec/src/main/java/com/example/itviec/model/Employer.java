package com.example.itviec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employer {
    private String id;
    private String logoPath;
    private String companyName;
    private CompanyType companyType;
    private List<Location> locations;

//    @Email(message = "Not valid email")
    private String email;


}



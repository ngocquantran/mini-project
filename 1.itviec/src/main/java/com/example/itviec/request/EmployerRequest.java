package com.example.itviec.request;

import com.example.itviec.model.CompanyType;
import com.example.itviec.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor

public class EmployerRequest {
    private String logoPath;
    private String companyName;
    private CompanyType companyType;
    private List<Location> locations;
    private String email;

    public EmployerRequest() {
        logoPath = "/img/default-avatar.jpg";
    }
}

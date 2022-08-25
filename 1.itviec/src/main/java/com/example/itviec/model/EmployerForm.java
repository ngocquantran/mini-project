package com.example.itviec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerForm {
    private MultipartFile image;
    private String companyName;
    private CompanyType companyType;
    private List<Location> locations;
    private String email;
}

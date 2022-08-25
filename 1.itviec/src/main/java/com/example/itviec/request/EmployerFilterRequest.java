package com.example.itviec.request;

import com.example.itviec.model.CompanyType;
import com.example.itviec.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerFilterRequest {
    private CompanyType type;
    private Location location;
}

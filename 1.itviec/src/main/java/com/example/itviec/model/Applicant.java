package com.example.itviec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String skill;
    private String jobId;

}

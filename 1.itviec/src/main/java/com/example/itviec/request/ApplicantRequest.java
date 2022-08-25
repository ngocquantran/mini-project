package com.example.itviec.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantRequest {
    private String name;
    private String email;
    private String phone;
    private String skill;
    private String jobId;
}

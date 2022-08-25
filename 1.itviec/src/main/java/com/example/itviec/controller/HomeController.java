package com.example.itviec.controller;

import com.example.itviec.model.CompanyType;
import com.example.itviec.model.EmployerForm;
import com.example.itviec.model.Location;
import com.example.itviec.repository.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private EmployerRepo employerRepo;

    @GetMapping
    public String homePage() {
        return "index";
    }






}

package com.example.itviec.controller;

import com.example.itviec.model.CompanyType;
import com.example.itviec.model.Employer;
import com.example.itviec.model.EmployerForm;
import com.example.itviec.model.Location;
import com.example.itviec.repository.EmployerRepo;
import com.example.itviec.request.EmployerFilterRequest;
import com.example.itviec.request.EmployerRequest;
import com.example.itviec.service.EmployerService;
import com.example.itviec.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployerController {

    @Autowired
    private EmployerRepo employerRepo;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private StorageService storageService;

    @GetMapping("/employers")
    public String goToEmployers(Model model, String keyword, String type, String location) {
        model.addAttribute("companyTypes", CompanyType.values());
        model.addAttribute("locations", Location.values());
        List<Employer> listE = employerRepo.getEmployers().values().stream().toList();

        if (keyword != null) {
            model.addAttribute("employers", employerService.findByKeyWord(keyword));
        } else if (type != null && location != null) {
            if (!type.equals("0")) {
                listE = employerService.filterByType(listE, type);
            }
            if (!location.equals("0")) {
                listE = employerService.filterByLocation(listE, location);
            }
            model.addAttribute("employers", listE);
        } else {
            model.addAttribute("employers", employerRepo.getEmployers().values());
        }
        return "employers";
    }


    @GetMapping("/employers/delete/{id}")
    public String deleteEmployer(Model model, @PathVariable String id) {
        model.addAttribute("employers", employerRepo.getEmployers().values());
        model.addAttribute("companyTypes", CompanyType.values());
        model.addAttribute("locations", Location.values());
        model.addAttribute("id", id);

        employerRepo.deleteEmployer(id);
        return "redirect:/employers";
    }

    @GetMapping("/employers/edit/{id}")
    public String goToEditEmployer(Model model, @PathVariable String id) {
        Employer employer = employerRepo.getEmployers().get(id);
        model.addAttribute("employer", employer);
        model.addAttribute("types", CompanyType.values());
        model.addAttribute("locations", Location.values());
        model.addAttribute("id", id);
        return "employer_edit";
    }

    @PostMapping("/employers/edit/{id}")
    public String handleEditEmployer(@ModelAttribute EmployerRequest employerRequest, Model model, @PathVariable String id, BindingResult bindingResult) {
        Employer employer = employerRepo.getEmployers().get(id);
        model.addAttribute("employer", employer);
        model.addAttribute("types", CompanyType.values());
        model.addAttribute("locations", Location.values());
        model.addAttribute("id", id);
        employerRepo.getEmployers().put(id, new Employer(id, employer.getLogoPath(), employerRequest.getCompanyName(), employerRequest.getCompanyType(), employerRequest.getLocations(), employerRequest.getEmail()));

        return "redirect:/employers";
    }


    @GetMapping("/employers/add")
    public String goToEmployersAdd(Model model) {
        model.addAttribute("locations", Location.values());
        model.addAttribute("types", CompanyType.values());
        model.addAttribute("newEmployer", new EmployerForm());
        return "employer_add";
    }


    @PostMapping("/employers/add")
    public String handleAddEmployer(@ModelAttribute EmployerForm employerForm, Model model, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            System.out.println(employerForm);
        }else {
            return "employer_add";
        }

        storageService.uploadFile(employerForm.getImage());

        model.addAttribute("locations", Location.values());
        model.addAttribute("types", CompanyType.values());
        model.addAttribute("newEmployer", employerForm);
        String pathImg="/upload_img/"+ employerForm.getImage().getOriginalFilename()+"";
        EmployerRequest newEmpoyerRequest=new EmployerRequest(pathImg,employerForm.getCompanyName(),employerForm.getCompanyType(),employerForm.getLocations(),employerForm.getEmail());
        newEmpoyerRequest.setLogoPath(pathImg);
        employerRepo.addEmployers(newEmpoyerRequest);
        System.out.println(newEmpoyerRequest);

        return "redirect:/employers";
    }


}

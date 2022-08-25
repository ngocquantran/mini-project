package com.example.itviec.controller;

import com.example.itviec.model.*;
import com.example.itviec.repository.EmployerRepo;
import com.example.itviec.repository.JobRepo;
import com.example.itviec.request.JobRequest;
import com.example.itviec.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private EmployerRepo employerRepo;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private JobService jobService;

    @GetMapping
    public String goToJobs(Model model, String keyword, String skill, String location) {
        model.addAttribute("skills", Skill.values());
        model.addAttribute("locations", Location.values());

        List<Job> listE = jobRepo.getAllJobs().values().stream().toList();

        if (keyword != null) {
            model.addAttribute("jobs", jobService.findByKeyWord(keyword));
        } else if (skill != null && location != null) {
            if (!skill.equals("0")) {
                listE = jobService.filterBySkill(listE, skill);
            }
            if (!location.equals("0")) {
                listE = jobService.filterByLocation(listE, location);
            }
            model.addAttribute("jobs", listE);
        } else {
            model.addAttribute("jobs", jobRepo.getAllJobs().values());
        }


        return "jobs";
    }


    @GetMapping("/delete/{id}")
    public String deleteJob(Model model, @PathVariable String id) {
        model.addAttribute("skills", Skill.values());
        model.addAttribute("locations", Location.values());
        model.addAttribute("jobs", jobRepo.getAllJobs().values());
        model.addAttribute("id", id);
        jobRepo.deleteJob(id);
        return "redirect:/jobs";
    }

    @GetMapping("/edit/{id}")
    public String goToEditJob(Model model, @PathVariable String id) {
        Job job = jobRepo.getAllJobs().get(id);
        model.addAttribute("employers", employerRepo.getEmployers().values());
        model.addAttribute("skills", Skill.values());
        model.addAttribute("locations", Location.values());
        model.addAttribute("job", job);
        model.addAttribute("id", id);
        return "job_edit";
    }

    @PostMapping("/edit/{id}")
    public String handleEditJob(@ModelAttribute JobRequest jobRequest, Model model, @PathVariable String id) {
        model.addAttribute("employers", employerRepo.getEmployers().values());
        model.addAttribute("skills", Skill.values());
        model.addAttribute("locations", Location.values());
        model.addAttribute("job", jobRequest);
        model.addAttribute("id", id);
        jobRequest.setCompanyName(employerRepo.getEmployers().get(jobRequest.getCompanyId()).getCompanyName());
        jobRepo.getAllJobs().put(id, new Job(id, jobRequest.getTitle(), jobRequest.getCompanyId(), jobRequest.getCompanyName(), jobRequest.getSkills(), jobRequest.getLocations(), jobRequest.getMinSalary(), jobRequest.getMaxSalary(), jobRequest.getDescription()));
        return "redirect:/jobs";
    }

    @GetMapping("/add")
    public String viewAddJob(Model model) {
        model.addAttribute("employers", employerRepo.getEmployers().values());
        model.addAttribute("skills", Skill.values());
        model.addAttribute("locations", Location.values());
        model.addAttribute("newJob", new JobRequest());
        return "job_add";
    }

    @PostMapping("/add")
    public String handleAddJob(@ModelAttribute JobRequest jobRequest, Model model, BindingResult bindingResult) {
        jobRequest.setCompanyName(employerRepo.getEmployers().get(jobRequest.getCompanyId()).getCompanyName());
        if (!bindingResult.hasErrors()) {
            System.out.println(jobRequest);
        }
        model.addAttribute("employers", employerRepo.getEmployers().values());
        model.addAttribute("skills", Skill.values());
        model.addAttribute("locations", Location.values());
        model.addAttribute("newJob", jobRequest);

        jobRepo.addJob(jobRequest);
        return "redirect:/jobs";
    }


}

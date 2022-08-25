package com.example.itviec.controller;

import com.example.itviec.model.Applicant;
import com.example.itviec.model.Location;
import com.example.itviec.model.Skill;
import com.example.itviec.repository.ApplicantRepo;
import com.example.itviec.repository.EmployerRepo;
import com.example.itviec.repository.JobRepo;
import com.example.itviec.request.ApplicantRequest;
import com.example.itviec.request.JobRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class ApplicantController {
    @Autowired
    private EmployerRepo employerRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private ApplicantRepo applicantRepo;
    @Autowired
    private JavaMailSender mailSender;


    @GetMapping("/jobs/{jobId}/applicant/add")
    public String viewAddApplicant(@ModelAttribute ApplicantRequest request, Model model, BindingResult result, @PathVariable String jobId) {
        model.addAttribute("jobId", jobId);
        model.addAttribute("applicant", request);
        return "applicant_add";
    }

    @PostMapping("/jobs/{jobId}/applicant/add")
    public String handleAddApplicant(@ModelAttribute ApplicantRequest request, Model model, BindingResult result, @PathVariable String jobId, HttpServletRequest httpServletRequest) {
        model.addAttribute("jobId", jobId);
        model.addAttribute("applicant", request);
        request.setJobId(jobId);
        applicantRepo.addApplicant(request);


//        Send Email
        String name = request.getName();
        String skill = request.getSkill();
        String jobTitle = jobRepo.getAllJobs().get(jobId).getTitle();
        System.out.println(jobTitle);
        String companyId = jobRepo.getAllJobs().get(jobId).getCompanyId();
        String companyEmail = employerRepo.getEmployers().get(companyId).getEmail();


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jobhunt");
        message.setTo(companyEmail);

        String mailSubject = jobTitle;
        String mailContent = "JobHunt xin gửi đến quý công ty thông tin ứng viên như bên dưới: \n";
        mailContent += "Họ và tên: " + name + "\n";
        mailContent += "Kĩ năng: " + skill + "\n";

        System.out.println(mailContent);

        message.setSubject(mailSubject);
        message.setText(mailContent);

        mailSender.send(message);
        System.out.println("Gửi mail thành công");

        return "redirect:/jobs/{jobId}/applicants";
    }

    @GetMapping("/jobs/{jobId}/applicants")
    public String viewJobApplicant(Model model, @PathVariable String jobId) {
        List<Applicant> list = applicantRepo.getAllApplicants().values().stream().filter(applicant -> applicant.getJobId().equals(jobId)).collect(Collectors.toList());
        model.addAttribute("applicants", list);
        model.addAttribute("jobId", jobId);


        return "job_applicants";
    }

    @GetMapping("/jobs/{jobId}/applicants/delete/{id}")
    public String deleteJobApplicant(Model model, @PathVariable String jobId, @PathVariable String id) {
        List<Applicant> list = applicantRepo.getAllApplicants().values().stream().filter(applicant -> applicant.getJobId().equals(jobId)).collect(Collectors.toList());
        model.addAttribute("applicants", list);
        model.addAttribute("jobId", jobId);
        model.addAttribute("id", id);
        applicantRepo.deleteApplicant(id);

        return "redirect:/jobs/{jobId}/applicants";
    }
}

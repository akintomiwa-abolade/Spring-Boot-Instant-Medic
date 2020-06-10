package com.jboss.hospitalmanagementsystem.controllers;

import com.jboss.hospitalmanagementsystem.models.Patient;
import com.jboss.hospitalmanagementsystem.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class LandingController {
    @Autowired
    PatientRepo patientRepo;

    @GetMapping({"/", "/index"})
    public String showLanding(){
        return "index";
    }
    @GetMapping("/appointment")
    public String showAppointment(){
        return "appointment";
    }
    @GetMapping("/blog-single")
    public String showBlog(){
        return "blog-single";
    }
    @GetMapping("/signup")
    public String showSignup(){
        return "signup";
    }
    @PostMapping("/signup")
    public String registerPatient(@RequestBody Patient patient, BindingResult result, Model model){
        if(result.hasErrors()){
            return "signup";
        }else{
            patientRepo.save(patient);
            return "redirect:all-patients";
        }
    }
    @PutMapping("/update-patient")
    public String updatePatient(@RequestBody Patient patient, int id){
        Patient existingPatient = patientRepo.findById(id).orElse(null);
        existingPatient.setFirstName(patient.getFirstName());
        existingPatient.setLastName(patient.getLastName());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setPassword(patient.getPassword());
        patientRepo.save(existingPatient);
        return "all-patients";
    }
    public String showUpdateForm(Model model) {
        model.addAttribute("patients", patientRepo.findAll());
        return "index";
    }

}

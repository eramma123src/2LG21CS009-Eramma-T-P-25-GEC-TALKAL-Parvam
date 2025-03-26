package com.example.studentcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.studentcrud.model.Staff;
import com.example.studentcrud.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/list")
    public String listStaff(Model model) {
        model.addAttribute("staff",staffService.listAll() );
        return "staff/index"; 
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff/create";
    }

    @PostMapping("/save")
    public String saveStaff(@ModelAttribute Staff sta) {
        staffService.saveStaff(sta);
        return "redirect:/staff/list";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return "redirect:/staff/list";
    }
    @GetMapping("/edit/{id}")
    public String editStaff(@PathVariable Long id,Model model) {
        model .addAttribute("staff",staffService.getStaff(id));
        return "staff/edit";
    }
   
    @PostMapping("/update/{id}")
    public String saveStaff(@PathVariable Long id, @ModelAttribute Staff sta) {
           sta.setId(id);
           staffService.saveStaff(sta);
           return "redirect:/staff/list"; 
        }
    }
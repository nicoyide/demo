package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeMapper mapper;

    @GetMapping("/")
    private String emp(){
        return "redirect:/emp-list";
    }
    @PostMapping("/insert")
    private String insert(@ModelAttribute("employee") Employee employee){
        mapper.insert(employee);
        return "redirect:/emp-list";
    }

    @GetMapping("/emp-list")
    private String employees(Model model){
        model.addAttribute("employee", mapper.findAll());
        return "employee";
    }

    @GetMapping("/delete")
    private String delete(@RequestParam("id") int id){
        mapper.deleteById(id);
        return "redirect:/emp-list";
    }

    @GetMapping("/form")
    private String updateForm(@RequestParam("id") int id, Model model){
        Employee emp = mapper.findById(id);
        if(emp != null){
            model.addAttribute("emp", emp);
        }
        return "form";
    }

    @PostMapping("/update")
    private String update(@ModelAttribute("employee") Employee employee){
        mapper.update(employee);
        return "redirect:/emp-list";
    }

}
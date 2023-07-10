package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class EmployeeController {
	 private final EmployeeRepository employeeRepository;

	    public EmployeeController(EmployeeRepository employeeRepository) {
	        this.employeeRepository = employeeRepository;

}
	    @GetMapping("/")
	    public String getAllEmployees(Model model) {
	        List<Employee> employees = employeeRepository.findAll();
	        model.addAttribute("employees", employees);
	        return "employees";
	    }

	    @GetMapping("/add")
	    public String showAddForm(Employee employee) {
	        return "add-employee";
	    }

	    @PostMapping("/add")
	    public String addEmployee(Employee employee) {
	        employeeRepository.save(employee);
	        return "redirect:/";
	    }

	    @GetMapping("/edit/{id}")
	    public String showUpdateForm(@PathVariable("id") long id, Model model) {
	        Employee employee = employeeRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
	        model.addAttribute("employee", employee);
	        return "update-employee";
	    }

	    @PostMapping("/update/{id}")
	    public String updateEmployee(@PathVariable("id") long id, Employee employee, Model model) {
	        employee.setId(id);
	        employeeRepository.save(employee);
	        return "redirect:/";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteEmployee(@PathVariable("id") long id, Model model) {
	        Employee employee = employeeRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
	        employeeRepository.delete(employee);
	        return "redirect:/";
	    }
	}

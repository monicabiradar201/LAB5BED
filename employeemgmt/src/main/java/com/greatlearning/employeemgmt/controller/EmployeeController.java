package com.greatlearning.employeemgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
 
import com.greatlearning.employeemgmt.model.Employee;
import com.greatlearning.employeemgmt.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@GetMapping("/employees")
	public String getAllEmployee(Model model) {
		List<Employee> results=service.getAllEmployees();
		model.addAttribute("employee",results);
		return "employee";
		
	}
	
	@GetMapping("/employees/new")
	public String addNewEmployee(Model model) {
		Employee result=new Employee();
		model.addAttribute("employee",result);
		return "create_employee";
		 
}
	
	@GetMapping("/employees/edit/{id}")
	public String UpdateEmployee(Model model,@PathVariable("id") int id) {
		Employee result=service.getEmployeeById(id); 
		model.addAttribute("employee",result);
		return "edit_employee";
	}

	
	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		service.deleteEmployeeById(id); 
		return "redirect:/employee";
	}

	
		@PostMapping("/employees")
		public String saveNewEmployee(@ModelAttribute("employee") Employee emp) {
			service.saveOrUpdate(emp); 
			return "redirect:/employee";
		}
		
			@PostMapping("/employees/{id}")
			public String UpdateEmployee(@ModelAttribute("employee") Employee emp,@PathVariable("id")int id) {
				Employee existingEmployee= service.getEmployeeById(id);
				existingEmployee.setFirstName(emp.getFirstName());
				existingEmployee.setLastName(emp.getLastName());
				existingEmployee.setEmail(emp.getEmail());
				service.saveOrUpdate(existingEmployee); 
				return "redirect:/employee";
			}
			}
			 
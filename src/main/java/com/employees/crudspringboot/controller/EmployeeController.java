package com.employees.crudspringboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employees.crudspringboot.entity.Employee;
import com.employees.crudspringboot.service.EmployeeService;


@Controller
@RequestMapping("/employees")
public class EmployeeController
{
	@Autowired
	private EmployeeService employeeService;

	
	@GetMapping("/list")
	public String getEmployees(Model model)
	{
		List<Employee> employees = employeeService.getEmployees();
		model.addAttribute("employees",employees);
		return "Employee-list";
	}
	
	@GetMapping("/ShowFormForAdd")
	public String ShowFormForAdd(Model model)
	{
		Employee employee = new Employee();
		model.addAttribute("employee",employee);
		return "Employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee)
	{
		employeeService.saveEmployee(theEmployee);
		return "redirect:/employees/list";
	}
	
	@GetMapping("/ShowFormForUpdate")
	public String ShowFormForUpdate(@RequestParam("employeeId") int id,Model model)
	{
		Optional<Employee> employee = employeeService.getEmployee(id);
		model.addAttribute("employee", employee);
		return "Employee-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id)
	{
		employeeService.deleteEmployee(id);
		return "redirect:/employees/list";
	}
}
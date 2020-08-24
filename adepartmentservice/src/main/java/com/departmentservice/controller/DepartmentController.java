package com.departmentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.departmentservice.model.Department;
import com.departmentservice.model.Departments;
import com.departmentservice.model.Employee;
import com.departmentservice.model.Employees;
import com.departmentservice.service.DepartmentService;
import com.departmentservice.service.EmployeeService;
@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/saveDept")
	public Department saveDept(@RequestBody Department dept)
	{
		
		
		return departmentService.createDeptServ(dept);
		
	}
	
	@GetMapping("/listDept")
	public Departments getAllDept()
	{
		List<Department> lis = departmentService.readAllDeptServ();
		Departments depts = new Departments();
		depts.setDepartments(lis);
		
		return depts;
	}
	
	@PutMapping("/updateDept/{depId}")
	public boolean updateDept(@RequestBody Department dept,@PathVariable int depId)
	{
		System.out.println("dept updating values"+depId + "name "+dept.getDeptName() +"loc "+dept.getDeptLoc());
		dept.setDeptId(depId);
		departmentService.updateDeptServ(dept);
		
		return true;
		
	}
	
	@DeleteMapping("/deleteDept/{depIds}")
	public boolean deleteDept(@PathVariable int depIds)
	{
		departmentService.delteDeptServ(depIds);
		employeeService.deleteEmpByDeptId(depIds);
		return true;
		
	}
	
	@GetMapping("/employee/listEmp/{deptId}")
	public Employees getEmployees(@PathVariable int deptId)
	{
		Employees emp = employeeService.readEmpFromDeptServ(deptId);
		
		return emp;
		
	}
	
	@PostMapping("/employee/saveEmp/{deptEmpFk}")
	public String  saveEmp(@RequestBody Employee emp,@PathVariable int deptEmpFk)
	{
		List<Department> lisDept = departmentService.readAllDeptServ();
		for (Department department : lisDept) {
			
			if(department.getDeptId() == deptEmpFk)
			{
				emp.setDeptEmpFk(deptEmpFk);
				employeeService.createEmpServ(emp);
				
				return "Employee saved Successfully";
			}
		}
		return "Dept not found ! so employee creation failed";
	}
	
	@PutMapping("/employee/updateEmp/{empId}")
	public String updateEmp(@RequestBody Employee emp,@PathVariable int empId)
	{
		emp.setEmpId(empId);
		employeeService.updateEmpServ(emp);
		
		return "Employee Updated Successfully";
	}
	
	@DeleteMapping("/employee/deleteEmp/{empId}/{deptEmpFk}")
	public String deleteEmp(@RequestBody Employee emp,@PathVariable int empId,@PathVariable int deptEmpFk)
	{
		
		employeeService.deleteEmpFromDeptServ(empId, deptEmpFk);
		
		return "Employee Deleted Successfullly";
	}
	

}

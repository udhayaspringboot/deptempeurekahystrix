package com.departmentservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.departmentservice.model.Employee;
import com.departmentservice.model.Employees;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	//@HystrixCommand(fallbackMethod = "createFallBackEmp")
	public Employee createEmpServ(Employee employee) {
		// TODO Auto-generated method stub
		
		//Employees emp = restTemplate.getForObject("", Employees.class);
		
		Employee emp =restTemplate.postForObject("http://employee-service/employee/saveEmp/"+employee.getDeptEmpFk(), employee, Employee.class);
		return emp;
	}

	@Override
	//@HystrixCommand(fallbackMethod = "createFallBackEmp")
	public boolean updateEmpServ(Employee employee) {
		// TODO Auto-generated method stub
		
		restTemplate.put("http://employee-service/employee/updateEmp/"+employee.getEmpId(), employee);
		return true;
	}

	@Override
	//@HystrixCommand(fallbackMethod = "createFallBackEmp")
	public Employees readEmpFromDeptServ(int deptId) {
		Employees emp = restTemplate.getForObject("http://employee-service/employee/listEmp/"+deptId, Employees.class);
		return emp;
	}

	@Override
	//@HystrixCommand(fallbackMethod = "createFallBackEmp")
	public boolean deleteEmpFromDeptServ(int empId, int deptId) {
		restTemplate.delete("http://employee-service/employee/deleteEmp/"+empId+"/"+deptId);
		
		return true;
	}

	@Override
	//@HystrixCommand(fallbackMethod = "createFallBackEmp")
	public boolean deleteEmpByDeptId(int deptId) {
		restTemplate.delete("http://employee-service/employee/deleteEmp/"+deptId);
		return false;
	}

	
	/*
	 * public boolean createFallBackEmp(Employee employee) {
	 * 
	 * 
	 * return false; }
	 */
	
	
}

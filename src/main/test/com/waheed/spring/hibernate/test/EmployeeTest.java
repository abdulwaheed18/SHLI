package com.waheed.spring.hibernate.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.waheed.spring.hibernate.Employee;
import com.waheed.spring.hibernate.EmployeeDao;
import com.waheed.spring.hibernate.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeTest {
	
	@Mock private EmployeeDao mockDao;

	@InjectMocks private EmployeeServiceImpl employeeServiceImpl;
	
	private Employee employee;
	
	@Before
	public void setup() {
		employee = new Employee();
		setEmployee();
	}
	
	private void setEmployee() {
		employee.setId(1);
		employee.setGender("M");
		employee.setCountry("INDIA");
		employee.setAboutYou("CRICKEETER");
		employee.setName("Sachin");
	}
	
	@Test
	public void employeeTest() throws Exception {
		when(mockDao.getEmployee(1)).thenReturn(employee);
		Employee emp =employeeServiceImpl.getEmployee(1);
		System.out.println(emp.toString());
		
		//Verify if getEmployee method was invoked on employeeServiceImpl call
		verify(mockDao).getEmployee(1);
		assertEquals("Tesing",emp.getName(),"Sachin");
	}
	
	@Test(expected=Exception.class)
	public void employeeTestFailure() throws Exception {
		when(mockDao.getEmployee(10)).thenReturn(null);
		Employee emp = employeeServiceImpl.getEmployee(10);
		
	}
}

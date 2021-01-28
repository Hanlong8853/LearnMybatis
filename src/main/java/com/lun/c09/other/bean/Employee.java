package com.lun.c09.other.bean;

import java.io.Serializable;

public class Employee implements Serializable{

	private static final long serialVersionUID = -7390587151857533202L;

	private Integer id;
	private String lastName;
	private String email;
	private String gender;
	private EmpStatus empStatus = EmpStatus.LOGOUT;
	
	public Employee() {}
	
	public Employee(Integer id, String lastName, String email, String gender) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	}
	
	public Employee(String lastName, String email, String gender) {
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return String.format("Employee [id=%s, lastName=%s, email=%s, gender=%s]", id, lastName, email, gender);
	}

	public EmpStatus getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(EmpStatus empStatus) {
		this.empStatus = empStatus;
	}

}

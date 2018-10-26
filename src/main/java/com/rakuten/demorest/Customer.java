package com.rakuten.demorest;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	private String email;
	private String name;
	private String gender;
	private Date dateOfBirth;
	private int age;
	private String city;
	private Date signupDate;
	
	public Customer(String email, String name, String gender, Date dateOfBirth, int age, String city, Date signupDate) {
		this.email = email;
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.city = city;
		this.signupDate = signupDate;
		
	}

	@Override
	public String toString() {
		return "Customer [email=" + email + ", name=" + name + ", gender=" + gender + ", dateOfBirth=" + DateFormatUtils.format(dateOfBirth,"yyyy-MM-dd")
				+ ", age=" + age + ", city=" + city + ", signupDate=" + DateFormatUtils.format(signupDate, "yyy-MM-dd hh:mm:ss.SSS") + "]";
	}
	
	

}

package pkg.person;

import java.io.Serializable;
import java.util.regex.Pattern;

abstract public class Person implements Serializable {

	
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String emailId;
	protected String phoneNumber;
	protected String address;
	protected String dob;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		boolean isValidName=Pattern.matches("[a-zA-Z]+", name);
		if(isValidName)
			this.name = name;
		else
			this.name="default Name";
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		
			this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		boolean isValidDob=Pattern.matches("\\d{2}-\\d{2}-\\d{4}", dob);
		if(isValidDob)
			this.dob = dob;
		else
		{
			this.dob="01-01-2005";
		}
	}
	public Person(String name, String emailId, String phoneNumber, String address, String dob) {
		super();
		this.setName(name);
		this.setEmailId(emailId);
		this.setPhoneNumber(phoneNumber);
		this.address = address;
		this.setDob(dob);
	}
	public Person() {
		super();
	}
	
}

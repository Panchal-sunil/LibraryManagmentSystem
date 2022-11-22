package pkg.person;

public class Student extends Person {


	private static final long serialVersionUID = 1L;
	private int rollNo;
	private int std;
	private String division;
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public int getStd() {
		return std;
	}
	public void setStd(int std) {
		this.std = std;
	}
	public String getDivison() {
		return division;
	}
	public void setDivison(String division) {
		this.division = division;
	}
	public Student(String name, String emailId, String phoneNumber, String address, String dob, int rollNo, int std,
			String division) {
		super(name, emailId, phoneNumber, address, dob);
		this.rollNo = rollNo;
		this.std = std;
		this.division = division;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", std=" + std + ", division=" + division + ", name=" + name + ", emailId="
				+ emailId + ", phoneNumber=" + phoneNumber + ", address=" + address + ", dob=" + dob + "]";
	}
	
}

package pkg.person;

public class Librarian extends Person {

	
	private static final long serialVersionUID = 1L;
	private int id;
	private String doj;
	
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Librarian [id=" + id + ", doj=" + doj + ", name=" + name + ", emailId=" + emailId + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", dob=" + dob + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public Librarian(String name, String emailId, String phoneNumber, String address, String dob, int id, String doj) {
		super(name, emailId, phoneNumber, address, dob);
		this.id = id;
		this.doj = doj;
	}
	public Librarian() {
		super();
	}
	
	
	
}

package model;

public class Student {
	//필드변수
	private String fullname;
	private String password;
	private String email;
	
	public Student(String fullname, String password, String email) {
		
		this.fullname = fullname;
		this.password = password;
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}

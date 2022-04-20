package buisnessLayer;

public class Administrator extends Person{
	private String employeeNumber;

	Administrator(String name,String DOB, String employeeNumber, String password) {
		this.name = name;
		this.DOB = DOB;
		this.employeeNumber = employeeNumber;
		this.password = password;
	}
}

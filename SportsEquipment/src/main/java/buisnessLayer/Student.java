package buisnessLayer;

public class Student extends Person{
	public String rollNumber;

	public Student(String name, String DOB, String rollNumber, String password){
		this.name = name;
		this.DOB = DOB;
		this.rollNumber = rollNumber;
		this.password = password;
	}
}

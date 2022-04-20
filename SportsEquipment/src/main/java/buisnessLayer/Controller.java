package buisnessLayer;

import serviceLayer.DBHandler;

import java.util.ArrayList;

public class Controller {
	private static ArrayList<Student> students;
	private ArrayList<Administrator> administrators;
	private static Student User;
	private static ArrayList<String> issues;

	public static void createAccount(String name,String DOB,String rollNumber,String password) {
		DBHandler.addAccount(name,DOB,rollNumber,password);
	}
	public static void updatePersonalInfo(String name, String DOB) {
		DBHandler.updatePersonalInfo(name,DOB,User.rollNumber);
	}
	public static void updateCredentials(String username, String password) {
		DBHandler.updateCredentials(username, password, User.rollNumber);
	}
	public static boolean verifyLogin(String username,String password) {
		User = DBHandler.verifyLogin(username, password);
		if (User != null) {
			return true;
		}
		return false;
	}

	public static boolean issueEquipment (String ref_id) {
		if (DBHandler.checkIssuance(User.rollNumber,ref_id)) {
			return true;
		}
		return false;
	}

	public static String getIssue() {
		return DBHandler.getIssue(User.rollNumber);
	}
	public static String returnIssue() {
		return DBHandler.returnIssue(User.rollNumber);
	}
}

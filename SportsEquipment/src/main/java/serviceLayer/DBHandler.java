package serviceLayer;

import buisnessLayer.Student;
import com.mongodb.*;

import java.util.ArrayList;

public class DBHandler {
	public static void addAccount(String name,String DOB,String username,String password) {
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

		DB db = mongoClient.getDB( "SportEquipment" );
		DBCollection coll = db.getCollection("Person");
		// Adding Data
		BasicDBObject doc = new BasicDBObject("name", name).
				append("DOB", DOB).
				append("username", username).
				append("password", password).append("Type", "student");

		coll.insert(doc);
//		System.out.println("Done");
		mongoClient.close();
	}
	public static void updatePersonalInfo(String name, String DOB, String username) {
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

		DB db = mongoClient.getDB( "SportEquipment" );
		DBCollection coll = db.getCollection("Person");

		BasicDBObject query = new BasicDBObject();
		query.put("username", username);

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("name", name);
		newDocument.put("DOB", DOB);

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);

		coll.update(query, updateObj);

		mongoClient.close();
	}
	public static void updateCredentials(String username,String password,String previousUsername) {
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

		DB db = mongoClient.getDB( "SportEquipment" );
		DBCollection coll = db.getCollection("Person");

		BasicDBObject query = new BasicDBObject();
		query.put("username", previousUsername);

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("username", username);
		newDocument.put("password", password);

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);

		coll.update(query, updateObj);

		mongoClient.close();
	}
	public static Student verifyLogin(String username,String password) {
		//boolean verification = false;
		Student student = null;
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

		DB db = mongoClient.getDB( "SportEquipment" );
		DBCollection coll = db.getCollection("Person");

		//DBCollection col = db.getCollection("mycol");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("username", username);
		searchQuery.put("password", password);

		DBCursor cursor = coll.find(searchQuery);

//		while (cursor.hasNext()) {
//			System.out.println("===========");
//			System.out.println(cursor.next());
//			System.out.println("===========");
//			//verification = true;
//		}
		if (cursor.hasNext()) {
			DBObject obj = cursor.next();
			student = new Student(obj.get("name").toString(),obj.get("DOB").toString(),obj.get("username").toString(),obj.get("password").toString());
		}

		mongoClient.close();
		return student;
	}
	public static ArrayList<String> getIssuableItems() {
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

		DB db = mongoClient.getDB( "SportEquipment" );
		DBCollection coll = db.getCollection("Equipment");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("issued", "n");

		DBCursor cursor = coll.find(searchQuery);

		ArrayList<String> items = new ArrayList<String>();

		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			String data = obj.get("ref_id").toString()+" \t"+obj.get("name").toString()+" \t"+obj.get("condition").toString();
			items.add(data);
		}

		mongoClient.close();
		return items;
	}
	public static boolean checkIssuance(String rollNumber,String ref_id) {
		ArrayList<String> Issuances = new ArrayList<String>();
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

		DB db = mongoClient.getDB( "SportEquipment" );
		DBCollection coll = db.getCollection("Issuances");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("roll_number",rollNumber);

		DBCursor cursor = coll.find(searchQuery);

		if (cursor.hasNext()) {
			return false;
		}

		BasicDBObject doc = new BasicDBObject("roll_number", rollNumber).
				append("ref_id", ref_id);

		coll.insert(doc);

		//===================================================
		DBCollection coll2 = db.getCollection("Equipment");

		BasicDBObject query = new BasicDBObject();
		query.put("ref_id", ref_id);

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("issued", "y");
//		newDocument.put("password", password);

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);

		coll2.update(query, updateObj);

		mongoClient.close();
		return true;
	}
	public static String returnIssue(String rollNumber) {
		String returnVal = null;
		ArrayList<String> Issuances = new ArrayList<String>();
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

		DB db = mongoClient.getDB( "SportEquipment" );
		DBCollection coll = db.getCollection("Issuances");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("roll_number",rollNumber);

		DBCursor cursor = coll.find(searchQuery);

		if (cursor.hasNext()) {
			DBObject obj = cursor.next();
			returnVal = obj.get("ref_id").toString();
		}
		else {
			mongoClient.close();
			return null;
		}

		coll.remove(searchQuery);
		//===================================================
		DBCollection coll2 = db.getCollection("Equipment");

		BasicDBObject query = new BasicDBObject();
		query.put("ref_id", returnVal);

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("issued", "n");

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);

		coll2.update(query, updateObj);

		mongoClient.close();
		return returnVal;
	}
	public static String getIssue(String rollNumber){
		String returnVal = null;
		ArrayList<String> Issuances = new ArrayList<String>();
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

		DB db = mongoClient.getDB( "SportEquipment" );
		DBCollection coll = db.getCollection("Issuances");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("roll_number",rollNumber);

		DBCursor cursor = coll.find(searchQuery);

		if (cursor.hasNext()) {
			DBObject obj = cursor.next();
			returnVal = obj.get("ref_id").toString();

			BasicDBObject searchQuery2 = new BasicDBObject();
			searchQuery2.put("ref_id", returnVal);

			DBCollection coll2 = db.getCollection("Equipment");
			DBCursor cursor2 = coll2.find(searchQuery2);
			DBObject obj2 = cursor2.next();
			returnVal+=",";
			returnVal+=obj2.get("name").toString();
			returnVal+=",";
			returnVal+=obj2.get("condition").toString();
		}
		mongoClient.close();
		return returnVal;
	}
	public static void main(String[] args) {
		System.out.println(getIssue("19I-0441"));
	}
}

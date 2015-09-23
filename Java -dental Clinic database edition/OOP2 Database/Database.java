
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Database  {
	//variables used to create a connection to the database
	static String host = "jdbc:mysql://localhost/DentalClinic";
	static String username = "root";
	static String password = "";
	
// get the driver for mysql and if it cant find the driver, it will run classnotfoundexception
public static void connection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

//deletes a specific value from the database it selects name, address and the procedurde id associated
	public static void deleteFromDatabase(int pat_id, String name,String address,int proc_id){
		//call the connection method
          connection();
		
		
		try {
			//get the connection
			Connection connect = DriverManager.getConnection(host,username,password);
			//create statement 
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement(
			"DELETE FROM patients WHERE pat_id = ? AND patientname = ? AND patientaddress=? AND proc_id = ? ");
			//this  just tells us weither or not the statement was successful or not
			System.out.println("deleted patient sucessful");
		
			//set values
			statement.setInt(1,pat_id);
			statement.setString(2,name);
			statement.setString(3,address);
			statement.setInt(4,proc_id);
			//execute statement and close the connection
			statement.executeUpdate();
			statement.close();
			connect.close();

		} catch (SQLException e) {
			//catch the exception print error message
			e.printStackTrace();
			System.out.println("There was an error deleting data from the database");
		}
		

	}
	
	//  inserts a patient in the database 
	public static void MysqlInsertPatient(int pat_id, String name, String address, int proc_id){
		//call connection method
		connection();
		
		
		try {
			//get the connection
			Connection connect = DriverManager.getConnection(host,username,password);
			//create the statement
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement(
			"INSERT INTO patients(pat_id,patientname,patientaddress,proc_id)VALUES(?,?,?,?)");
			//feed back to see if data was added
			System.out.println("Data added to patients");
			
			//set values
			statement.setInt(1,pat_id);
			statement.setString(2,name);
			statement.setString(3,address);
			statement.setInt(4,proc_id);
			//execute statement
			statement.executeUpdate();
			//close statement and connection to database
			statement.close();
			connect.close();
			

		} catch (SQLException e) {
			//catch the exception, print error message
			e.printStackTrace();
			
			System.out.println("There was an error adding data to the database");
		}
	}
	
	//list the patients from the database
public static String  listPatientsFromDatabase(int pat_id, String name, String address, int proc_id){
	//call connection method
	connection();
	
	
	try {
		//connect to database 
		Connection connect = DriverManager.getConnection(host,username,password);
		//create statement 
		PreparedStatement statement = (PreparedStatement) connect.prepareStatement(
				"SELECT * FROM patients ORDER BY pat_id");
		//feed back
		System.out.println("Patients Listed");
		//execute the query and get the result set
		ResultSet list = statement.executeQuery();
		while (list.next()){
		   list.getInt(pat_id);
		   list.getString(name);
	      list.getString(address);
	      list.getInt(proc_id);
		}
		statement.executeUpdate();
		statement.close();
		connect.close();
		
	//catch the exception	
	} catch (SQLException e) {
		e.printStackTrace();
		
		System.out.print("there was an error adding of patient");
	}
	//add it to a string and return that string. This will list in the text area in the GUI2 class
	String x = "Patient_Id: "+pat_id+"\n "+"Patient Name "+ name+"\n "+"Patient-Address: " +address+" \n"+"Procedure-IdL: "+proc_id;
	return x;
}
//list procedures and load to dropdown menu
public String[] listProceduresfromDatabase(int proc_id, String procname, int cost){
	//get the connection
	connection();
	
	
	try {
		//connect to databasse
		Connection connect = DriverManager.getConnection(host,username,password);
		//create statement
		PreparedStatement statement = (PreparedStatement) connect.prepareStatement(
				"SELECT * FROM proceudres ORDER BY proc_id");
		//feedback from the program
		System.out.println("Listing Procedures .....");
		//get the resultset and execute query
		ResultSet list = statement.executeQuery();
		//get the values
		while (list.next()){
		   list.getInt(proc_id);
		   list.getString(procname);
	      list.getInt(cost);
	      
		}
		//execute the statment, close the connection
		statement.executeUpdate();
		statement.close();
		connect.close();
		
		
	} catch (SQLException e) {
		//catch the exception
		e.printStackTrace();
		//print messsage for feedback
		System.out.println(" error listing patietnts ");
	
	}
	//create a string array with 5 values(table size)
	String y[] = new String[5];
	//assign all values to variable and return that variable
	String x = proc_id+procname+cost;
	for (int i =0;i<y.length;i++)
	{
		y[i]=x;
	}
	return y;
	
}
//list ALL payments in the database
public static String  listPaymentsFromDatabase(int pat_id , String date, String payment){
	//call connection method
	connection();
	
	
	try {
		//connect to database 
		Connection connect = DriverManager.getConnection(host,username,password);
		//create statement 
		PreparedStatement statement = (PreparedStatement) connect.prepareStatement(
				"SELECT * FROM payments ORDER BY pat_id");
		//feed back
		System.out.println("Patients Listed");
		//execute the query and get the result set
		ResultSet list = statement.executeQuery();
		while (list.next()){
		   list.getInt(pat_id);
	      list.getString(payment);
	      list.getString(date);
	   
		}
		statement.executeUpdate();
		statement.close();
		connect.close();
		
	//catch the exception	
	} catch (SQLException e) {
		e.printStackTrace();
		
		System.out.print("there was an error getting the list of patients");
	}
	//add it to a string and return that string. This will list in the text area in the GUI2 class
	String x ="Patient_Id: "+ pat_id+"\n "+"Date of Payment: "+date+"\n "+"Payment Amount:"+payment;
	return x;
}
//genterate report based on patients and their payments
public void generateReport(int pat_id,String patientname, String address, int proc_id, String paymentname,String date, String payment){
	//call connection method
	connection();
	
	
	try {
		//connect to database 
		Connection connect = DriverManager.getConnection(host,username,password);
		//create statement 
		PreparedStatement statement0 = (PreparedStatement) connect.prepareStatement(
				"SELECT * FROM patients ORDER BY pat_id");
		PreparedStatement statement1 = (PreparedStatement) connect.prepareStatement(
				"SELECT * FROM payments ORDER BY pat_id");
		
		ResultSet list = statement0.executeQuery();
		ResultSet list2 = statement1.executeQuery();
		while ((list.next())&&(list2.next())){
		   list.getInt(pat_id);
		   list.getString(patientname);
	      list.getString(address);
	      list.getInt(proc_id);
	     
		   list2.getInt(pat_id);
		    list2.getString(payment);
		      list2.getString(date);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		
		System.out.print("there was an error getting the list of all the data required");
	}	
		
		
}
 
}
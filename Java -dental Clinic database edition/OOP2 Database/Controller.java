import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.*;

import java.awt.*;
import java.io.*;

public class Controller  {
	
	private static ArrayList Patients,Procedures,Price;
	private static Scanner s;
	static String procost;
	private static Formatter write;
	public  static Database myDB;

	public Controller () throws FileNotFoundException
	{
		Patients = new ArrayList();
		Procedures = new ArrayList();
		Price = new ArrayList();
		openProcFile();
		Database myDB = new Database();
		
	}
		
	public static Patient doAddPatient() {
		
		/*Add a patient to the list. Display the details - note displays details in a message box for the meantime
		*so that we know that the action listener is responding and taking in user input. 
		*havent got any further, this the general bones of the Dental Clinic
		*/
		openMainFile();
		Patient TemporaryObject = new Patient(Patient.getPatientName(),Patient.getNewPatientAdd());
		myDB.MysqlInsertPatient(1, "LOL", "LOL",22);
		Patients.add(TemporaryObject);
		
		//System.out.printf("%s\n%s",TemporaryObject);
		//write.format("%s\n%s",TemporaryObject);
		
			
		return TemporaryObject;
		
		
	}	

	/*method removes a patient*/
	public void doRemovePatient(String deleteName)
	{
	
/*	Patient reference;
		for (int i =0;i<Patients.size();i++)
		{
			reference = (Patient)Patients.get(i);
			
			if (reference.equals(deleteName)){
			Patients.remove(i);
			
			
			}
			
		}*/
	myDB.deleteFromDatabase(1, "LOL", "LOL", 22);
	}
	public String listAllPatients()
	{
		//This button lists all patients in the patient list. It will display P1's information on screen
		//note this is an attempt at READING files for patient object.
	
		//openMainFile();
		String PatientsList = myDB.listPatientsFromDatabase(1,"john","cork", 3);
		//Patient j = new Patient("John Smith","Cork");
		//Patients.add(j);
		/*String line= null;
		while (s.hasNext())
		{
			
			//read all lines in file
			line = s.nextLine();
			//add it to arrayList
			Patients.add(line);
			}
	
		String patientList =j.toString()+"\n";
			for (int i=0;i<Patients.size();i++)
			{
				// get the patients from list
				patientList = patientList+"%s\n%s\n%s"+ Patients.get(i).toString()+"\n";
				
				
			}
*/
		return PatientsList;
	
		
			
	}
	
	public String[] listProcedures()
	{
		//list procedures form comboxbox 
			String ProcList = Patient.listProcedure();
			
			while (s.hasNext())
			{
				//read all lines in file
				String a = s.nextLine();
				//split the line after the equals sign
				//assign to an array
				String[] dropdown = a.split("=");
				String procline = dropdown[0];
				
				//test print
				System.out.println(dropdown[0]);
				System.out.println(dropdown[1]);
				
				//add it to arrayList
				Procedures.add(procline);
				
			
			}
			String[] lineArray = new String [10];
			
				for (int i = 0 ; i<Procedures.size();i++)
				{
				//Print it on screen
				System.out.println(Procedures.get(i));
				//print in GUI
				ProcList =  Procedures.get(i)+"\n";
				lineArray[i]=ProcList;
				Patients.add(lineArray);
				}
		
			
			
			return lineArray;
	}
	
	public String[] returnCosttoCombo(){
		//after selecting procedure form combobox, the price should display in a textara after item is selected. 
		
		String ProcList = Patient.listProcedure();
	
		String[] costArray = new String[10];
		String [] dropdown =new String[2];
		while (s.hasNext())
		{
			
			//read all lines in file
			String a = s.nextLine();
			//split the line after the equals sign
			//assign to an array
			dropdown = a.split("=");
			String proname = dropdown[0];
			procost = dropdown[1];
		
		
			//add it to arrayList
			Price.add(procost);
			}
			for (int i = 0 ; i<Price.size();i++)
			{
				
			ProcList = Price.get(i) +"\n";
			costArray[i]=ProcList;
			
			Patients.add(costArray[i]);
			}
	
		
		
		return costArray;
	}
	public void doAddProcedure(String Pn, String cost )
	{
		
		
		
		for (int i = 0; i<Patients.size();i++)
		{
			if (Pn .equals(Patients.get(i))){
				
				
				double cost1 = Double.parseDouble(cost);
		
				Procedure TemporaryObject = new Procedure(Pn,cost1 );
				Patients.add(TemporaryObject);
				
			
				
			
				
			}
		}
	
		
	}
	public static String ListPayments()
	{
		String Payment = null;
		for (int i = 0;i< Patients.size(); i++)
		{
			
			
			Payment = Database.listPaymentsFromDatabase( 1,"25/4/2013","20.00");
			System.out.println(Payment);
		}
		return Payment;
		
	}
	


	
public void openProcFile(){
		
	try {
		s = new Scanner(new File("C:\\Users\\jade\\Google Drive\\College\\semester4\\oop2\\labwork\\src\\Database\\procedurelist.txt"));
		
		System.out.println("Boom Created!");
			
		}
		catch (Exception e){
			System.out.println("ugh ohh error!");
		}
}	
	


public void closeFile(){
	s.close();
}
public static void openMainFile(){
	
	try {
		s = new Scanner(new File("C:\\Users\\jade\\Google Drive\\College\\semester4\\oop2\\labwork\\src\\Database\\mainfile.txt"));
		
		System.out.println("This file has been Created!");
			
		}
		catch (Exception e){
			System.out.println("ugh ohh error!Cant find requested File or path maybe incorrect!!");
		}
}
}

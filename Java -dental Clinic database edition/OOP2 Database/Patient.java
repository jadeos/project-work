
/*This class stores information about a patient, the procedures and the payments 
 * That are associated with that patient
 * @Authors Jade O'Sullivan
 */


import java.io.File;
import java.util.Formatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Patient {

	private int payment;
	private static String patientName;
	private static String patientAdd;
	private static String patientPhone;
	private static ArrayList <Payment > p_paymentList = new ArrayList<Payment>();
	private static ArrayList <Procedure >p_procList  = new ArrayList<Procedure>();
	private static Scanner s;

	
	
	public Patient (String pn,String pa)
	{
		patientName=pn;
		patientAdd=pa;
		
		
	System.out.println();
		
	}
	//create void methods with no return type
	public void addPayment (Payment p)
	{
		
		p_paymentList.add(p);
	}
	
	public void addPatientName(String pn)
	{
		patientName=pn;
	}
	public void addPatient (String pa)
	{
		patientAdd=pa;
		
	}
	public void addPatientPhone(String pp)
	{
		patientPhone=pp;
	}
	// create return types.
	public int getPayment()
	{
		return payment;
	}
	public static String getPatientName()
	{
		return patientName;
	}
	public static String getNewPatientAdd()
	{
		return patientAdd;
	}
	public static String getPatientPhone()
	{
		return patientPhone;
		
	}
	
	//list procedures
	public static String listProcedure( )
	{
		Procedure john = new Procedure("Root Canal",200);
		p_procList.add(john);
		
	
		String procList=null;
		for(int i=0; i<=p_procList.size();i++)
		{
			procList = procList +p_procList;
		}
		return procList;
	}
	
	public static String listPayment()
	{
		Payment One = new Payment(300);
		p_paymentList.add(One);
		
		String pay = null;
		for (int i=0;i<p_paymentList.size();i++)
		{
			pay +=  p_paymentList.get(i);	
			System.out.println(One+pay+"\n");
		
		}
		return pay;
	}
	public static String addProcedure()
	{
		Procedure A= new Procedure(Procedure.getProcedure(),Procedure.getCost());
		p_procList.add(A);
		String newProcedure = null;
		for (int i = 0;i<p_procList.size();i++)

		{
			newProcedure = newProcedure+ Procedure.getProcedure() +Procedure.getCost();
	}
		 return newProcedure;
	}
	


	//return all the details as a string
	public  String toString()
	{
		String details=patientName+" " + patientAdd+" " + patientPhone +""+p_procList ;
		return details;
	}
	
	//print out the string
	public void print()
	{
		System.out.println(toString());

	}

}
 






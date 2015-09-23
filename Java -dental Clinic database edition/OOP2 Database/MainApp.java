/*the main class controls all the data. it prints from the patient class. 
 * The main class is only associated with the Patients, it does not have anything to do with the procedures or payemnt
 * methods. 
 * @Authors Jade O'Sullivan
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainApp extends JFrame implements ActionListener   {
	public static void main(String [] args) throws FileNotFoundException {
		
		//newF.openFile();
		//newF.readInfo();
		//newF.addInfo();
		//newF.closeFile();
		
	//	Database myDB =
		Gui2 myGui = new Gui2();
		//create an Array list for the list of patients.
		//ArrayList <Patient > Patient_List = new ArrayList<Patient>();
		//create a patient using the constructors
		//Patient p1 = new Patient("John Smith","1234567");
		//add patient to patient list
		//Patient_List.add(p1);
		
		//Implement scanner as it will be used from this point on
		Scanner keyboard=new Scanner(System.in);
		//Create a menu for user 
		
		System.out.println("Please type a number");
		System.out.println ("MENU");
		System.out.println("-------");
		System.out.println("1.Add a patient");
		System.out.println("2.Display a list of  all Patients");
		System.out.println("3.Remove a Patient");
		System.out.println("4.Add a procedure");
		System.out.println("5.List all  procedure");
		System.out.println("6.Delete a procedure");
		System.out.println("7.Quit without saving");
		//wait for user input
		int x=keyboard.nextInt();
		
		
		
		switch (x)
		{
		case 1: 
		
			//add a patient to the list
			System.out.println("Please enter the patients name");
			String name=keyboard.next();
		
		
			
			System.out.println("Please enter patients phone");
			String phone=keyboard.nextLine();
			keyboard.nextLine();
			
			System.out.println("Please enter the Patients payment amount");
			int amount=keyboard.nextInt();
			
			//This is the new patient that has been added based on scanner input
			Patient newPatient = new Patient(name,phone);
				System.out.println(newPatient);
				//Patient_List.add(newPatient);	
				
				//prints contents of everything inside 
				//System.out.println(Patient_List);
			break;
		case 2: 
			//display a list of patients
			//System.out.println(Patient_List);
			
			
			break;
		case 3: 
			//remove a patient from the list
			System.out.println("What List would you like to remove-Please specify a number?");
			//System.out.println(Patient_List);
			//int a = keyboard.nextInt();
			//for(int i=0; i<=Patient_List.size();i++)
			{
			//	Patient_List.remove(a-1);
			}
			//System.out.println(a+" has been removed.");
			//System.out.println(Patient_List);
			break;
		case 4:
			//Add a procedure
			int  i=0;
			System.out.println("ADD A PROCEDURE");
			System.out.println("Please enter the name of the patient");
			String patName=keyboard.nextLine();
			keyboard.next();
			System.out.println("ADD "+patName);
				//for (i =0;i<Patient_List.size();i++)
				{
					//System.out.println(Patient_List.get(i));
					//if (Patient_List.get(i).getPatientName().equals(patName))
					{
						
						//doesnt print code listed below
						System.out.println("What is the name of the procedure?");
						String procName= keyboard.next();
						keyboard.nextLine();
						
						System.out.println("What is the cost of the procedure");
						double procCost=keyboard.nextDouble();
						keyboard.nextLine();
			
						Procedure newProcedure =new Procedure (procName,procCost);
						System.out.println(newProcedure);
						//Patient_List.get(i).addProcedure(newProcedure);
				
					}
				}
			break;
		case 5:
			//List all procedures
			//p1.listProcedure();
			break;
		case 6: 
			//delete a procedure
			int y;
			System.out.println("Please type Patient name");
			String PName=keyboard.next();
			
		//	for ( y=0;y<Patient_List.size();y++)
			{
				//read in user input
				System.out.println("What is the name of the procedure?");
				String pname =keyboard.nextLine();
				keyboard.nextLine();
				
				System.out.println("How much did that procedure cost?");
				double price = keyboard.nextDouble();
			
				//Assign it to object
				Procedure remove = new Procedure(pname,price);
				System.out.println(remove);
				
		
				//check if the input exists in system
			//	if (PName.equals(Patient_List.get(y)))
				{
					
			//		Patient_List.get(y).removeProcedure(remove);
				}
				
			//	System.out.println(Patient_List.get(y));
				}
				
			//	System.out.println(y+" has been removed");
			//	System.out.println(Patient_List);
					break;


		case 7:
			//exit system
			System.out.println("Okay thank you");
			System.exit(0);
		}
		keyboard.close();
		}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}



/* Adding & Patient not functioning properly. 
 * No Payment method or details there 
 * no date
 */


		
	} 



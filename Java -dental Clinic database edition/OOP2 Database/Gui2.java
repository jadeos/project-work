
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java. awt.*;
import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

public class Gui2 extends JFrame implements ActionListener,ItemListener,ListSelectionListener {
	private static  Controller myController;
	private String[] costList;
	private static JPanel tab1,tab4;
	private static JLabel tab1NamePrompt,tab2NamePrompt,PatA,PatCost;
	private static JComboBox ProcName1;
	private static JTextArea ProcName,PatName,PatAdd,patients,procedures,ProcCost, listPay,reportList;
	private static JButton 	listpatients,AddPatient,DeletePatient,listprocedures,AddProcedure,DeleteProcedure,report;
	@SuppressWarnings("unchecked")
	private static Scanner s;
	private static Formatter write;
	private JTextArea textArea;
	
	public Gui2() throws FileNotFoundException  
	{
		myController = new Controller();
		
		JFrame frame = new JFrame ("Dental Clinic");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setSize(new Dimension(566, 483));		
		frame.setLocation(500,100);
	
		Container contentpane = frame.getContentPane();
		
		//Create a tabbed layout. call it tabbed.This is a design option.
				JTabbedPane tabbed = new JTabbedPane();

				//create each individual tab
				tab1 = new JPanel();
				tab4 = new JPanel();
				//Tab one layout
				tab1.setLayout(null);
				
				//Display Patient Name at top of Gui in patients and procedures tabs.
				
				tab1NamePrompt = new JLabel("Patient Name : ");
				tab1NamePrompt.setBounds(14, 9, 94, 14);
				tab1.add(tab1NamePrompt);
				PatName = new JTextArea(1,10);
				PatName.setBounds(107, 4, 130, 22);
				tab1 .add(PatName);
				
				//create an array to list the procedures in the dropdown box and the Proceudre cost associated
				String[]  procedureList = new String[20];
				procedureList= myController.listProcedures();
				
				ProcCost = new JTextArea(1,10);
				ProcCost.setBounds(379, 45, 130, 22);
			 tab1.add(ProcCost);
			 //Patient Management 
				listpatients = new JButton("List Patients");
				listpatients.setBounds(17, 80, 123, 23);
				listpatients.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{ 
						ListPatients();
					}});
				
				
				tab1.add(listpatients);
				
				AddPatient  = new JButton("Add a Patient");
				AddPatient.setBounds(207, 80, 123, 23);
				AddPatient.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event1){
						//adding patients 
						AddPatient();
				
					}});
				tab1.add(AddPatient);
				
				DeletePatient = new JButton("Remove a Patient");
				DeletePatient.setBounds(368, 78, 141, 23);
				DeletePatient.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event1){
					DeletePatient();
				}});
				
								//Add the address label
								PatA = new JLabel("Patient Address: ");
								PatA.setBounds(261, 9, 108, 14);
								tab1.add(PatA);
				ProcName1 = new JComboBox(procedureList);
				ProcName1.setBounds(127, 47, 99, 20);
				tab1 .add(ProcName1);
				ProcName1.addActionListener( new ActionListener(){ 
						//combobox action listener	
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								
							System.out.println(myController.returnCosttoCombo());
							
						costList = myController.returnCosttoCombo();
						 ProcName1 = (JComboBox)e.getSource();
					        String costAssoc = (String)ProcName1.getSelectedItem();
					        //test to see if selected item has actually been selected
					        System.out.print(costAssoc);
					  
					        openProcFile();
								         
					        while (s.hasNext())
								       		{
								       		
								       			//read all lines in file
								       			String a = s.nextLine();
								       			//split the line after the equals sign
								       			//assign to an array
								       			String [] dropdown = a.split("=");
								       			String proname = dropdown[0];
								       			String procost = dropdown[1];
								       			
								       			
								       			//loop through the array
								       		  for ( int i = 0; i< costList.length; i++){
								       				  //set the value of cost list to the i'th element
								       			 
							            	costList[i]= procost;
							            	//set the text in the textbox
							            	ProcCost.setText(costList[i]);
							            
								       			  }}  
								          
			} });
				
				//Patient Name and Address labels are placed at the top of gui on both tabs
				tab2NamePrompt= new JLabel("Procedure Name: ");
				tab2NamePrompt.setBounds(14, 50, 103, 14);
				tab1.add(tab2NamePrompt);
				PatAdd = new JTextArea(1,10);
				PatAdd.setBounds(379, 4, 133, 23);
				tab1 .add(PatAdd);
				PatCost= new JLabel("Procedure Cost: ");
				PatCost.setBounds(286, 50, 81, 14);
				tab1 .add(PatCost);
				tab1.add(DeletePatient);
				
			
				
		
				
				JButton ListPayments = new JButton ("List payments");
				ListPayments.setBounds(37, 233, 148, 23);
			
				ListPayments.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event2){
						ListPay();
					}});
				tab1.add(ListPayments);

				
				//assign each tab a value
				tabbed.addTab("Patient",tab1);
				
				patients = new JTextArea();
				patients.setBounds(37, 120, 448, 91);
				tab1.add(patients);
				
				listPay = new JTextArea();
				listPay.setBounds(37, 267, 448, 80);
				tab1.add(listPay);
				
				JButton btnSave = new JButton("Save ");
				btnSave.setBounds(396, 368, 89, 23);
				tab1.add(btnSave);
				
				JButton btnSaveAndQuit = new JButton("Save and Quit");
				btnSaveAndQuit.setBounds(231, 368, 141, 23);
				tab1.add(btnSaveAndQuit);
				report = new JButton("Generate Report");
				report.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event2){
						printReport();
					}});
				tab4.add(report);
				reportList = new JTextArea();
				reportList.setBounds(370, 120, 448, 910);
				tab4.add(reportList);
				
				//reports section to be added later
				tabbed.addTab("Report",tab4);
				
				//add it to the content pane
				contentpane.add(tabbed);
				//show the frame on screen
				frame.setVisible(true);
						
	}
	//list all patients
	public static void ListPatients(){
		
		JLabel listing1= new JLabel();
		listing1.setText("Below is a list of patients");
		tab1.add(listing1);
		//add the list of patients to the text area
		String [] patlist = new String [10];
		for(int i = 0;i<patlist.length;i++)
		{
		String list = myController.listAllPatients();
		patients.setText(list+"\n");
		}
		tab1.add(patients);
		tab1.setVisible(true);
	}
	//add a patient and list them in the textarea 
	public static void AddPatient(){
	
		JLabel addPat = new JLabel ("Add Patient");
		addPat.setLayout(new FlowLayout());
		Patient pat = new Patient(PatName.getText(),PatAdd.getText());
		JTextArea patientInfo = new JTextArea ();
		
		patientInfo.setText(PatName.getText()+PatAdd.getText());
		String x  = myController.doAddPatient().toString();
	
		//set text, set size
		JTextArea list = new JTextArea(myController.listAllPatients()+x);
		tab1.add(list);
		tab1.add(patientInfo);
	
	}
	//remove the patient from the database
	public void DeletePatient(){
		String deleteName;
		JLabel deletePat = new JLabel("Delete a patient");
		deleteName = PatName.getText();
		JTextArea patientInfo = new JTextArea ();
		patientInfo.setText(PatName.getText()+PatAdd.getText());
		myController.doRemovePatient(deleteName);
		JTextArea list = new JTextArea();
		list.setText(PatName.getText() +"has been removed.");
	
		tab1.add(patientInfo);
		tab1.add(list);
	}
	
	
		//list the payments in the text area
		public void ListPay(){
			JLabel addPay = new JLabel ("List of Payments:");
			
			listPay.setText (myController.ListPayments()+"\n");
			tab1.add(addPay);
			tab1.add(listPay);
			
		}
		//print the report
		public void printReport(){
			JLabel report = new JLabel ("Report as of date:");
			
			//listPay.setText (myController.()+"\n");
			tab4.add(report);
			tab4.add(reportList);
			
		}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//open thre procedure file
	public void openProcFile(){
		
		try {
			s = new Scanner(new File("C:\\Users\\jade\\Google Drive\\College\\semester4\\oop2\\labwork\\src\\Database\\procedurelist.txt"));
			
			System.out.println("Boom Created!");
				
			}
			catch (Exception e){
				System.out.println("ugh ohh error!");
			}
	}	
		

//close the file
	public void closeFile(){
		s.close();
	}	
}
	



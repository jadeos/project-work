/*here we're looking at the payment method and the total cost of that payment
 * @authors Jade O'Sullivan
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {

	
	//create variables
	private int payment;
	private double paymentAmt;
	private Date paymentDate; 
	private boolean isPaid;
	
	
	//create constructor method
	public Payment (int d)
	{
		payment=d; 
	
		
	}
	//create date-note this is something i looked up from oracle, it may needed to be changed later
	public Date paymentDate(int m, int d, int y ) throws ParseException
	{
		SimpleDateFormat DateObj = new SimpleDateFormat("dd/mm/yy");
		Date paymentDate   = DateObj.parse("21/04/2015");
		return paymentDate;
	
	}
	
	//create void methods with no return type
	public void addPayment(int d)
	{
		payment=d;
	}
	
	public void addAmount(int c)
	{
		paymentAmt=c;
		
	}
	
	// create return types
	public int getSinglePayment()
	{
		return payment;
	}
	public double getTotalAmount()
	{
		return paymentAmt;
		
	}
	
	//return the details as a string
	public String toString()
	{
		String info ="Payment date:"+paymentDate+ "Total: €"+paymentAmt+"  \n";
		return info;
	}
	
	//print out the string 
	public void print()
	{
	System.out.println(toString());
	}
}






/*in this class we're storing information about the type for procedure and the cost of that procedure
 * @authors Jade O'Sullivan
 */


public class Procedure {
	
	
		//create variables
		private int procedure;
		private static String procName;
		private static double procCost;
		
		
		//create constructor method for the procedure
		public Procedure(String p, double c)
		{
			procName = p;
			procCost=c;
			
		}
		
		//create void methods with no return type
		public void addProcedure (String p)
		{
			procName=p;
			
		}
		public void addCost(int c)
		{
			procCost=c;
		}
		
		// create return types.
		public static String getProcedure()
		{
			return procName;
		}
		public static double getCost()
		{
			return procCost;
		}
		
	
		//return all the details as a string
		public String toString()
		{
			String details= procName +": €"+procCost;
			return details;
		}
		
		//print out the string
		public void print()
		{
			System.out.println(toString());

		}
	

}


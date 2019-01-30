/* This is an authorship statement of this project:
 
   The University of Melbourne
   Master of Information Systems
   COMP90041 Programming and Software Development
   Name: Yinghao Zhang
   Student ID: 887152
   
   InvalidMoveException is one of exceptions that
   should be dealt with in Project C.
 */

public class InvalidMoveException extends Exception
{
	private int removeNum;
	
	/*This constructor is used to initialize all instance variables.
	  when there is no input.
	 */
	public InvalidMoveException()
	{
		super("InvalidMoveException");
	}
	//End of constructor
	
	//This constructor is used to initialize one instance variables.
	public InvalidMoveException(String message)
	{
		super(message);
	}
	//End of constructor
	
	//This constructor is used to initialize one instance variables.
	public InvalidMoveException(int removeNum)
	{
		super("InvalidMoveException");
		this.removeNum = removeNum;
	}
	//End of constructor
	
	//This method is used to get the number of stones needed to be removed.
	public int getRemoveNum()
	{
		return removeNum;
	}
	//End of method
}
//End of class
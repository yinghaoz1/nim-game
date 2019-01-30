/* This is an authorship statement of this project:
 
   The University of Melbourne
   Master of Information Systems
   COMP90041 Programming and Software Development
   Name: Yinghao Zhang
   Student ID: 887152
   
   InvalidNumberOfArgumentsException is one of exceptions that
   should be dealt with in Project C.
 */

public class InvalidNumberOfArgumentsException extends Exception
{
	/*This constructor is used to initialize all instance variables.
	  when there is no input.
	 */
	public InvalidNumberOfArgumentsException()
	{
		super("Incorrect number of arguments supplied to command.");
	}
	//End of constructor
	
	//This constructor is used to initialize one instance variables.
	public InvalidNumberOfArgumentsException(String message)
	{
		super(message);
	}
	//End of constructor
}
//End of class
/* This is an authorship statement of this project:
 
   The University of Melbourne
   Master of Information Systems
   COMP90041 Programming and Software Development
   Name: Yinghao Zhang
   Student ID: 887152
   
   InvalidCommandException is one of exceptions that
   should be dealt with in Project C.
 */

public class InvalidCommandException extends Exception
{
	private String command;
	
	/*This constructor is used to initialize all instance variables.
	  when there is no input.
	 */
	public InvalidCommandException()
	{
		super("InvalidCommandException");
	}
	//End of constructor
	
	//This constructor is used to initialize one instance variables.
	public InvalidCommandException(String command)
	{
		super("InvalidCommandException");
		this.command = command;
	}
	//End of constructor
	
	//This method is used to get the command.
	public String getCommand()
	{
		return command;
	}
	//End of method
}
//End of class
import java.util.Scanner;

public class NimHumanPlayer extends NimPlayer
{
	private int removeNum;
	
	/*This constructor is used to initialize all instance variables 
	  when there is no input.
	 */
	public NimHumanPlayer()
	{
		super();
		removeNum = 0;
	}
	//End of constructor
	
	//This constructor is used to initialize three instance variables.
	public NimHumanPlayer(String username, String familyName, 
			String givenName)
	{
		super(username, familyName, givenName);
		removeNum = 0;
	}
	//End of constructor
	
	/*This method is used to input the number of stones needed 
	  to be removed by human players.
	 */
	public void removeStone(int stoneNum, int removeLimit, 
			Scanner keyboard)
	{
		removeNum = keyboard.nextInt();
	}
	//End of method
	
	/*This abstract method is used to get the number of stones needed 
	  to be removed.
	 */
	public int getRemoveNum()
	{
		return removeNum;
	}
	//End of method
}
//End of class
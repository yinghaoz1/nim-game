/* This is an authorship statement of this project:
 
   The University of Melbourne
   Master of Information Systems
   COMP90041 Programming and Software Development
   Name: Yinghao Zhang
   Student ID: 887152
   
   NimAIPlayer is an descendant class of NimPlayer,
   recording the relevant information of each AI player.  
 */

import java.util.Scanner;

public class NimAIPlayer extends NimPlayer implements Testable 
{
	private int removeNum;

	/*This constructor is used to initialize all instance variables 
	  when there is no input.
	 */
	public NimAIPlayer()
	{
		super();
		removeNum = 0;
	}
	//End of constructor
	
	//This constructor is used to initialize three instance variables.
	public NimAIPlayer(String username, String familyName, 
			String givenName)
	{
		super(username, familyName, givenName);
		removeNum = 0;
	}
	//End of constructor
	
	/*This method is used to decide the number of stones needed 
	  to be removed by AI players.
	 */
	public void removeStone(int stoneNum, int removeLimit, Scanner keyboard)
	{
		int k;
		boolean kExists = false;
		
		for (k = 0; k * (removeLimit + 1) + 1 <= stoneNum; k++)
		{
			if (k * (removeLimit + 1) + 1 == stoneNum)
			{
				removeNum = 1 + (int)(Math.random() * Math.min(removeLimit, stoneNum));
				kExists = true;
				break;
			}
		}
			
		if (kExists == false)
		{
			removeNum = stoneNum - ((k - 1) * (removeLimit + 1) + 1);
		}
	}
	//End of method
	
	/*This method is used to get the number of stones needed 
	  to be removed.
	 */
	public int getRemoveNum()
	{
		return removeNum;
	}
	//End of method

	public String advancedMove(boolean[] available, String lastMove) 
	{
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}
	//End of method
}
//End of class

/* This is an authorship statement of this project:
 
   The University of Melbourne
   Master of Information Systems
   COMP90041 Programming and Software Development
   Name: Yinghao Zhang
   Student ID: 887152
   
   NimGame is one of classes of project C,
   recording the relevant information of the game process.
 */

public class NimGame
{
	private int stoneNum;
	private int removeLimit;
	
	//This constructor is used to initialize two instance variables.
	public NimGame(int stoneNum, int removeLimit)
	{
		setStone(stoneNum, removeLimit);
	}
	//End of constructor
	
	//This method is used to set two instance variables.
	public void setStone(int stoneNum, int removeLimit)
	{
		this.stoneNum = stoneNum;
		this.removeLimit = removeLimit;
	}
	//End of method
	
	//This method is used to set the current stone count.
	public void setStoneNum(int stoneNum)
	{
		this.stoneNum = stoneNum;
	}
	//End of method
	
	//This method is used to remove the stone during each round.
	public void removeStone(int removeNum) 
	{
		stoneNum -= removeNum;
	}
	//End of method
	
	//This method is used to get the current stone count.
	public int getStoneNum()
	{
		return stoneNum;
	}
	//End of method
	
	//This method is used to get the upper bound on stone removal.
	public int getRemoveLimit()
	{
		return removeLimit;
	}
	//End of method
}	
//End of class
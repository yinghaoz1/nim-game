/* This is an authorship statement of this project:
 
   The University of Melbourne
   Master of Information Systems
   COMP90041 Programming and Software Development
   Name: Yinghao Zhang
   Student ID: 887152
   
   NimPlayer is one of classes of project C,
   recording the relevant information of each player.  
 */

import java.util.Scanner;
import java.io.Serializable;

public abstract class NimPlayer implements Serializable
{
	private String username;
	private String familyName;
	private String givenName;
	private int playedNum;
	private int wonNum;
	private double wonRatio;
	
	/*This constructor is used to initialize all instance variables 
	  when there is no input.
	 */
	public NimPlayer()
	{
		username = null;
		givenName = null;
		familyName = null;
		playedNum = 0;
		wonNum = 0;
	}
	//End of constructor
	
	/*This constructor is used to initialize all instance variables 
	  when the username, given name, family name of players are given.
	 */
	public NimPlayer(String username, String familyName, String givenName)
	{
		setName(username, familyName, givenName);
		playedNum = 0;
		wonNum = 0;
	}
	//End of constructor
	
	/*This method is used to set the username, given name, 
	  family name of players.
	 */
	public void setName(String username, String familyName, String givenName)
	{
		this.username = username;
		this.familyName = familyName;
		this.givenName = givenName;
	}
	//End of method
	
	//This method is used to set all instance variables
	public void setPlayer(String username, String familyName, String givenName, 
			int playedNum, int wonNum)
	{
		this.username = username;
		this.familyName = familyName;
		this.givenName = givenName;
		this.playedNum = playedNum;
		this.wonNum = wonNum;
	}
	//End of method
	
	//This method is used to set the number of games won for each player.
	public void setWonNum(int wonNum)
	{
		this.wonNum = wonNum;
	}
	//End of method
	
	//This method is used to calculate the exact winning ratio for each player.
	public void calculateWonRatio()
	{
		if (playedNum != 0)
		{
			wonRatio = (double)(wonNum) * 100 / playedNum;
		}
		else
		{
			wonRatio = 0.0;
		}
	}
	//End of method
	
	//This method is used to increase the number of games played by 1.
	public void playedNumIncrement()
	{
		playedNum++;
	}
	//End of method
	
	//This method is used to increase the number of games won by 1.
	public void wonNumIncrement()
	{
		wonNum++;
	}
	//End of method
	
	//This method is used to reset the statistics for each player.
	public void resetstats()
	{
		playedNum = 0;
		wonNum = 0;
		wonRatio = 0.0;
	}
	//End of method
	
	//This method is used to display the information of each player.
	public void displayplayer()
	{
		System.out.println(username + "," + givenName + "," + familyName 
				+ "," + playedNum + " games," + wonNum + " wins");
	}
	//End of method
	
	//This method is used to get the username.
	public String getUsername()
	{
		return username;
	}
	//End of method
	
	//This method is used to get the given name.
	public String getGivenName()
	{
		return givenName;
	}
	//End of method
	
	//This method is used to get the family name.
	public String getFamilyName()
	{
		return familyName;
	}
	//End of method
	
	//This method is used to get the number of games played.
	public int getPlayedNum()
	{
		return playedNum;
	}
	//End of method
	
	//This method is used to get the number of games won.
	public int getWonNum()
	{
		return wonNum;
	}
	//End of method
	
	//This method is used to get the exact winning percentage.
	public double getWonRatio()
	{
		return wonRatio;
	}
	//End of method
	
	//This method is used to get the rounded winning percentage.
	public int getRoundedWonRatio()
	{
		return (int)Math.round(wonRatio);
	}
	//End of method
	
	//This method is used to get the full name.
	public String getName()
	{
		return givenName + " " + familyName;
	}
	//End of method
	
	/*This abstract method is used to decide how many stones 
	  will be removed according to different types of players.
	 */
	public abstract void removeStone(int stoneNum, int removeNum, 
			Scanner keyboard);
	//End of abstract method
	
	/*This abstract method is used to get the number of stones needed 
	  to be removed.
	 */
	public abstract int getRemoveNum();
	//End of abstract method
}
//End of class

		

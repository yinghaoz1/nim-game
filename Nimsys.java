/* This is an authorship statement of this project:
 
   The University of Melbourne
   Master of Information Systems
   COMP90041 Programming and Software Development
   Name: Yinghao Zhang
   Student ID: 887152
   
   Nimsys is the main part of project C,
   showing the function of the game system.
*/

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Nimsys
{
	//These are one static variable and one constant of this class.
	static int NumOfPlayers = 0;
	static final int MAX_NUMOFPLAYERS = 100;
	
	public static void main(String[] args) 
	{
	    //An object array is created viewing each player as an object.
        NimPlayer[] playerArray = new NimPlayer[MAX_NUMOFPLAYERS];		         
		Scanner keyboard = new Scanner(System.in);
		
		fileExists(playerArray);
		
	    System.out.println("Welcome to Nim");
	    System.out.println();
	    System.out.print("$");
		
	    while(true)
	    {			
	    		String[] dataArray = null;
	    	    String text = keyboard.nextLine();
	    		
	    		StringTokenizer textFactory = new StringTokenizer(text, " ");
	    		
	    		String command = textFactory.nextToken();
	    		
	    		if (textFactory.hasMoreTokens())
	    		{
	    			String data = textFactory.nextToken();
	    			dataArray = data.split(",");
	    		}
			
	        	identifyCommand(command, dataArray, playerArray, keyboard);
	    }
	}
	//End of main method
	
	//This method is used to check if the file has already existed.
	public static void fileExists(NimPlayer[] playerArray)
	{
		File fileObject = new File("players.dat");
		
		if(fileObject.exists())
		{
			try
			{
				ObjectInputStream inputStream = new ObjectInputStream
	        		 (new FileInputStream("players.dat"));
				
				NumOfPlayers = (int)inputStream.readInt();
        		 
		        for (int index = 0; index < NumOfPlayers; index++)
		        {
		        		playerArray[index] = 
		        				(NimPlayer)inputStream.readObject();
		        }
		         
		        inputStream.close();
			}
			catch(ClassNotFoundException e)
			{
				System.out.println("Problem with file input.");
			}
			catch(IOException e)
			{
				System.out.println("File not found.");
			}
		}
	}
	//End of method
	
	//This method is used to identify which command the user wants to take.
	public static void identifyCommand(String command, String[] dataArray, 
			NimPlayer[] playerArray, Scanner keyboard)
	{
		try 
		{
			if (command.equals("addplayer"))
			{
				if (dataArray.length < 3)
				{
					throw new InvalidNumberOfArgumentsException
					("Incorrect number of arguments supplied to command.");
				}
				addplayer(dataArray, playerArray);
			}
			else if (command.equals("addaiplayer"))
			{
				if (dataArray.length < 3)
				{
					throw new InvalidNumberOfArgumentsException
					("Incorrect number of arguments supplied to command.");
				}
				addaiplayer(dataArray, playerArray);
			}
			else if (command.equals("removeplayer"))
			{
				removeplayer(dataArray, playerArray, keyboard);
			}
			else if (command.equals("editplayer"))
			{
				if (dataArray.length < 3)
				{
					throw new InvalidNumberOfArgumentsException
					("Incorrect number of arguments supplied to command.");
				}
				editplayer(dataArray, playerArray);
			}
			else if (command.equals("resetstats"))
			{
				resetstats(dataArray, playerArray, keyboard);
			}
			else if (command.equals("displayplayer"))
			{
				displayplayer(dataArray, playerArray);
			}
			else if (command.equals("rankings"))
			{
				rankings(dataArray, playerArray);
			}
			else if (command.equals("startgame"))
			{
				if (dataArray.length < 4)
				{
					throw new InvalidNumberOfArgumentsException
					("Incorrect number of arguments supplied to command.");
				}
				startgame(dataArray, playerArray, keyboard);
			}
			else if (command.equals("exit"))
			{
				exit(playerArray);
			}
			else
			{
				throw new InvalidCommandException(command);
			}
		}
		catch(InvalidCommandException e)
		{
			System.out.println("'" + e.getCommand() + "' is not a valid command.");
		}
		catch(InvalidNumberOfArgumentsException e)
		{
			System.out.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println("Problem with file output.");
		}
		finally
		{
			System.out.println();
			System.out.print("$");
		}
	}
	//End of method
	
	//This method is used to check if a player has already existed.
	public static boolean playerExists(String username, NimPlayer[] playerArray)
	{
		for(int index = 0; index < NumOfPlayers; index++)
			if ((playerArray[index].getUsername()).equals(username))
			{
				return true;
			}
		return false;
	}
	//End of method
	
	//This method is used to return the index of each player in playerArray.
	public static int playerIndex(String username, NimPlayer[] playerArray)
	{
		for(int index = 0; index < NumOfPlayers; index++)
			if ((playerArray[index].getUsername()).equals(username))
				{
					return index;
				}		
		return 0;		
	}
	//End of method
	
	//This method is used to add the information of human players.
	public static void addplayer(String[] dataArray, NimPlayer[] playerArray)
	{
		new NimHumanPlayer(dataArray[0], dataArray[1], dataArray[2]);
		
		if(playerExists(dataArray[0], playerArray))
		{
			System.out.println("The player already exists.");
		}
		else
		{
			playerArray[NumOfPlayers] = new NimHumanPlayer(dataArray[0], dataArray[1], 
					dataArray[2]);			
			NumOfPlayers++;
		}
	}
	//End of method
	
	//This method is used to add the information of AI players.
	public static void addaiplayer(String[] dataArray, NimPlayer[] playerArray)
	{
		new NimAIPlayer(dataArray[0], dataArray[1], dataArray[2]);
		
		if(playerExists(dataArray[0], playerArray))
		{
			System.out.println("The player already exists.");
		}
		else
		{
			playerArray[NumOfPlayers] = new NimAIPlayer(dataArray[0], dataArray[1], 
					dataArray[2]);			
			NumOfPlayers++;
		}
	}
	//End of method
	
	//This method is used to remove the information of players.
	public static void removeplayer(String[] dataArray, NimPlayer[] playerArray, 
			Scanner keyboard)
	{
		if (dataArray == null)
		{
			System.out.println("Are you sure you want to remove "
					+ "all players? (y/n)");
			String removeAll = keyboard.nextLine();
			if (removeAll.equals("y"))
			{
				playerArray = null;
				NumOfPlayers = 0;
			}
		}
		else if (playerExists(dataArray[0], playerArray))
		{
			for(int index = 0; index < NumOfPlayers; index++)
				if ((playerArray[index].getUsername()).equals(dataArray[0]))
				{
					updatePlayer(index, playerArray);
					break;
				}
			NumOfPlayers--;
		}
		else
		{
			System.out.println("The player does not exist.");
		}
	}
	//End of method
	
	/*This method is used to update the information of players after some 
	  players are removed.
	 */
	public static void updatePlayer(int index, NimPlayer[] playerArray)
	{
		playerArray[index] = playerArray[NumOfPlayers - 1];
		playerArray[NumOfPlayers - 1] = null;
	}
	//End of method
	
	//This method is used to edit the information of players.
	public static void editplayer(String[] dataArray, NimPlayer[] playerArray) 
	{
		if (playerExists(dataArray[0], playerArray))
		{
			for(int index = 0; index < NumOfPlayers; index++)
				if ((playerArray[index].getUsername()).equals(dataArray[0]))
				{
					playerArray[index].setName(dataArray[0], dataArray[1], 
							dataArray[2]);
					break;
				}
		}
		else
		{
			System.out.println("The player does not exist.");
		}
	}
	//End of method
	
	//This method is used to reset the statistics of players.
	public static void resetstats(String[] dataArray, NimPlayer[] playerArray, 
			Scanner keyboard)
	{
		if (dataArray == null)
		{
			System.out.println("Are you sure you want to reset all player "
					+ "statistics? (y/n)");
			String resetAll = keyboard.nextLine();
			if (resetAll.equals("y"))
			{
				for(int index = 0; index < NumOfPlayers; index++)
					playerArray[index].resetstats();
			}
		}
		else if (playerExists(dataArray[0], playerArray))
		{
			for(int index = 0; index < NumOfPlayers; index++)
				if ((playerArray[index].getUsername()).equals(dataArray[0]))
				{
					playerArray[index].resetstats();
					break;
				}
		}
		else
		{
			System.out.println("The player does not exist.");
		}
	}
	//End of method
	
	//This method is used to display the information of players.
	public static void displayplayer(String[] dataArray, NimPlayer[] playerArray)
	{
		if (dataArray == null)
		{			
			sortUsername(playerArray);
			
		    for(int index = 0; index < NumOfPlayers; index++)
		    		playerArray[index].displayplayer();
			
		}
		else if (playerExists(dataArray[0], playerArray))
		{
			for(int index = 0; index < NumOfPlayers; index++)
				if ((playerArray[index].getUsername()).equals(dataArray[0]))
				{
					playerArray[index].displayplayer();
					break;
				}
		}
		else
		{
			System.out.println("The player does not exist.");
		}
	}
	//End of method
	
	//This part is used to sort the username of each player for display.
	//This method is the main part of username sorting.
	public static void sortUsername(NimPlayer[] playerArray)
    {
        int index, indexOfNext;
        
		for (index = 0; index < NumOfPlayers - 1; index++)
		{
			indexOfNext = indexOfSmallestUsername(index, playerArray);
			interchange(index,indexOfNext, playerArray);
		} 
    }
	//End of method
	
	//This method is used to return the current index of the "smallest" username.
	private static int indexOfSmallestUsername(int startIndex, 
			NimPlayer[] playerArray)
	{
		int indexOfMin = startIndex;
		int index;
		for (index = startIndex + 1; index < NumOfPlayers; index++)
	    {
	        	if (playerArray[indexOfMin].getUsername().compareToIgnoreCase
	        			(playerArray[index].getUsername()) > 0)
	        	{
	        		indexOfMin = index;
	        	}
	    }
	    return indexOfMin;
	}
	//End of method
	//End of username sorting
	
	//This method is used to rank the result of players.
	public static void rankings(String[] dataArray, NimPlayer[] playerArray)
	{
		sortPlayer(dataArray, playerArray);
			
		rankingResult(playerArray);
	}
	//End of method
	
	//This part is used to sort each player for ranking.
	//This method is the main part of player sorting.
	public static void sortPlayer(String[] dataArray, 
			NimPlayer[] playerArray)
    {
        int index, indexOfNext;
        
        if (dataArray == null || dataArray[0].equals("desc"))
		{
			for (index = 0; index < NumOfPlayers - 1; index++)
			{
				indexOfNext = indexOfBiggest(index, playerArray);
				interchange(index,indexOfNext, playerArray);
			}
			
		}
		else if (dataArray[0].equals("asc"))	
		{
    			for (index = 0; index < NumOfPlayers - 1; index++)
    			{
    				indexOfNext = indexOfSmallest(index, playerArray);
    				interchange(index,indexOfNext, playerArray);
    			}
		}  
    }
	//End of method

	/*This method is used to return the current index of 
	  the player who won the least.
	 */
    private static int indexOfSmallest(int startIndex, NimPlayer[] playerArray)
    {
        double min = playerArray[startIndex].getWonRatio();
        int indexOfMin = startIndex;
        int index;
        for (index = startIndex + 1; index < NumOfPlayers; index++)
        {
        		if (playerArray[index].getWonRatio() < min)
        		{
        			min = playerArray[index].getWonRatio();
        			indexOfMin = index;
        		}
        		else if (playerArray[index].getWonRatio() == min)
        		{
        			
        			if (playerArray[indexOfMin].getUsername().compareToIgnoreCase
        					(playerArray[index].getUsername()) > 0)
        			{
        				min = playerArray[index].getWonRatio();
        				indexOfMin = index;
        			}
        		}
        }
        return indexOfMin;
    }
    //End of method
    
    //This method is used to return the current index of the player who won the most.
    private static int indexOfBiggest(int startIndex, NimPlayer[] playerArray)
    {
        double max = playerArray[startIndex].getWonRatio();
        int indexOfMax = startIndex;
        int index;
        for (index = startIndex + 1; index < NumOfPlayers; index++)
            if (playerArray[index].getWonRatio() > max)
            {
                max = playerArray[index].getWonRatio();
                indexOfMax = index;
            }
            else if (playerArray[index].getWonRatio() == max)
    			{
    				if (playerArray[indexOfMax].getUsername().compareToIgnoreCase
    						(playerArray[index].getUsername()) > 0)
    				{
    					max = playerArray[index].getWonRatio();
    					indexOfMax = index;
    				}
    			}
        return indexOfMax;
    }
    //End of method
    
    //This method is used to interchange two players.
    private static void interchange(int i, int j, NimPlayer[] playerArray)
    {
        NimPlayer temp;
        temp = playerArray[i];
        playerArray[i] = playerArray[j];
        playerArray[j] = temp;
    }
    //End of method
    //End of player sorting
    
    //This method is used to display the result of rankings.
    public static void rankingResult(NimPlayer[] rankingArray)
    {
    		if (NumOfPlayers > 10)
    		{
    			for (int index = 0; index < 10; index++)
    				System.out.printf("%-5s| %02d games | %s%n", 
    					(rankingArray[index].getRoundedWonRatio()) + "%",
    					rankingArray[index].getPlayedNum(), 
    					rankingArray[index].getName());
    		}
    		else
    		{
    			for (int index = 0; index < NumOfPlayers; index++)
    				System.out.printf("%-5s| %02d games | %s%n", 
    					(rankingArray[index].getRoundedWonRatio()) + "%",
    					rankingArray[index].getPlayedNum(), 
    					rankingArray[index].getName());
    		}
    }
    //End of method
    
    //This method is used to start the game.
	public static void startgame(String[] dataArray, 
			NimPlayer[] playerArray, Scanner keyboard)
	{
		int initialStoneNum = Integer.parseInt(dataArray[0]);
		int stoneNum = Integer.parseInt(dataArray[0]);
		int removeLimit = Integer.parseInt(dataArray[1]);
		
		NimGame game = new NimGame(stoneNum, removeLimit);
		
		if (playerExists(dataArray[2], playerArray) && 
				playerExists(dataArray[3], playerArray))
		{
			int player1Index = playerIndex(dataArray[2], playerArray);
			int player2Index = playerIndex(dataArray[3], playerArray);
			gameProcess(game, playerArray, player1Index, 
					player2Index, keyboard, initialStoneNum);
		}
		else
		{
			System.out.println("One of the players does not exist.");
		}
	}
	//End of method
	
	//This method is used to indicate the process of game.
	public static void gameProcess(NimGame game, NimPlayer[] playerArray, 
			int player1Index, int player2Index, Scanner keyboard, int initialStoneNum)
	{
		System.out.println();
		System.out.println("Initial stone count: " + game.getStoneNum());
		System.out.println("Maximum stone removal: " + game.getRemoveLimit());
		System.out.println("Player 1: " + playerArray[player1Index].getName());
		System.out.println("Player 2: " + playerArray[player2Index].getName());
		System.out.println();
		
		boolean firstPersonRound = true;
		
		while (game.getStoneNum() > 0) 
		{
			game.setStoneNum(eachRound(game, playerArray, player1Index,
					player2Index, firstPersonRound, keyboard, initialStoneNum));
			firstPersonRound = !firstPersonRound;
		}	
		
		firstPersonRound = gameResult(playerArray, player1Index,
				player2Index, firstPersonRound, keyboard);
		
		updateStats(playerArray, player1Index, player2Index, firstPersonRound);
	}
	//End of method
	
	//This method is used to indicate each round of the game.
	public static int eachRound(NimGame game, NimPlayer[] playerArray, 
			int player1Index, int player2Index, boolean firstPersonRound, 
			Scanner keyboard, int initialStoneNum)
	{
		String starNum;
		boolean validInput = false;
		
		while (! validInput)
		{
			starNum = "";
			int removeNum;
			
			for (int i = 0; i < game.getStoneNum(); i++)
				starNum += " *";
			System.out.println(game.getStoneNum() + " stones left:" + starNum);
		
			System.out.println(( (firstPersonRound)? playerArray[player1Index].getGivenName()
					: playerArray[player2Index].getGivenName()) + "\'s turn - remove how many?");
			
			if (firstPersonRound) 
			{
				playerArray[player1Index].removeStone(game.getStoneNum(), 
						game.getRemoveLimit(), keyboard);
				removeNum = playerArray[player1Index].getRemoveNum();
			}
			else
		    {
				playerArray[player2Index].removeStone(game.getStoneNum(), 
						game.getRemoveLimit(), keyboard);
				removeNum = playerArray[player2Index].getRemoveNum();
			}
		
			System.out.println();
			
			try 
			{
				if (removeNum >=1 && removeNum <= Math.min(game.getStoneNum(), 
						game.getRemoveLimit()))
				{
					game.removeStone(removeNum);
				}
				else
				{
					throw new InvalidMoveException();
				}
				validInput = true;
			}
			catch (InvalidMoveException e)
			{
				System.out.println("Invalid move. You must remove between 1 and "
				+ Math.min(game.getStoneNum(), game.getRemoveLimit()) + " stones.");
				System.out.println();
			}
		}
		
		return game.getStoneNum();
	}
	//End of method
	
	//This method is used to display the result of each game.
	public static boolean gameResult(NimPlayer[] playerArray, int player1Index, 
			int player2Index, boolean firstPersonRound, Scanner keyboard)
	{
		System.out.println("Game Over");
		System.out.println((firstPersonRound ? playerArray[player1Index].getName()
				: playerArray[player2Index].getName()) + " wins!");
		//This variable is only used to remove the '\n'.
		
		if (! (playerArray[player1Index].getClass() == NimAIPlayer.class 
				&& playerArray[player2Index].getClass() == NimAIPlayer.class))
		{
			keyboard.nextLine();
		}

		return firstPersonRound;
	}
	//End of method
	
	//This method is used to update the statistics of each player after each round.
	public static void updateStats(NimPlayer[] playerArray, int player1Index, 
			int player2Index, boolean firstPersonRound)
	{
		if (firstPersonRound)
		{
			playerArray[player1Index].wonNumIncrement();
	    }
		else
		{
			playerArray[player2Index].wonNumIncrement();
		}
		
		playerArray[player1Index].playedNumIncrement();
		playerArray[player2Index].playedNumIncrement();
		
		playerArray[player1Index].calculateWonRatio();
		playerArray[player2Index].calculateWonRatio();
	}
	//End of method
	
	//This method is used to exit the system.
	public static void exit(NimPlayer[] playerArray) throws IOException
	{
		ObjectOutputStream outputStream = new ObjectOutputStream
       		 (new FileOutputStream("players.dat"));
		
		outputStream.writeInt(NumOfPlayers);
			
		for (int index = 0; index < NumOfPlayers; index++)
			outputStream.writeObject(playerArray[index]);
			
		outputStream.close();
		
		System.out.println();
		System.exit(0);
	}
	//End of method
}
//End of class

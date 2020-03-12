package com.main_package;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Keeps track of the map the Hero is currently
 * battling through and updates the map
 * Uses Point Class defined in the java.awt library
 * Hero has a Map Class object
 * @author Deron Washington II
 * Last update: 9/17/19 at 3:08am
 */
public class Map
{
	// PRIVATE MEMBERS //////////////////////////////////////////////////////////////////////////////////////////
	
	/** 2 dimensional character array to store map as we update it	 */
	private char [][] map = new char[5][5];
	
	/** 2 dimensional character array to determine whether a particular 
	 * spot on the array should be revealed to the user or not. It 
	 * depends on whether the Hero has been to that location or not.
	 */
	boolean [][] revealed = new boolean[5][5];
	
	
	
	// PUBLIC METHODS ///////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Default Constructor of the Map
	 * Initializes revealed to all false
	 * so everything will be revealed
	 */
	public Map()
	{
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 5; j++)
				revealed[i][j] = false;
	}
	
	
	/**
	 * Method to load the map in from the text file
	 * referenced to by the parameter, store in map
	 * @param mapNum is the number of the map to load
	 */
	void loadMap(int mapNum)
	{
		String mapFile = "";
		int cLength = 9;
		String mapLine [] = new String [5];
		char cMapLine []= new char [cLength];
		char row [] = new char [5];

		// which map to load

		if (mapNum == 1)
			mapFile = "Map1.txt";

		else if(mapNum == 2)
			mapFile = "Map2.txt";

		else if (mapNum == 3)
			mapFile = "Map3.txt";

		try
		{
			File file = new File(mapFile);
			Scanner read = new Scanner (file);			

			// erase whitespace in a string function
			String temp = "";
			for (int i = 0; i < 5; i++)
			{
				mapLine[i] = read.nextLine();
				cMapLine = mapLine[i].toCharArray();


				for(int j = 0; j < 9; j++)
				{
					// if it is not a space add it to temp string
					if (cMapLine[j] != 32)
						temp += cMapLine[j];
				}

				// convert temp to char array and store in row
				row = temp.toCharArray();

				// populates row of map 
				for (int col = 0; col < 5; col++)
					{
						map[i][col] = row[col];
						revealed[i][col] = false;
					}

				// at the end reset temp string
				temp = "";
			} // end of for loop with index i
			read.close();
		} // end of try

		catch (FileNotFoundException FNF)
		{
			System.out.print("There was an error opening your file.\nCheck your file name.");
		}
		catch (Exception e)
		{
			System.out.print("Something went wrong!");
		}

	} // end of load map

	/**
	 * Gets the character on the map referenced to by 
	 * parameter p
	 * @param p is object that holds the location of the
	 * character we want to return
	 * @return the character at p on the map
	 */
	char getCharAtLoc(Point p)
	{
		// p.y represents the rows
		// p.x represents the cols
		int x = p.x,
			y = p.y;
		
		char c = map[x][y];
		
		return c;
	}

	/**
	 * Displays the map and its specific characters depending
	 * on the revealed variable's values
	 * @param p is object that holds the location of the Hero
	 */
	void displayMap(Point p)	
	{
		// variables to help with displaying asterisks where Hero is
		int x = p.x,
			y = p.y;

		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				// if we have been to that location and it is not 
				//where we currently are
				if (revealed[i][j] == true && i != x && j != y) 
					System.out.print(map[i][j] + " ");
				
				// if Hero is at a location
				else if (i == x && j == y)
					System.out.print('*' + " ");
				
				// if we have been there
				else if (revealed[i][j] == true)
					System.out.print(map[i][j] + " ");
				
				else 
					System.out.print('x' + " ");
			}

			//new line for each row for formatting 
			System.out.print("\n");
		}
	}
	
	/**
	 * Returns the location of the start of the map
	 * @return the location of the 's'
	 */
	Point findStart()
	{
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 5; j++)
			{
				// return location of the s
				if (map[i][j] == 's')
				{
					Point tempLoc = new Point(i,j);
					return tempLoc;
				}
			}
		
		return null;
	}
	
	/**
	 * Reveals the character at p in on the map by
	 * updating the revealed private member variable
	 * @param p is the location that is to be revealed
	 */
	void reveal(Point p)
	{
		revealed[p.x][p.y] = true;
	}
	
	/**
	 * Removes character at p and changes it to nothing 'n'
	 * @param p is the location to be removed and changed
	 */
	void removeCharAtLoc(Point p)
	{
		if (map[p.x][p.y] == 'e' || map[p.x][p.y] == 'i')
		{
			map[p.x][p.y] = 'n';
		}
	}
	
}



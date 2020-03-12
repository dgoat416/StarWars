package com.main_package;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Generates an enemy and an item for the enemy
 * EnemyGenerator has an ItemGenerator
 * @author Deron Washington II
 * Last update: 9/14/19 at 2:30am
 */
public class EnemyGenerator
{

	// PRIVATE MEMBERS ///////////////////////////////////////////////////////////
	
	 /** Holds a list of the possible enemies  */
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	
	/** Will be used to create an item for the enemy */
	private ItemGenerator ig;
	
	
	// PUBLIC METHODS ////////////////////////////////////////////////////////////
	
	/** Constructor to create an enemy with an item 
	 * Reads in EnemyList.txt and stores in enemyList
	 * @param ig is used to store the return of the generate item method
	 */
	EnemyGenerator(ItemGenerator ig)
	{
		this.ig = ig;
		
		try
		{
			// setup the file to be read
			File file = new File("EnemyList.txt");
			Scanner read = new Scanner(file);
			
			// captures the data gathered by scanner
			String input = "";		
			String [] EnemyInfo = new String[3];
			
			// temporary integer for converting string to int for Enemy Class				
			int temp_maxHp = 0;
			
			// force variable to hold to determine which type of enemy to make
			char force;
			
			// process the file
			while(read.hasNext() == true)
			{
				// read line and parse into separate fields for enemy generation
				input = read.nextLine();
				EnemyInfo = input.split(",");
				
				// convert string data for maxHp (max health) into integer
				temp_maxHp = Integer.parseInt(EnemyInfo[1]);
				
				// covert string to char for force 
				force = EnemyInfo[2].charAt(0);
				
				
				switch (force)
				{
				case 'f':
				{
					// creates ForceEnemy
					enemyList.add( new ForceEnemy(EnemyInfo[0], 0, temp_maxHp, ig.generateItem()) );
					break;
				}
				case 'n':
				{
					// creates Enemy (put the item in the item section as null
					enemyList.add( new Enemy(EnemyInfo[0], 0, temp_maxHp, ig.generateItem()) );
					break;
				}
					default:
						{
							System.out.print("Which type of enemy did you want to create?");
						}
				}
								
			}	// end while loop
								
			
			// close the scanner and file
			read.close();

		}	// end try
		catch(Exception FNF)
		{
			System.out.print("File Error. File possibly not found. \nCheck your file name and directory.");
		}
	}
	
	/**
	 * Generates an Enemy by randomly selecting a specific enemy in enemyList
	 * @param level is the level that the Enemy should be at
	 * @return a tempEnemy which is a copy of an enemy from enemyList
	 */
	Enemy generateEnemy(int level)
	{
		// generate random enemy
		Random rand = new Random();
		int random = rand.nextInt(enemyList.size());
		
		// look at whether the enemy is a force enemy or not and construct accordingly
		
		if (enemyList.get(random) instanceof ForceEnemy)
		{
			ForceEnemy tempEnemy = new ForceEnemy( enemyList.get(random).getName(), level, enemyList.get(random).getMaxHp() * level * 2, enemyList.get(random).getItem() );
			return tempEnemy;
		}
		else if (enemyList.get(random) instanceof Enemy)
		{
			 Enemy tempEnemy = new Enemy( enemyList.get(random).getName(), level, enemyList.get(random).getMaxHp() * level * 2, enemyList.get(random).getItem() );
			 return tempEnemy;
		}
		
		// if not of type of ForceEnemy or Enemy return an Enemy object = null
		return null;
		
	}
	
	
	
}

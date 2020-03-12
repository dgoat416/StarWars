package com.main_package;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * ItemGenerator Class to Generate an item for the Enemy to hold 
 * and the Hero to use
 * @author Deron Washington II
 * Last update: 9/11/19 at 9:24pm
 */
public class ItemGenerator
{
	// PRIVATE MEMBERS ////////////////////////////////////////////////////////////////////
	
	/** Holds the list of Item Class objects */
	ArrayList<Item> itemList = new ArrayList<Item>();
	
	
	
	// PUBLIC METHODS ////////////////////////////////////////////////////////////////////
	
	/** Class Constructor to create an item
	 * It reads in the ItemList.txt file 
	 * and populates itemList  
	 */
	ItemGenerator() 
	{		
		try 
		{
			// set up reading of file
			File file = new File ("ItemList.txt");
			Scanner read = new Scanner(file);
			String input;
			
			while (read.hasNext() == true)
			{
				input = read.nextLine();
				
				// adds new Item to the list and stores the string 
				// captured in x into name field of Item Class
				itemList.add(new Item(input));			
			}
			
			read.close();
	
		}
		catch (Exception FNF)
		{
			System.out.print("File Error. File possibly not found. \nCheck your file name and directory.");
		}
		
	}
	
	/**
	 * Generates an item by randomly selecting an item in itemList
	 * @return tempItem which is a new copy of an item from itemList
	 */
	Item generateItem()
	{
		// generate random item
		Random rand = new Random();
		int random = rand.nextInt(itemList.size());
		
		// create new item
		Item tempItem = new Item(itemList.get(random).getName());
		
		return tempItem;
	}
	

}

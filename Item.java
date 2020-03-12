package com.main_package;

/**
 * Item Class to represent an item that will help the Hero 
 * Class Object and is held by the Enemy Class Object
 * @author Deron Washington II
 * Last Update: 9/10/19 at 8:22
 */
public class Item
{
	// PRIVATE MEMBER /////////////////////////////////////////////////////////////////////////
	
	/** Holds the name of the Item	 */
	private String name;		
	
	
	
	// PUBLIC METHODS /////////////////////////////////////////////////////////////////////////
	
	/*
	 * Item Class Constructor to initialize the name
	 * @param n is an identifier representing the name of the item
	 */
	Item(String n)
	{
		name = n;
	}
	
	/**
	 * Method to output the name of the item
	 * @return the name of the item 
	 */
	String getName()
	{
		return name;
	}
}

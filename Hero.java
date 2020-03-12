package com.main_package;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents the Hero the user will be controlling
 * through the duration of the game
 * Hero is an Entity
 * Hero has a Map
 * Hero has an Item
 * Hero uses Force
 * @author Deron Washington II
 * Last update: 9/17/19 at 3:35am
 */
public class Hero extends Entity implements Force
{

	// PRIVATE MEMBERS ////////////////////////////////////////////////////////////////////////////////

	/** Holds the Items the Hero has */
	private ArrayList<Item> Items = new ArrayList<Item>();		

	/** Holds the map for the Hero */
	private Map map = new Map();

	/** Holds the location that the Hero is currently at */
	private Point location;



	// PUBLIC METHODS ///////////////////////////////////////////////////////////////////////////////

	/**
	 * Hero Constructor to initialize all member variables
	 * and all fields from the Entity Class
	 * @param n is the name of Hero
	 * @param m is the Map variable to save into map member variable
	 */
	public Hero(String n, Map m)
	{
		// when this object is constructed, level = 1 and maxHp = 30
		super(n, 1, 30);
		// TODO Auto-generated constructor stub
		map = m;
		map.loadMap(1);
		location = map.findStart();
	}

	/**
	 * Details how the Hero attacks the Enemy
	 * Hero has the choice to use Force if they have holocron
	 * or attack with their blaster. Both attacks damage the 
	 * enemy a random amount contingent upon the level of the 
	 * Hero
	 */
	@Override
	public void attack(Entity e)
	{
		// TODO Auto-generated method stub

		int damage = 0;

		Random rand = new Random();

		try 
		{
			damage = rand.nextInt(e.getMaxHp() - 3) + ( 2 * e.getLevel());
		}
		catch (Exception badBound)
		{
			damage = rand.nextInt(e.getMaxHp()) + 1;
		}
		finally 
		{
			e.takeDamage(damage);
			System.out.printf("\n%s attacks %s for %d damage.\n", getName(), e.getName(), damage);
		}
	}



	/**
	 * Method to attack using forcePush power
	 * @return the amount of damage that forcePush delivers
	 */
	@Override
	public int forcePush()
	{
		// TODO Auto-generated method stub

		// How much damage?
		Random rand = new Random();
		int damage = rand.nextInt(getMaxHp() - 1) + getLevel() + 1;

		return damage;
	}

	/**
	 * Method to attack using forceChoke power
	 * @return the amount of damage that forceChoke delivers
	 */
	@Override
	public int forceChoke()
	{
		// TODO Auto-generated method stub

		// How much damage?
		Random rand = new Random();
		int damage = rand.nextInt(getMaxHp() - 1) + getLevel() + 2;

		return damage;
	}

	/**
	 * Method to attack using forceSlam power
	 * @return the amount of damage that forceSlam delivers
	 */
	@Override
	public int forceSlam()
	{
		// TODO Auto-generated method stub

		// How much damage?
		Random rand = new Random();
		int damage = rand.nextInt(getMaxHp() - 1) + getLevel() + 3;

		return damage;
	}

	/**
	 * Display the name, level, health, inventory
	 * and map of the Hero
	 */
	@Override
	public void display()
	{
		super.display();
		displayItems();
		map.displayMap(location);	
	}

	/**
	 * Display the Inventory and Items of the Hero
	 */
	public void displayItems()	
	{
		System.out.print("Inventory: \n");

		for(int i = 0; i < Items.size(); i++)
			System.out.printf("%d." + Items.get(i).getName() + "\n" , i + 1);
	}

	/**
	 * Return the number of items in Items ArrayList
	 * @return number of items in Items ArrayList member variable
	 */
	public int getNumItems()
	{
		return Items.size();
	}

	/**
	 * Adds an item to your inventory which is Items ArrayList 
	 * as long as Hero doesn't already have 5 items in inventory
	 * @param i is the item that can possibly be added
	 * @return true if the item was added and false otherwise
	 */
	public boolean pickUpItem(Item i)
	{


		if (Items.size() < 5)
		{
			Items.add(i);
			return true;
		}

		else if (Items.size() == 5)
		{
			Scanner read = new Scanner(System.in);
			int selection = 0;

			System.out.print("\nYour Inventory is full. Would you like to: \n1. Drop Item in your "
					+ "inventory and pick up this one \n2. Keep Current Inventory\n");

			selection = read.nextInt();



			// remove item of the item number they select and add current item
			if (selection == 1)
			{
				System.out.print("\nWhich item in your Inventory would you like to drop?\n"
						+ "Input the number of the item you would like to drop.\n");

				selection = read.nextInt();
				Items.remove(selection - 1);

				if(Items.size() < 5)
					Items.add(i);

				read.close();
				return true;

			}


			// keep current inventory
			else if (selection == 2)
			{
				read.close();
				return false;
			}

			read.close();
		}


		return false;
	}

	/**
	 * Remove an item in your inventory that has the name n
	 * @param n is the name of the item to remove from inventory
	 * @return the Item you removed
	 */
	public Item removeItem (String n)
	{
		for (int i = 0; i < Items.size(); i++)
		{
			if (n.compareToIgnoreCase(Items.get(i).getName()) == 0)
				return Items.remove(i);
		}

		//System.out.printf("\n%s is not in your inventory!\n", n);
		return null;
	}

	/**
	 * Remove the Item at index in your inventory
	 * @param index is the location of the item in your inventory - 1
	 * @return the Item you removed
	 */
	public Item removeItem(int index)
	{
		try
		{
			if (index < Items.size())
				return Items.remove(index - 1);
			else 
				return null;
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			System.out.print("The index you put in doesn't correspond to your inventory.");
		}

		return null;

	}

	/**
	 * Checks whether the Hero has a medKit in it's inventory
	 * @return true if it has a medKit and false otherwise
	 */
	public boolean hasMedKit()
	{
		String itemName =  "Med Kit";

		for (int i = 0; i < Items.size(); i++)
		{
			if (itemName.compareToIgnoreCase(Items.get(i).getName()) == 0)
				return true;
		}

		return false;
	}

	/**
	 * Checks whether the Hero has a Holocron in it's inventory
	 * @return true if it has a Holocron and false otherwise
	 */
	public boolean hasHolocron()
	{
		String itemName =  "Holocron";

		for (int i = 0; i < Items.size(); i++)
		{
			if (itemName.compareToIgnoreCase(Items.get(i).getName()) == 0)
				return true;
		}

		return false;
	}

	/**
	 * Checks whether the Hero has a Key in it's inventory
	 * @return true if it has a Key and false otherwise
	 */
	public boolean hasKey()
	{
		String itemName =  "Key";

		for (int i = 0; i < Items.size(); i++)
		{
			if (itemName.compareToIgnoreCase(Items.get(i).getName()) == 0)
				return true;
		}

		return false;
	}


	/**
	 * Checks whether the Hero has Armor in it's inventory
	 * @return true if it has Armor and false otherwise
	 */
	public boolean hasArmor()
	{
		String[] itemNames = {"Helmet", "Shield", "Chestplate"};

		for (int i = 0; i < Items.size(); i++)
		{
			if (itemNames[0].compareToIgnoreCase(Items.get(i).getName()) == 0 
					|| itemNames[1].compareToIgnoreCase(Items.get(i).getName()) == 0
					|| itemNames[2].compareToIgnoreCase(Items.get(i).getName()) == 0 )
				return true;
		}

		return false;
	}

	/**
	 * Returns the location of the Hero
	 * @return location member variable
	 */
	public Point getLocation()
	{
		return location;
	}

	/**
	 * Moves the Hero's location North one unit
	 * @return the char at the room the Hero is entering
	 */
	public char goNorth()	
	{
		map.reveal(location);

		if (location.x > 0)
		{
			location.x--;
			return map.getCharAtLoc(location);
		}

		else
			System.out.print("\nYou can't go north. There is a wall there.\n");

		return map.getCharAtLoc(location);
	}

	/**
	 * Moves the Hero's location South one unit
	 * @return the char at the room the Hero is entering
	 */
	public char goSouth()
	{
		map.reveal(location);

		if (location.x < 4)
		{
			location.x++;
			return map.getCharAtLoc(location);
		}

		else 
			System.out.print("\nYou can't go south. There is a wall there.\n");

		return map.getCharAtLoc(location);
	}

	/**
	 * Moves the Hero's location East one unit
	 * @return the char at the room the Hero is entering
	 */
	public char goEast()
	{
		map.reveal(location);

		if (location.y < 4)
		{
			location.y++;
			return map.getCharAtLoc(location);
		}

		else 
			System.out.print("\nYou can't go east. There is a wall there.\n");

		return map.getCharAtLoc(location);
	}


	/**
	 * Moves the Hero's location West one unit
	 * @return the char at the room the Hero is entering
	 */
	public char goWest()
	{
		map.reveal(location);

		if (location.y > 0)
		{
			location.y--;
			return map.getCharAtLoc(location);
		}

		else 
			System.out.print("\nYou can't go west. There is a wall there.\n");

		return map.getCharAtLoc(location);
	}



}


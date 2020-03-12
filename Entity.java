package com.main_package;

/**
 * Entity Class to be inherited by the Hero Class and the Enemy Class
 * @author Deron Washington II
 * Last update: 9/10/19 at 8:10pm
 */
public abstract class Entity 
{
	// PRIVATE DATA MEMBERS //////////////////////////////////////////////////////////
	
	/** Name of the entity */
	private	String name;
	
	/** Level of the entity	 */
	private int level;
	
	/** Max health of the entity */
	private int maxHp;
	
	/** Health of the entity */
	private int hp;
	
	
	
	// PUBLIC FUNCTIONS ////////////////////////////////////////////////////////////////////
	/**
	 * Entity Class constructor 
	 * @param n is an identifier representing name of the entity
	 * @param l is an identifier representing the level of the entity
	 * @param m is an identifier representing the maxHP of the entity
	 */
	public Entity(String n, int l, int m)
	{
		name = n;
		level = l;
		maxHp = m;
		hp = m;
	}
	
	/**
	 * Abstract function for attacking an Entity
	 * @param e is an identifier representing an Entity Class object (or a subclass)
	 */
	public abstract void attack(Entity e);
	
	/** 
	 * Get function to return private field name 
	 * @return	name corresponding to the Entity class object
	 */
	public String getName()
	{
		return name;
	}
		
	/**
	 * Get function to return the private field level
	 * @return	level corresponding to the Entity class object
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Get function to return the private field hp (health)
	 * @return	current value of health corresponding to the Entity class object
	 */
	public int getHp()
	{
		return hp;
	}
	
	/**
	 * Get function to return the private field of max hp (max health)
	 * @return	max health corresponding to the Entity class object
	 */
	public int getMaxHp()
	{
		//return (maxHp +( level * 2));
		return maxHp;
	}
	
	
	/**
	 * Function to increase the level of an Entity class object
	 */
	public void increaseLevel()
	{
		level++;
	}
	
	/**
	 * Form of a set function to heal the Entity class object by h hp(health)
	 * @param h is an identifier to represent the health to increase by
	 */
	public void heal(int h)
	{
		if ( (hp + h) > maxHp )
			hp = maxHp;
		
		else
			hp+= h;
	}

	/**
	 * Form of a set function to simulate the Entity class object taking damage by h hp(health)
	 * @param h is an identifier to represent the health to decrease by
	 */
	public void takeDamage(int h)
	{
		hp -= h;
	}
	
	/**
	 * Form of a set function to increase the MaxHp (max health) of the Entity class object
	 * @param h is an identifier to represent the amount to increase maxHp by
	 */
	public void increaseMaxHP(int h)
	{
		maxHp += h;
	}
	
	/**
	 * Form of a set function to decrease the MaxHp (max health) of the Entity class object
	 * @param h is an identifier to represent the amount to decrease maxHp by
	 */
	public void decreaseMaxHP(int h)
	{
		maxHp -= h;
	}
	
	/**
	 * Displays all the private members (fields) of the Entity class object except MaxHP
	 */
	public void display()
	{
		System.out.print("\n" + name + " Lvl: " + level
						+ "\nHP: " + hp + "/" + maxHp + "\n");
	}
	
	
	
}

package com.main_package;

import java.util.Random;

/**
 * Enemy Class inherits the Entity Class
 * Enemy is an instance of EnemyGenerator
 * Enemy has an Item
 * ForceEnemy is a Enemy
 * @author Deron Washington II
 * Last update: 9/12/19 at 2:30am
 *
 */
public class Enemy extends Entity
{
	// PRIVATE MEMBERS /////////////////////////////////////////////////////////////////////////

	/*   Item for the Enemy to hold until the Hero defeats them	 */
	private Item item;



	// PUBLIC METHODS //////////////////////////////////////////////////////////////////////////

	/**
	 * Enemy Constructor to initialize all members of 
	 * Enemy and the Entity Class
	 * @param n is the name of the enemy
	 * @param l is the level of the enemy
	 * @param m is the maxHp of the enemy
	 * @param i is the item that the enemy has
	 */
	public Enemy(String n, int l, int m, Item i)
	{
		super(n, l, m);
		// TODO Auto-generated constructor stub
		item = i;
	}

	/*
	 * Details how the Enemy attacks the Hero
	 */
	@Override
	public void attack(Entity e)
	{
		// TODO Auto-generated method stub

		// How much damage?
		Random rand = new Random();
		int damage = 0;


		try 
		{
			damage = rand.nextInt(e.getMaxHp() - 3) + e.getLevel();
		}
		catch (Exception badBound)
		{
			damage = rand.nextInt(e.getMaxHp()) + 1;
		}
		finally
		{
			e.takeDamage(damage);
			System.out.printf("\n%s hits %s for %d damage.\n", getName(), e.getName(), damage);
		}
	}


	/**
	 * Get method for item member variable
	 * @return item member variable
	 */
	public Item getItem()	
	{
		return item;
	}

}

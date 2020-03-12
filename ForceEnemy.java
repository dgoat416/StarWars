package com.main_package;

import java.util.Random;

/**
 * ForceEnemy inherits the Enemy superclass 
 * ForceEnemy implements the Force interface
 * @author Deron Washington II
 * Last Update: 9/12/19 at 6:30pm
 */
public class ForceEnemy extends Enemy implements Force
{

	/**
	 * ForceEnemy Constructor
	 * @param n represents the name of the ForceEnemy
	 * @param l represents the level of the ForceEnemy
	 * @param m represents the maxHp of the ForceEnemy
	 * @param item represents the item that the ForceEnemy possesses 
	 */
	public ForceEnemy(String n, int l, int m, Item item)
	{
		super(n, l, m, item);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Details how the ForceEnemy attacks the Hero
	 * Randomly chooses between forcePush, forceChoke,
	 * and forceSlam
	 */
	@Override
	public void attack (Entity e)
	{	
		// randomize the functions 1 to 3 
		Random rand = new Random();
		int forcePower = 0;
		int damage = 0;
		
		// random number between 1 and 3
		forcePower = rand.nextInt(3) + 1;
		
		switch (forcePower)
		{
		case 1:
			{
				damage = forcePush();
			System.out.printf("\n%s hits %s with a Force Push for %d damage.\n", getName(), e.getName(), damage);
			break;
			}
		case 2:
			{
				damage = forceChoke();
				System.out.printf("\n%s hits %s with a Force Choke for %d damage.\n", getName(), e.getName(), damage);
				break;
			}
		case 3:
			{
				damage = forceSlam();
			System.out.printf("\n%s hits %s with a Force Slam for %d damage.\n", getName(), e.getName(), damage);
			break;
			}
			
		}
		
		e.takeDamage(damage);
		
	}

	/**
	 * Function for force enemies to attack using forcePush
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
	 * Function for force enemies to attack using forceChoke
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
	 * Function for force enemies to attack using forceSlam
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

}

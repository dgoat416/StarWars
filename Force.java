package com.main_package;

/**
 * Interface to be implemented in ForceEnemy that allows
 * the ForceEnemy object to have special abilities listed
 * as the functions below
 * @author Deron Washington II
 * Last update: 9/12/19 at 6:30pm
 *
 */
public interface Force
{
	/** Menu for the interface to select which action the 
	 * ForceEnemy wants to perform
	 */
	public static final String FORCE_MENU = 
			"1. Force Push\n2. Force Choke\n3. Force Slam\n";
	
	/*
	 * Interface functions to perform the specific type 
	 * of force actions listed
	 */
	public int forcePush();
	public int forceChoke();
	public int forceSlam();
}

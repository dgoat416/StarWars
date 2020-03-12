package com.main_package;

import java.util.Random;
import java.util.Scanner;

/**
 * Class to run the main program
 * @author Deron Washington II
 * Last Update: 9/17/19 at 4:
 */
public class Main_Package 
{
	/**
	 * Method to determine if the room the Hero just entered was
	 * a room with an Enemy ('e' on the map) or not and proceed
	 * accordingly
	 * @param h represents the Hero Class Object the user controls
	 * @param m represents the Map Class Object associated with Hero
	 * @param eg represents the EnemyGenerator Class Object to create
	 * enemies and items if necessary
	 * @return true if the room just entered is a room with an Enemy 
	 */
	public static boolean enemyRoom(Hero h, Map m, EnemyGenerator eg)
	{
		if ( m.getCharAtLoc(h.getLocation()) == 'e' )
		{
			Enemy bad = eg.generateEnemy(1);

			System.out.printf("\nYou've encountered a %s!\n", bad.getName());
			bad.display();

			//			String FIGHT_MENU = "\n1. Fight\n2. Run Away\n";
			//
			//			System.out.print(FIGHT_MENU);
			//			int Choice = CheckInput.getIntRange(1, 2);

			// FIGHT
			//			if (Choice == 1)
			//			{
			if (fight(h, bad) == false)
			{ 
				// if Hero died 
				if (h.getHp() <= 0)
				{
					System.out.print("\n\nYou failed your mission! You are a disgrace to your planet!\n");
					System.exit(0);
				}

				// if Hero killed Enemy
				else	if (bad.getHp() <= 0)
				{
					System.out.printf("\nYou defeated the %s!", bad.getName());
					m.removeCharAtLoc(h.getLocation());

					if( h.pickUpItem(bad.getItem()) )
						System.out.printf("\nYou have recieved a %s from the Enemy.\n ", bad.getItem().getName());

					return true;
				}

				// Hero is running
				//					else 
				//					{
				//						Choice = 2;
				//					}
			}

			//					if (h.hasHolocron())
			//						{
			//						System.out.print(WHICH_ATTACK_MENU);
			//						whichAttack = CheckInput.getIntRange(1, 2);
			//						
			//						switch(whichAttack)
			//						{
			//						case 1: // USE BLASTER
			//						{
			//							h.attack(bad);
			//						}
			//						case 2:	// USE FORCE
			//						{
			//							System.out.print(Force.FORCE_MENU);
			//							int forcePower = CheckInput.getIntRange(1,3);
			//							int damage = 0;
			//							
			//							switch (forcePower)
			//							{
			//							case 1:
			//								damage = h.forcePush();
			//								break;
			//							case 2:
			//								damage = h.forceChoke();
			//								break;
			//							case 3:
			//								damage = h.forceSlam();
			//								break;
			//								
			//							}
			//							
			//							bad.takeDamage(damage);
			//						} // end of case 2 of first switch
			//						
			//						} // end switch(whichAttack)
			//						
			//						
			//						} // end of if(hasHolocron)
			//					
			//					else  // USE BLASTER
			//					{
			//						h.attack(bad);
			//					}
			//				}	// end second if loop
		} // end if(Choice == 1)

		//			// RUN TO RANDOM ROOM
		//			if (Choice == 2)
		//			{
		//				int validMove = 0;
		//
		//				// Is North Valid
		//				if (h.getLocation().x > 0)
		//					validMove++;
		//
		//				// Is South Valid
		//				if (h.getLocation().x < 4)
		//					validMove++;
		//
		//				// Is East Valid
		//				if (h.getLocation().y < 4)
		//					validMove++;
		//
		//				// Is West Valid
		//				if (h.getLocation().y > 0)
		//					validMove++;
		//
		//				Random rand = new Random();
		//				int run = rand.nextInt(validMove) + 1;
		//				char nextRoom = '\0';
		//
		//				switch (run)
		//				{
		//				case 1:
		//				{
		//					nextRoom = h.goNorth();
		//
		//					if (nextRoom != 'e')
		//						return false;
		//
		//					else 
		//						return true;
		//
		//				}
		//				case 2:
		//				{
		//					nextRoom = h.goSouth();
		//
		//					if (nextRoom != 'e')
		//						return false;
		//
		//					else 
		//						return true;
		//
		//
		//				}
		//				case 3:
		//				{
		//					nextRoom = h.goEast();
		//
		//					if (nextRoom != 'e')
		//						return false;
		//
		//					else 
		//						return true;
		//
		//				}
		//				case 4:
		//				{
		//					nextRoom = h.goWest();
		//
		//					if (nextRoom != 'e')
		//						return false;
		//
		//					else 
		//						return true;
		//
		//				}
		//				} // end switch
		//
		//			} // end if(Choice == 2)

		return true;
	} // end of first if statement if in enemy room
	//		else 
	//			return true;
	//
	//	}

	/**
	 * Method to determine if the user wants to fight or run away 
	 * and carries out the fight based on the rules of the game
	 * @param h is the Hero Class Object the user controls
	 * @param e is the Enemy/ForceEnemy Class Object that the Hero fights
	 * @return true if the user chooses to fight and false if the user runs
	 * or if the user or the enemy die
	 */
	public static boolean fight(Hero h, Enemy e)
	{
		String FIGHT_MENU = "\n1. Fight\n2. Run Away\n";
		String FULL_FIGHT_MENU = "\n1. Fight \n2. Run Away \n3. Use Med Kit\n";
		String WHICH_ATTACK_MENU = "\n1. Use Blaster\n2. Use Force\n";

		int Choice = 1;
		int whichAttack = 0;
		int done = 0;
		int j = 0;

		// still fighting
		while (done != -1)
		{
			//if (j > 0)
			//{
			if (h.hasMedKit())
			{
				System.out.print(FULL_FIGHT_MENU);
				Choice = CheckInput.getIntRange(1, 3);
			}

			else 
			{
				System.out.print(FIGHT_MENU);
				Choice = CheckInput.getIntRange(1, 2);
			}
			//}


			// FIGHT
			if (Choice == 1)
			{
				if (h.hasHolocron())
				{
					System.out.print(WHICH_ATTACK_MENU);
					whichAttack = CheckInput.getIntRange(1, 2);

					switch(whichAttack)
					{
					case 1: // USE BLASTER
					{
						h.attack(e);
						break;
					}
					case 2:	// USE FORCE
					{
						// remove Holocron when you use force
						h.removeItem("Holocron");
						System.out.print(Force.FORCE_MENU);
						int forcePower = CheckInput.getIntRange(1,3);
						int damage = 0;



						switch (forcePower)
						{
						case 1:
						{
							damage = h.forcePush();
							System.out.printf("\n%s hits %s with a Force Push for %d damage.\n", h.getName(), e.getName(), damage);
							break;
						}
						case 2:
						{
							damage = h.forceChoke();
							System.out.printf("\n%s hits %s with a Force Choke for %d damage.\n", h.getName(), e.getName(), damage);
							break;
						}
						case 3:
						{
							damage = h.forceSlam();
							System.out.printf("\n%s hits %s with a Force Slam for %d damage.\n", h.getName(), e.getName(), damage);
							break;
						}

						} // end of small switch
						e.takeDamage(damage);

					} // end of case 2 switch(whichAttack)

					} // end of switch whichAttack
				} // end of if(hasHolocron)

				// IF NO HOLOCRON ATTACK USING BLASTER
				else
				{
					h.attack(e);
				}

				j++; // keeps track of how many times we run
				// enemy attacks hero
				e.attack(h);
				if (h.hasArmor())
				{
					String [] Armor = {"Helmet", "Shield", "Chestplate"};

					for (int i = 0; i < Armor.length; i++)
					{
						if (h.removeItem(Armor[i]) != null)
						{
							h.heal(1);
						}

					}
				}

				// if hero dies
				if (h.getHp() <= 0)
				{
					done = - 1;
					System.out.print("\n\nYou failed your mission! You are a disgrace to your planet!\n");
					System.exit(0);
				}

				// if enemy dies
				if (e.getHp() <= 0)
				{

					done = -1;
					break;
				}

			}	// end if(Choice == 1)

			// RUN
			else if (Choice == 2)
			{// RUN TO RANDOM ROOM
				if (Choice == 2)
				{
					int validMove = 0;

					// Is North Valid
					if (h.getLocation().x > 0)
						validMove++;

					// Is South Valid
					if (h.getLocation().x < 4)
						validMove++;

					// Is East Valid
					if (h.getLocation().y < 4)
						validMove++;

					// Is West Valid
					if (h.getLocation().y > 0)
						validMove++;

					Random rand = new Random();
					int run = rand.nextInt(validMove) + 1;
					char nextRoom = '\0';

					switch (run)
					{
					case 1:
					{
						nextRoom = h.goNorth();

						if (nextRoom != 'e')
							return false;

						else 
							return true;

					}
					case 2:
					{
						nextRoom = h.goSouth();

						if (nextRoom != 'e')
							return false;

						else 
							return true;


					}
					case 3:
					{
						nextRoom = h.goEast();

						if (nextRoom != 'e')
							return false;

						else 
							return true;

					}
					case 4:
					{
						nextRoom = h.goWest();

						if (nextRoom != 'e')
							return false;

						else 
							return true;

					}
					} // end switch

				} // end if(Choice == 2)
				done = -1;
				break;
			}

			// USE MEDKIT
			else if (Choice == 3)
			{
				h.removeItem("Med Kit");
				h.heal(25);
				h.display();
			}

		} // end of if

		return false;


	} // end of function 


	/**
	 * Method to determine if the room just entered has an Item and 
	 * proceed accordingly
	 * @param h is the Hero Class Object the user controls
	 * @param m represents the Map Class Object associated with Hero
	 * @param ig is the ItemGenerator Class Object to create the Item 
	 */
	public static void itemRoom(Hero h, Map m, ItemGenerator ig)
	{
		if (m.getCharAtLoc(h.getLocation()) == 'i')
		{
			if (h.pickUpItem(ig.generateItem()))
			{
				System.out.print("\nYou picked up a %s! \nIt has been added to your Inventory!");
				m.removeCharAtLoc(h.getLocation());
			}
		}
	}

	/**
	 * Main Program
	 * @param args to allow main to execute
	 */
	public static void main(String[] args) 
	{
		// Prompt for input
		System.out.print("Name your Hero: \n");

		// Take in input
		String heroName = CheckInput.getString();

		int mapNumber = 0;;
		Map myMap = new Map();
		Hero myHero = new Hero(heroName, myMap);


		// end test code
		ItemGenerator myIG = new ItemGenerator();
		EnemyGenerator myEG = new EnemyGenerator(myIG);
		Enemy Villain = myEG.generateEnemy(1);
		int input = 0;
		String MENU = "1. Go North \n2. Go South \n3. Go East \n4. Go West \n5. Quit\n";
		char newLocation = '\0';

		while (input != 5)
		{
			myHero.display();
			System.out.print(MENU);
			input = CheckInput.getIntRange(1, 5);

			switch(input)
			{
			case 1:
			{
				newLocation = myHero.goNorth();
				myHero.display();
				break;
			}
			case 2:
			{
				newLocation = myHero.goSouth();
				myHero.display();
				break;
			}
			case 3:
			{
				newLocation = myHero.goEast();
				myHero.display();
				break;
			}
			case 4:
			{
				newLocation = myHero.goWest();
				myHero.display();
				break;
			}
			case 5:
				System.out.print("\nGAME OVER!");
				System.exit(0);	

			}// end switch


			if(newLocation == 'n')
			{
				System.out.print("\nThere was nothing here\n");
				myHero.display();
				continue;
			}
			if(newLocation == 'i')
			{
				itemRoom(myHero, myMap, myIG);


				myHero.display();
				continue;
			}

			if(newLocation == 'e') 
			{
				while (enemyRoom(myHero, myMap, myEG) == false);

				myHero.display();
				continue;
			}

			if (newLocation == 'f')
			{
				if (myHero.hasKey())
				{
					if (myHero.getLevel() < 3)
						myMap.loadMap(myHero.getLevel());

					else 
					{
						mapNumber = myHero.getLevel() % 3;
						myMap.loadMap(mapNumber);
					}

					myHero.removeItem("Key");

				}

				else
				{
					if (myHero.hasHolocron())	
					{
						Random rand = new Random();
						int random = rand.nextInt(2) + 1;
						int open = 1;

						if (random == open)
						{
							myHero.increaseLevel();
							myHero.increaseMaxHP(10);

							if (myHero.getLevel() < 3)
							{
								myMap.loadMap(myHero.getLevel());
								for(int k = 0; k < 5; k++)
									for(int l = 0; l < 5; l++)
										myMap.revealed[k][l] = false;
							}


							else 
							{
								mapNumber = myHero.getLevel() % 3;
								myMap.loadMap(mapNumber);
								for(int k = 0; k < 5; k++)
									for(int l = 0; l < 5; l++)
										myMap.revealed[k][l] = false;
							}


						}

						myHero.removeItem("Holocron");

					}

					else if(myHero.hasKey())
					{
						myHero.increaseLevel();
						myHero.increaseMaxHP(10);

						if (myHero.getLevel() < 3)
						{
							myMap.loadMap(myHero.getLevel());
							for(int k = 0; k < 5; k++)
								for(int l = 0; l < 5; l++)
									myMap.revealed[k][l] = false;
						}

						else 
						{
							mapNumber = myHero.getLevel() % 3;
							myMap.loadMap(mapNumber);
							for(int k = 0; k < 5; k++)
								for(int l = 0; l < 5; l++)
									myMap.revealed[k][l] = false;
						}

						myHero.removeItem("Key");


					}
				}

			}

		} // end of while loop


	} // end of Main
} // end of Main_Package

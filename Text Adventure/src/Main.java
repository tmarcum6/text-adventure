import java.util.Scanner;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		/***************************
		  *****System Objects******
		 ***************************/
		//Scanner for inputs
		Scanner scanner = new Scanner(System.in);
		//Random number generator
		Random rand = new Random();
		
		//Game Variables
		String[] enemies = {"Pirate", "Frigate", "Cruiser", "Destroyer"};
		int maxEnemyHealth = 100;
		int enemyAttackDamage = 25;
		
		//Player Variables
		int health = 100;
		int attackDamage = 75;
		int numHealthPotions = 3;
		int healthPotionHealAmount = 30;
		int healthPotionDropChance = 50; //Percentage
		int numTokens = 75;
		int tokenDropChance  = 50; //Percentage
		
		//Boolean for main game loop
		boolean running = true;
		
		//Title 
		System.out.println("	Welcome to Galactic Warfront!	");
		
		//Tag for start of game loop
		GAME:
		while (running){
			System.out.println("----------------------------------------------------");
			
			//Randomize enemy health and type
			int enemyHealth = rand.nextInt(maxEnemyHealth);
			String enemy = enemies[rand.nextInt(enemies.length)];
			
			//Random enemy appears 
			System.out.println("\t# " + enemy + " has appeared! #\n\n");
			
			//Start of loop for while the enemy is alive
			while (enemyHealth > 0){
				
				//Navigation and health menu
				System.out.println("\tYour Health: " + health);
				System.out.println("\t" + enemy + "'s Health: " + enemyHealth);
				System.out.println("\n\tWhat would you like to do?");
				System.out.println("\t1. Attack");
				System.out.println("\t2. Repair");
				System.out.println("\t3. Run!");
				
				//Reads the users next line of input
				String input = scanner.nextLine();
				
				//If statement to setup damage dealt and received when the user selects '1'
				if (input.equals("1")){
					
					int damageDealt = rand.nextInt(attackDamage);
					int damageTaken = rand.nextInt(enemyAttackDamage);
					enemyHealth -= damageDealt;
					health -= damageTaken;
					
					//Damage Output
					System.out.println("\t> You strike the "+ enemy + " for " + damageDealt + " damage");
					System.out.println("\t> You receive " + damageTaken + " damage in return");

					//If we die print output
					if (health < 1){
						System.out.println("Oh dear... you have taken too much damage.");
						break;
					}
				
				//Else if to setup repairing ship when the user selects '2'		
				} else if (input.equals("2")){
					
					//If we have health a repair kit
					if (numHealthPotions > 0){
						health += healthPotionHealAmount;
						numHealthPotions--;
						System.out.println("\t>You reapair your ship for " + healthPotionHealAmount
										  +"\n\t> You now have " + health + " Health"
										  +"\n\t> You have " + numHealthPotions + " repairs left");							
					}
				
					//If we do not have a repair kit
					else {
						System.out.println("\t> You have no repairs left! Defeat enemies for a chance to repair.");
					}
				
				//Else if to setup running away from enemy when the user selects '3'
				} else if (input.equals("3")){
					System.out.println("\tYou run away from the " + enemy + "!");
					continue GAME;
					
				//Else to close if the user does not select '1-3'
				} else{
					System.out.println("\tInvalid Command!");
				}
			}
			
			//
			//If we die
			// 
			if (health < 1){
				System.out.println("Your ship is destroyed and cannot go any further.");
				break;
			} 
			
			//Menu for defeating an enemy
			System.out.println("----------------------------------------------------");
			System.out.println(" # " + enemy + " was deafeated! # " );
			
			//Shows how many repair kits left
			System.out.println(" # You have " + health + " Health left # ");
			
			//Sets up repair kit random drop chance 
			if(rand.nextInt(100) < healthPotionDropChance){
				numHealthPotions++;
				System.out.println(" # The " + enemy + " dropped repair kit! # ");
				System.out.println(" # You now have " + numHealthPotions + " repair kit(s). # ");
			}
		
			//Sets up token random drop chance 
			if(rand.nextInt(75) < tokenDropChance){
				numTokens++;
				System.out.println(" # The " + enemy + " dropped space coins! # ");
				System.out.println(" # You now have " + numTokens + " space coin(s). # ");
			}
			
			//Navigation for after killing first enemy
			System.out.println("----------------------------------------------------");
			System.out.println("What would you like to do now?");
			System.out.println("1. Continue fighting");
			System.out.println("2. Retreat to Mothership");
			
			//Takes the next input from the user
			String input = scanner.nextLine();
			
			//If the input is not '1-2'
			while (!input.equals("1") && !input.equals("2")){
				System.out.println("Invalid Command!");
				input = scanner.nextLine();
			}
			
			//If user input is '1' continue adventure
			if (input.equals("1")){
				System.out.println("You continue on your adventure!");
			}
			
			//If the user input is '2' do stuff inside 'Mothership'
			else if (input.equals("2")){
				System.out.println("\nYou retreat to the Mothership");
				
				//Menu for stuff inside MS
				System.out.println("\nWhat would you like to do?\n");				
				System.out.println("1. Purchase repair kits for the journey");
				System.out.println("2. Repair ship to full health for free");
				System.out.println("3. Get back to the fight\n");
				input = scanner.nextLine();

				//If user input is '1' purchase a repair kit from the Mothership
				if(input.equals("1")){
					continue;
				}
				
				//Repair ship if user input is '2'
				else if(input.equals("2")){
					
					//If we need health restore it and display 
					if (health != 100){
						health += (100-health);
						System.out.println("\t>You reapair your ship back to full health "
										  +"\n\t> You now have " + health + " Health"
										  +"\n\t> You have " + numHealthPotions + " repair kits left");							
					}
				
					//If we do not have a repair kit
					else {
						System.out.println("\t> You do not need to be repaired.");
					}
				}
				
				//If user input is '3' continue loop
				else if(input.equals("3")){
					continue GAME;
				}
				
				//If the user input is not within range
				else {
					System.out.println("Invalid Command!");
					input = scanner.nextLine();
				}
			}
		}
		
		//End game credits
		System.out.println("\n#########################");
		System.out.println(" # THANKS FOR PLAYING! # ");
		System.out.println("#########################");
	}
}


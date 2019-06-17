import java.util.Scanner;
import java.util.Random;

/*
	This class is used to simulate the battle between Hikari and Duat in the Prologue Chapter 		of the game
*/
class Battle
{
	//Declare variables
	private int damage;
	private Scanner sc = new Scanner(System.in);
	private PlayableCharacter player;
	private Hikari opponent;
	private boolean endOfBattle = false;

	/*
		Constructor method of the player and the opponent Hikari
	*/
	public Battle(PlayableCharacter player)
	{
		this.player = new PlayableCharacter(player);
		this.opponent = new Hikari();
	}
	
	/*
		A method to Display the start of the battle and move into turn phase
	*/	
	public void BattleBegin()
	{
		System.out.println(player.getName() + " vs. " + opponent.getName()); 
		turn();
	}
	
	/*
		Every turn it asks the player what out of their abilities they wish to use
	*/
	public void turn()
	{
		//Repeats until a character has less than 1HP
		while(!endOfBattle)
		{
			//Used to reset the terminal so it is easy to follow what has happened at 				//this stage in the battle
			System.out.print("\033[H\033[2J");
			System.out.flush();

			//Prints out the health and magic at that point in the battle
			System.out.println(opponent.getName() + " HP : " + opponent.getHealth() + "\n" + opponent.getName() + "Magic: " + opponent.getMana() + "\n\n\n" + player.getName() + " HP: " + player.getHealth() + "\n" + "Magic: " + player.getMana());

			System.out.println("\n Attack (type a) or defend? (type d)");
	
			//Takes in the player's decision
			Decision();
		} 
	}

	/*
		This gets the players decision and makes a move depending on the choice they make
	*/
	public void Decision()
	{
		//Catches any IO Exceptions
		try
		{
		String decision = sc.nextLine();

		System.out.println("\n");
		//If the user types 'a' for attack it will move into the attack phase of the 			//battle
		if(decision.toLowerCase().equals("a")) 
		{
			Attack();
		}
		//If the user types 'd' it will move into the defense phase of the battle
		else if(decision.toLowerCase().equals("d"))
		{
			Defend();
		}
		//Gives the user time to read the input from the battle TODO: Slow each move 			print down
		Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println("ERROR");
		}
	}

	/*
		IF the player chooses to attack, it will make it as if the opponent has decided 		to attack back (Trying to make easy as Prologue Chapter)
	*/
	public void Attack()
	{
		//Simulates the player's attack
		endOfBattle = AttackPhase(player, opponent, 0);
		System.out.println(opponent.getName() + " HP " + opponent.getHealth());
	
		//If the enemy is down at this point it ends the battle immediately
		if(endOfBattle)
		{
			Result();
			return;
		}

		//Hikari's ult, currently fixing it
		System.out.println("Hikari uses Arrow of Light!");
		opponent.ArrowOfLight(player, opponent);
		
		//Player is stunned from Hikari's ult giving Hikari and extra attack
		endOfBattle = AttackPhase(opponent, player, 0);
		
		//If the player is downed it willl set the health to 0
		if(player.getHealth() <= 0)
			player.setHealth(0);
		System.out.println("HP " + player.getHealth());

		//If the player is down at this point it ends the battle immediately
		if(endOfBattle)
		{
			Result();
			return;
		}
	}

	/*
		IF the player chooses to defend, it will make it as if the opponent has decided 		to attack
	*/
	public void Defend()
	{
		System.out.println("Hikari uses Arrow of Light!");
		opponent.ArrowOfLight(player, opponent);
		
		endOfBattle = AttackPhase(opponent, player, 1);
		System.out.println("HP: " + player.getHealth());
		if(endOfBattle)
		{
			Result();
		}
	}

	/*
		The calculation of the attack in a battle
	*/
	public int CalculateAttack(int attack, int defense, int defend)
	{
		if(defend == 0)
			damage = (attack * attack) / defense;
		else
			damage = attack / defense;

		if(damage <= 1)
		{
			return 1;
		}
		else
		{
			return damage;
		}
	}

	/*
		The calculation of the magic attack in a battle
	*/
	public int CalculateMagicAttack(int magicAttack, int magicRes, int defend)
	{
		if(defend == 0)
			damage = magicAttack * magicAttack * 2 / magicRes;
		else
			damage = magicAttack * 2 / magicRes;

		if(damage <= 1)
		{
			return 1;
		}
		else
		{
			return damage;
		}
	}

	/*
		This determines whether an attack hit or missed and what happens in each phase of 			the attack
	*/
	public boolean AttackPhase(PlayableCharacter attacker, PlayableCharacter defender, int 		defend)
	{
		//Calculates the damage done
		damage = CalculateAttack(attacker.getAttack(), defender.getDef(), defend);

		//Gets the result of the evasion
		int evaded = Evaded(defender);
		
		//If it was not evaded it gets the calculated damage and applies it
		if(evaded == 0) 
		{
			System.out.println(defender.getName() + " took " + damage + " damage");

			defender.setHealth(damage);
			
			//If the defender is downed it ends the battle there
			if(defender.getHealth() <= 0)
			{
				return true;
			}
		}
		//Otherwise no damage is done
		else
		{
			System.out.println(defender.getName() + " dodged the attack");
		}
		//If the defender is not downed it continues the battle (returning false to 			endOfBattle)
		return false;
	}

	/*
		Similar to the attack phase method, except magic is not able to evaded
	*/
	public boolean MagicAttackPhase(PlayableCharacter attacker, PlayableCharacter defender, int 		defend)
	{
		//Gets the damage of the magic ability
		damage = CalculateMagicAttack(attacker.getMagicAtk(), defender.getMagicRes(), defend);
		
		//Prints out the damage done
		System.out.println(defender.getName() + " took " + damage + " damage");

		defender.setHealth(damage);
		
		//Checks if defender is downed, if so ends battle
		if(defender.getHealth() <= 0)
		{
			return true;
		}

		//Otherwise continues the battle
		return false;
	}	

	/*
		Determines whether or not an attack was evaded (randomly)
	*/
	public int Evaded(PlayableCharacter defender)
	{
		Random rand = new Random();
		int chance = rand.nextInt(100) + 1;

		//IF it was evaded it returns 1
		if(chance < defender.getEvasion())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	/*
		Displays the result of the battle
	*/
	public void Result()
	{
		if(player.getHealth() <= 0)
		{
			System.out.println("Defeat");
		}
		else if(opponent.getHealth() <= 0)
		{
			System.out.println("Victory");
		}
		else
		{
			System.out.println("ERROR");
		}
	}
}

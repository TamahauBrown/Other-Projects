import java.util.Scanner;
import java.util.Random;

class BattleMechanics
{
	private Fighter player;
	private Fighter opponent;
	private int damage;
	protected boolean endOfBattle = false;
	private Scanner sc = new Scanner(System.in);
	private int playNum;
	private int oppNum;

	/*
		Constructor method of the player and the opponent
	*/
	public BattleMechanics(Fighter player, Fighter opponent, int playerNum, int opponentNum)
	{
		this.player = new Fighter(playerNum, player);
		this.opponent = new Fighter(opponentNum, opponent);
		playNum = playerNum;
		oppNum = opponentNum;
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
			e.printStackTrace();
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
		opponent.ArrowOfLight(player, opponent, playNum, oppNum);
		
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
		opponent.ArrowOfLight(player, opponent, playNum, oppNum);
		
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
	public boolean AttackPhase(Fighter attacker, Fighter defender, int defend)
	{
		//Calculates the damage done
		System.out.println(player.getAttack());
		//damage = CalculateAttack(attacker.getAttack(), defender.getDef(), defend);

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
	public boolean MagicAttackPhase(Fighter attacker, Fighter defender, int 		defend)
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
	public int Evaded(Fighter defender)
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

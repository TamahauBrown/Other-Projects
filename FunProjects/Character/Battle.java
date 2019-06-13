import java.util.Scanner;
import java.util.Random;

class Battle
{
	private int damage;
	private Scanner sc = new Scanner(System.in);
	private PlayableCharacter player;
	private PlayableCharacter opponent;
	private boolean endOfBattle = false;

	public Battle(PlayableCharacter player, PlayableCharacter opponent)
	{
		this.player = new PlayableCharacter(player);
		this.opponent = new PlayableCharacter(opponent);
	}
		
	public void BattleBegin()
	{
		System.out.println(player.getName() + " vs. " + opponent.getName()); 
		turn();
	}
	
	public void turn()
	{
		while(!endOfBattle)
		{
			System.out.println("Attack (type a) or defend? (type d)");
			String decision = sc.nextLine();

			if(decision.toLowerCase().equals("a")) 
			{
				endOfBattle = AttackPhase(player, opponent, 0);
				System.out.println(opponent.getName() + " HP " + opponent.getHealth());
				if(endOfBattle)
				{
					Result();
					break;
				}
				endOfBattle = AttackPhase(opponent, player, 0);
				
				if(player.getHealth() <= 0)
					player.setHealth(0);
				System.out.println("HP " + player.getHealth());
				if(endOfBattle)
				{
					Result();
					break;
				}
			}
			else if(decision.toLowerCase().equals("d"))
			{
				endOfBattle = AttackPhase(opponent, player, 1);
				System.out.println("HP: " + player.getHealth());
				if(endOfBattle)
				{
					Result();
					break;
				}
			}

			System.out.println(opponent.getName() + " HP : " + opponent.getHealth() + "\n" + player.getName() + " HP: " + player.getHealth());
		} 
	}

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

	public boolean AttackPhase(PlayableCharacter attacker, PlayableCharacter defender, int defend)
	{
		damage = CalculateAttack(attacker.getAttack(), defender.getDef(), defend);

		int evaded = Evaded(defender);
		
		if(evaded == 0) 
		{
			System.out.println(defender.getName() + " took " + damage + " damage");

			defender.setHealth(damage);
			if(defender.getHealth() <= 0)
			{
				return true;
			}
		}
		else
		{
			System.out.println(defender.getName() + " dodged the attack");
		}
		return false;
	}

	public int Evaded(PlayableCharacter defender)
	{
		Random rand = new Random();
		int chance = rand.nextInt(100) + 1;
		if(chance < defender.getEvasion())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

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

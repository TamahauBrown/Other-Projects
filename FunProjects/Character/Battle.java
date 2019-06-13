import java.util.Scanner;

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
			System.out.println("Attack or defend?");
			String decision = sc.nextLine();

			if(decision.toLowerCase().equals("a")) 
			{
				endOfBattle = AttackPhase(player, opponent, 0);
				System.out.println("Hikari HP " + opponent.getHealth());
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
			else if(decision.toLowerCase().equals("defend"))
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
			damage = attack * 3 - defense;
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

		System.out.println(defender.getName() + " took " + damage + " damage");

		defender.setHealth(damage);

		if(defender.getHealth() <= 0)
		{
			return true;
		}
		return false;
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

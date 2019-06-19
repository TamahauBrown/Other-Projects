import java.util.Random;
/*
	Creates an instance of the super boss Hikari
*/
class Hikari extends PlayableCharacter
{
	//Constructor method to create his stats
	public Hikari()
	{
		super("Hikari, the guardian of the light", "Light", 1, 450, 650, 300, 300, 90, 50, 15000, 12000);
	}

	/*
		Hikari's first Ultimate, he can choose to rewind time negating 1,000 damage
	*/
	public void extraTurn()
	{
		//Gets Hikari's current HP and checks to see if he gets restored to max HP or restores the full 1000HP
		int HP = super.getHealth();
		if(HP + 1000 <= 15000)
		{
			super.restoreHealth(1000);
			System.out.println("\n Hikari restores 1000 HP"); 
		}
		else
			super.fullHealth();
	}

	/*
		Hikari's second ultimate which does a devastating amount of damage to his opponent
	*/
	public boolean ArrowOfLight(Fighter player, Fighter opponent, int playNum, int oppNum)
	{
		//Calls the battle class to be able to do the magic attack phase
		BattleMechanics battle = new BattleMechanics(player, opponent, playNum, oppNum);

		//Buffs Hikari's magic attack for the ultimate
		int damage = super.getMagicAtk() + 500;
		int baseAttack = opponent.getMagicAtk();
		
		opponent.setMagicAtk(damage);

		boolean endOfBattle = battle.MagicAttackPhase(opponent, player, 0);
			
		//Restores Hikari's magic attack back to normal
		opponent.setMagicAtk(baseAttack);
		System.out.println("You are stunned");
		
		//Returns whether or not the battle is over
		return endOfBattle;
	}
	
	/*
		Randomly generates which of Hikari's moves he uses **NOT YET IMPLEMENTED**
	*/
	public void MoveSet()
	{
		Random rand = new Random();
		int move = 3;
		
		switch(move)
		{
			case 1:

			case 2:

			//If Hikari rewinds time, it restores his health by 1000 and gives him an extra attack
			case 3:
			System.out.println("*Hikari rewinds time*");
			extraTurn();
			break;
		}
	}
}

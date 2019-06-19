import java.util.Scanner;
import java.util.Random;

/*
	This class is used to simulate the battle between Hikari and Duat in the Prologue Chapter 		of the game
*/
class Battle
{
	//Declare variables
	private Fighter player;
	private Fighter opponent;
	BattleMechanics bm;

	/*
		Constructor method of the player and the opponent Hikari
	*/
	public Battle(Fighter player, Fighter opponent, int playerNum, int opponentNum)
	{
		this.player = new Fighter(playerNum, player);
		this.opponent = new Fighter(opponentNum, opponent);
		bm = new BattleMechanics(player, opponent, playerNum, opponentNum);
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
		while(!bm.endOfBattle)
		{
			//Used to reset the terminal so it is easy to follow what has happened at 				//this stage in the battle
			//System.out.print("\033[H\033[2J");

			//Prints out the health and magic at that point in the battle
			System.out.println(opponent.getName() + " HP : " + opponent.getHealth() + "\n" + opponent.getName() + "Magic: " + opponent.getMana() + "\n\n\n" + player.getName() + " HP: " + player.getHealth() + "\n" + "Magic: " + player.getMana());

			System.out.println("\n Attack (type a) or defend? (type d)");
	
			//Takes in the player's decision
			bm.Decision();
		} 
	}
}

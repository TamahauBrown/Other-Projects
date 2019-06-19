import java.util.*;

/*
	Creates an instance of the front menu of the game
*/
public class Game
{
	protected String name;
	 String faction;

	public static void main(String [] args)
	{
		Game game = new Game();
		CharacterChecker checker = new CharacterChecker();
	
		//Gets the character's name and faction
		game.name = checker.nameChecker();
		game.faction = checker.factionChecker(); 
		
		//Creates an instance of the player
		PlayableCharacter character = new PlayableCharacter(game.name, game.faction, 0, 650, 450, 550, 550, 60, 40, 20000, 5000);

		//Creates an instance of the player
		Hikari hikari = new Hikari();

		Fighter player = new Fighter(3, character);
		Fighter opponent = new Fighter(1, hikari);
		System.out.println("Hello " + player.getName() + ". Your faction is " + 			player.getFaction());
		player.getStats();

		//TODO: Sleep here to allow the user to read

		//Creates an instance of a battle in the prologue chaper
		Battle battle = new Battle(player, opponent, 3, 1);
		battle.BattleBegin();
	}
}

import java.util.*;

/*
	Creates an instance of the front menu of the game
*/
public class Game
{
	private String name;
	private String faction;

	public static void main(String [] args)
	{
		Game game = new Game();
		CharacterChecker checker = new CharacterChecker();
	
		//Gets the character's name and faction
		game.name = checker.nameChecker();
		game.faction = checker.factionChecker(); 
		
		//Creates an instance of the player
		PlayableCharacter character = new PlayableCharacter(game.name, game.faction, 0, 650, 450, 550, 550, 60, 40, 20000, 5000);
		System.out.println("Hello " + character.getName() + ". Your faction is " + 			character.getFaction());
		character.getStats();

		//TODO: Sleep here to allow the user to read

		//Creates an instance of a battle in the prologue chaper
		Battle battle = new Battle(character);
		battle.BattleBegin();
	}
}

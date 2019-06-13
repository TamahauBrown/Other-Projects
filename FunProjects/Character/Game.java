import java.util.*;

public class Game
{
	private String name;
	private String faction;
	public static void main(String [] args)
	{
		boolean breaker = false;

		Game game = new Game();
		CharacterChecker checker = new CharacterChecker();
		game.name = checker.nameChecker();
		game.faction = checker.factionChecker(); 
		
		PlayableCharacter character = new PlayableCharacter(game.name, game.faction, 0, 5, 5, 5, 5, 5, 5, 60);
		System.out.println("Hello " + character.getName() + ". Your faction is " + 			character.getFaction());
		character.getStats();

		//Evasion and Speed are out of 100
		PlayableCharacter Hikari = new PlayableCharacter("Hikari", "Light", 1, 5, 650, 300, 			300, 90, 85, 2000);
		System.out.println("The guardian of the light " + Hikari.getName());
		Hikari.getStats();

		Battle battle = new Battle(character, Hikari);
		battle.BattleBegin();
	}
}

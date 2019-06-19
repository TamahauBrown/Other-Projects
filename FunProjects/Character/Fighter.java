public class Fighter extends PlayableCharacter
{
    	PlayableCharacter character;

    	public Fighter(int number, PlayableCharacter ch)
    	{
		super(ch);
		Game game = new Game();
		int player = number;
		switch(player)
		{
			case 1:
			PlayableCharacter character = new Hikari();			
	
			case 2:

			case 3:
			character = new PlayableCharacter(ch);
		}
    	}

    	public boolean ArrowOfLight(Fighter player, Fighter opponent, int playNum, int oppNum)
	{
		Hikari hikari = new Hikari();
		boolean bool = false;
		bool = hikari.ArrowOfLight(player, opponent, playNum, oppNum);
		return bool;
	}

}

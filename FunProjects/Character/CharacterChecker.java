import java.util.Scanner;

class CharacterChecker
{
	Scanner sc = new Scanner(System.in);
	
	/*
		Asks the user for their character's name (less than 8 letters), if it is not a letter 			or it is longer than 8 letter it will prompt for them to do so until they do.
	*/
	public String nameChecker() 
	{
		while(true)
		{
			System.out.println("What is your name?");
			String name = sc.nextLine();
		
			if(name.length() <= 8) { 
				int nameChecker = 0;
				if(name.matches(".*[a-zA-z]+.[a-zA-z]")) { nameChecker = 1; } 
				if(nameChecker == 1) { return name.substring(0,1).toUpperCase() 							+ name.substring(1).toLowerCase(); } } }
	}

	/*
		Asks the user if they want to join the light or dark faction. If they enter 			anything other than light or dark (not case sensitive) then it will keep prompting 			them to do so.
	*/
	public String factionChecker() 
	{
		while(true)
		{
			System.out.println("Light or Dark?");
			String faction = sc.nextLine();
		
			if(faction.toLowerCase().equals("light") || 				     				   faction.toLowerCase().equals("dark")) 
			{
			   	return faction.substring(0,1).toUpperCase() +			 				   	faction.substring(1).toLowerCase(); 
			} 
		}
	}
}

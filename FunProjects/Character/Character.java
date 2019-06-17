/* Creates an instance of the basic skeleton of a game character */
class Character
{
	//Gets their name faction and character id e.g.: a bat might be 6
	private String name;
	private String faction;
	private int id;

	//Basic constructor method to get their name, id and faction
	public Character(String name, String faction, int id)
	{ this.name = name; this.faction = faction; this.id = id; }
	
	/* Getter methods */
	public String getName() { return name; }
	public String getFaction() { return faction; }
	public int getId() { return id; }
}

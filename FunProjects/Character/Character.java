class Character
{
	private String name;
	private String faction;
	private int id;

	public Character(String name, String faction, int id)
	{
		this.name = name;
		this.faction = faction;
		this.id = id;
	}
	
	public String getName()
	{ return name; }
	
	public String getFaction()
	{ return faction; }

	public int getId()
	{ return id; }
}

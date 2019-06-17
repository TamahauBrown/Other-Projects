/*
	Creates an instance of a character that is not a harmless NPC (Non-playable character)
*/
class PlayableCharacter extends Character
{
	//Stores all the character's stats in an array
	int [] stats = new int [9];
	
	/*
		Constructor method to set all the character's stats
	*/
	public PlayableCharacter(String name, String faction, int id, int atk, int magicAtk, int def, int magicRes, int atkSpd, int evasion, int health, int mana)
	{
		super(name, faction, id);
		stats[0] = atk; stats[1] = magicAtk; stats[2] = def; stats[3] = magicRes; stats[4] = atkSpd; stats[5] = evasion; stats[6] = health; stats[7] = health; stats[8] = mana;
	}

	/*
		Creates an instance of a character using an exisiting character
	*/
	public PlayableCharacter(PlayableCharacter player)
	{
		super(player.getName(), player.getFaction(), player.getId());
		stats[0] = player.getAttack();
		stats[1] = player.getMagicAtk();
		stats[2] = player.getDef();
		stats[3] = player.getMagicRes();
		stats[4] = player.getAtkSpd();
		stats[5] = player.getEvasion();
		stats[6] = player.getHealth();
		stats[7] = player.getMaxHP();
		stats[8] = player.getMana();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* Getter Methods */
	public int getAttack() { return stats[0]; }
	public int getMagicAtk() { return stats[1]; }
	public int getDef() { return stats[2]; }
	public int getMagicRes() { return stats[3]; }
	public int getAtkSpd() { return stats[4]; }
	public int getEvasion() { return stats[5]; }
	public int getHealth() { return stats[6]; }
	public int getMaxHP() { return stats[7]; }
	public int getMana() { return stats[8]; }

	public void getStats() {
		System.out.println("Your stats are: \n" +
				   "Attack: " + stats[0] +
				   "\nMagic Attack: " + stats[1] +
				   "\nDefense: " + stats[2] +
				   "\nMagic Resistance: " + stats[3] +
				   "\nAttack Speed: " + stats[4] +
				   "\nEvasion: " + stats[5]); }

	////////////////////////////////////////////////////////////////////////////////////////////////////

	/* Setter methods */
	public void setAttack(int attack) { stats[0] = attack; }
	public void setMagicAtk(int buff) { stats[1] += buff; }
	public void setHealth(int damage) { stats[6] -= damage; }
	public void setMana(int spellCost) { stats[8] -= spellCost; }

	////////////////////////////////////////////////////////////////////////////////////////////////////

	//Restores the character to full HP
	public void fullHealth() { stats[6] = stats[7]; }

	//Restores the character's health by the amount of the restoration
	public void restoreHealth(int restore) { stats[6] += restore; }
}

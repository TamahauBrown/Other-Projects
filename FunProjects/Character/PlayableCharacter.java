class PlayableCharacter extends Character
{
	int [] stats = new int [7];
	
	public PlayableCharacter(String name, String faction, int id, int atk, int magicAtk, int def, int magicRes, int atkSpd, int evasion, int health)
	{
		super(name, faction, id);
		stats[0] = atk;
		stats[1] = magicAtk;
		stats[2] = def;
		stats[3] = magicRes;
		stats[4] = atkSpd;
		stats[5] = evasion;
		stats[6] = health;
	}

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
	}

	public int getAttack()
	{
		return stats[0];
	}

	public int getMagicAtk()
	{
		return stats[1];
	}

	public int getDef()
	{
		return stats[2];
	}

	public int getMagicRes()
	{
		return stats[3];
	}

	public int getAtkSpd()
	{
		return stats[4];
	}

	public int getEvasion()
	{
		return stats[5];
	}

	public int getHealth()
	{
		return stats[6];
	}

	public void setHealth(int damage)
	{
		stats[6] -= damage;
	}

	public void getStats()
	{
		System.out.println("Your stats are: \n" +
				   "Attack: " + stats[0] +
				   "\nMagic Attack: " + stats[1] +
				   "\nDefense: " + stats[2] +
				   "\nMagic Resistance: " + stats[3] +
				   "\nAttack Speed: " + stats[4] +
				   "\nEvasion: " + stats[5]);
	}
}

package classtype;

import java.io.IOException;
import java.util.ArrayList;
import character.Generator;


public class Class {
	public String[] Features, Weapons, Armor, Proficiencies;
	public int Level, MaxHealth;
	public PrimeStat Prime, Secondary;
	public ClassName cla;
	public Class(int level, ClassName c) {
		this.Level = level;
		cla = c;
		ArrayList<String[]> featArr;
		try {
			switch(cla) {
				case BARBARIAN:
					featArr = Generator.genArrFromFile("Barbarian.txt");
					break;
				case BARD:
					featArr = Generator.genArrFromFile("Bard.txt");
					break;
				case CLERIC:
					featArr = Generator.genArrFromFile("Cleric.txt");
					break;
				case DRUID:
					featArr = Generator.genArrFromFile("Druid.txt");
					break;
				case FIGHTER:
					featArr = Generator.genArrFromFile("Fighter.txt");
					break;
				case MONK:
					featArr = Generator.genArrFromFile("Monk.txt");
					break;
				case PALADIN:
					featArr = Generator.genArrFromFile("Paladin.txt");
					break;
				case RANGER:
					featArr = Generator.genArrFromFile("Ranger.txt");
					break;
				case ROGUE:
					featArr = Generator.genArrFromFile("Rogue.txt");
					break;
				case SORCERER:
					featArr = Generator.genArrFromFile("Sorcerer.txt");
					break;
				case WARLOCK:
					featArr = Generator.genArrFromFile("Warlock.txt");
					break;
				case WIZARD:
					featArr = Generator.genArrFromFile("Wizard.txt");
					break;
				default:
					featArr = Generator.genArrFromFile("Fighter.txt");
			}
		}
		catch (IOException e){
			featArr = null;
			System.out.println("This isn't working correctly");
		}
		this.Features = new String[5];
		if(Level>0)
			this.Features[0] = featArr.get(0)[0];
		if(Level>1)
			this.Features[1] = featArr.get(1)[0];
		if(Level>2)
			this.Features[2] = featArr.get(2)[0];
		if(Level>3)
			this.Features[3] = featArr.get(3)[0];
		if(Level>4)
			this.Features[4] = featArr.get(4)[0];
		this.Armor = featArr.get(5);
		this.Weapons = featArr.get(6);
		this.Proficiencies = featArr.get(7);
		String[] primeStats = featArr.get(8);
		int HitDie = Integer.parseInt(featArr.get(9)[0]);
		this.MaxHealth = HitDie;
		for(int i = 1; i<=Level-1; i++) {
			this.MaxHealth += (int)(Math.random()*HitDie)+1;
		}
		assignPrimeStat(primeStats[0]);
		assignSecondStat(primeStats[1]);
	}
	public void assignPrimeStat(String prime) {
		if (prime.equals("Str"))
			this.Prime = PrimeStat.STR;
		else if (prime.equals("Dex"))
			this.Prime = PrimeStat.DEX;		
		else if (prime.equals("Con"))
			this.Prime = PrimeStat.CON;
		else if (prime.equals("Int"))
			this.Prime = PrimeStat.INT;
		else if (prime.equals("Wis"))
			this.Prime = PrimeStat.WIS;
		else if (prime.equals("Cha"))
			this.Prime = PrimeStat.CHA;
	}
	public void assignSecondStat(String sec) {
		if (sec.equals("Str"))
			this.Secondary = PrimeStat.STR;
		else if (sec.equals("Dex"))
			this.Secondary = PrimeStat.DEX;		
		else if (sec.equals("Con"))
			this.Secondary = PrimeStat.CON;
		else if (sec.equals("Int"))
			this.Secondary = PrimeStat.INT;
		else if (sec.equals("Wis"))
			this.Secondary = PrimeStat.WIS;
		else if (sec.equals("Cha"))
			this.Secondary = PrimeStat.CHA;
	}
	public String[] getFeatures() {
		return Features;
	}
	public String[] getWeapons() {
		return Weapons;
	}
	public String[] getArmor() {
		return Armor;
	}
	public String[] getProficiencies() {
		return Proficiencies;
	}
	public int getLevel() {
		return Level;
	}
	public int getMaxHealth() {
		return MaxHealth;
	}
	public PrimeStat getPrime() {
		return Prime;
	}
	public PrimeStat getSecondary() {
		return Secondary;
	}
	
	public String toString() {
		return cla.toString();
	}
}

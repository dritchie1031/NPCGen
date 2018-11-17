package classtype;

public enum ClassName {
	BARBARIAN("Barbarian"), BARD("Bard"), CLERIC("Cleric"), DRUID("Druid"), FIGHTER("Fighter"), MONK("Monk"),
	PALADIN("Paladin"), RANGER("Ranger"), ROGUE("Rogue"), SORCERER("Sorcerer"), WARLOCK("Warlock"), WIZARD("Wizard"),
	BANDIT("Bandit"),CULTIST("Cultist"),SOLDIER("Soldier");
	
	private String name;
	
	private ClassName(String n) {
		name = n;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public static ClassName getClassName(int i) {
		switch (i) {
		case 0:
			return BARBARIAN;
		case 1:
			return BARD;
		case 2:
			return CLERIC;
		case 3:
			return DRUID;
		case 4:
			return FIGHTER;
		case 5:
			return MONK;
		case 6:
			return PALADIN;
		case 7:
			return RANGER;
		case 8:
			return ROGUE;
		case 9:
			return SORCERER;
		case 10:
			return WARLOCK;
		case 11:
			return WIZARD;
		case 12:
			return BANDIT;
		case 13:
			return CULTIST;
		case 14:
			return SOLDIER;
		default:
			return ClassName.FIGHTER;
		}
	}
}

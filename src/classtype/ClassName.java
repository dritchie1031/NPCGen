package classtype;

public enum ClassName {
	BARBARIAN("Barbarian"), BARD("Bard"), CLERIC("Cleric"), DRUID("Druid"), FIGHTER("Fighter"), MONK("Monk"), PALADIN("Paladin"), 
		RANGER("Ranger"), ROGUE("Rogue"), SORCERER("Sorcerer"), WARLOCK("Warlock"), WIZARD("Wizard");
	
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
			return ClassName.BARBARIAN;
		case 1:
			return ClassName.BARD;
		case 2:
			return ClassName.CLERIC;
		case 3:
			return ClassName.DRUID;
		case 4:
			return ClassName.FIGHTER;
		case 5:
			return ClassName.MONK;
		case 6:
			return ClassName.PALADIN;
		case 7:
			return ClassName.RANGER;
		case 8:
			return ClassName.ROGUE;
		case 9:
			return ClassName.SORCERER;
		case 10:
			return ClassName.WARLOCK;
		case 11:
			return ClassName.WIZARD;
		default:
			return ClassName.FIGHTER;
		}
	}
}

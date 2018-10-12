package classtype;

public enum PrimeStat {
	STR(0), DEX(1), CON(2), INT(3), WIS(4), CHA(5);
	private final int placeVal;
	PrimeStat(int place){
		placeVal = place;
	}
	public int getPlace() {
		return placeVal;
	}
}

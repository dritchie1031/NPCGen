package racetype;

import java.io.IOException;
import java.util.ArrayList;

import character.Generator;

public class Race {
	String[] FirstNames, LastNames, Eyes, Hair, Skin, Features;
	String RaceName;
	int[] ScoreBonuses, Weight, Age, Height;
	int Speed;
	Sex s;

	public Race(Sex sex, int raceNum) {
		this.s = sex;
		switch (raceNum) {
		case 1:
			RaceName = "Dwarf";
			break;
		case 2:
			RaceName = "Elf";
			break;
		case 3:
			RaceName = "Halfling";
			break;
		case 4:
			RaceName = "Human";
			break;
		case 5:
			RaceName = "Dragonborn";
			break;
		case 6:
			RaceName = "Gnome";
			break;
		case 7:
			RaceName = "Half-Elf";
			break;
		case 8:
			RaceName = "Half-Orc";
			break;
		case 9:
			RaceName = "Tiefling";
			break;
		default:
			RaceName = "Human";
		}

		ArrayList<String[]> traitArr;
		try {
			traitArr = Generator.genArrFromFile(RaceName + ".txt");
		} catch (IOException e) {
			traitArr = null;
			System.out.println("This isn't working correctly");
		}
		if (this.s == Sex.MALE) {
			this.FirstNames = traitArr.get(0);
			this.Height = Generator.convertStrArrToIntArr(traitArr.get(1));
			this.Weight = Generator.convertStrArrToIntArr(traitArr.get(8));
		} else if (this.s == Sex.FEMALE) {
			this.FirstNames = traitArr.get(3);
			this.Height = Generator.convertStrArrToIntArr(traitArr.get(4));
			this.Weight = Generator.convertStrArrToIntArr(traitArr.get(8));
		} else {
			this.FirstNames = traitArr.get(6);
			this.Height = Generator.convertStrArrToIntArr(traitArr.get(7));
			this.Weight = Generator.convertStrArrToIntArr(traitArr.get(8));
		}
		this.Speed = 30;
		this.Age = Generator.convertStrArrToIntArr(traitArr.get(9));
		this.Eyes = traitArr.get(10);
		this.Hair = traitArr.get(11);
		this.LastNames = traitArr.get(12);
		this.ScoreBonuses = Generator.convertStrArrToIntArr(traitArr.get(13));
		this.Skin = traitArr.get(14);
		this.Features = traitArr.get(15);
	}

	public String toString() {
		return RaceName;
	}

	public String[] getFirstNames() {
		return FirstNames;
	}

	public String[] getEyes() {
		return Eyes;
	}

	public String[] getHair() {
		return Hair;
	}

	public String[] getSkin() {
		return Skin;
	}

	public int[] getHeight() {
		return Height;
	}

	public int[] getScoreBonuses() {
		return ScoreBonuses;
	}

	public int[] getWeight() {
		return Weight;
	}

	public int[] getAge() {
		return Age;
	}

	public int getSpeed() {
		return Speed;
	}

	public String[] getLastNames() {
		return LastNames;
	}

	public String[] getFeatures() {
		return Features;
	}
}

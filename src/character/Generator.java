package character;

import java.util.ArrayList;
import java.io.*;
import classtype.PrimeStat;

public class Generator {
	public static Object generate(Object[] arr) {
		int rand = (int)(Math.random()*arr.length);
		return arr[rand];
	}
	public static int generateNum(int[] arr) {
		int rand = (int)(Math.random()*arr.length);
		return arr[rand];
	}
	public static int[] generateStats(PrimeStat a, PrimeStat b) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0; i < 6; i++)
			arr.add(getRandStat());
		int temp = 0;
		for(int i = 0; i < arr.size(); i++) {
			for(int j = i+1; j<arr.size(); j++) {
				if(arr.get(i)<arr.get(j)) {
					temp = arr.get(j);
					arr.set(j, arr.get(i));
					arr.set(i, temp);
				}
			}
		}
		int[] finalStats = new int[6];
		finalStats[a.getPlace()] = arr.remove(0);
		finalStats[b.getPlace()] = arr.remove(0);
		for(int i = 0;i<finalStats.length;i++) {
			if(finalStats[i] == 0) {
				int place = (int)(Math.random()*arr.size());
				finalStats[i] = arr.remove(place);
			}
		}
		return finalStats;
	}
	private static int getRandStat() {
		int a = (int)(Math.random()*6)+1;
		int b = (int)(Math.random()*6)+1;
		int c = (int)(Math.random()*6)+1;
		int d = (int)(Math.random()*6)+1;
		if(a<=b && a<=c && a<=d)
			return b+c+d;
		else if(b<=a && b<=c && b<=d)
			return a+c+d;
		else if(c<=a && c<=b && c<=d)
			return a+b+d;
		else
			return a+b+c;
	}
	
	public static ArrayList<String[]> genArrFromFile(String filename) throws IOException{
		FileReader reader = new FileReader(filename);
		ArrayList<Character> arr = new ArrayList<Character>();
		int c = reader.read();
		while (c!=-1) {
			arr.add((char)c);
			c = reader.read();
		}
		reader.close();
		char[] newArr = new char[arr.size()];
		for (int i = 0; i < arr.size();i++) {
			newArr[i] = arr.get(i);
		}
		String totalStr = new String(newArr);
		String[] smallSplit = totalStr.split("/");
		ArrayList<String[]> retArr = new ArrayList<String[]>();
		for (String s:smallSplit) {
			retArr.add(s.split("-"));
		}
		return retArr;
	}
	public static int[] convertStrArrToIntArr(String[] arr) {
		int[] retArr = new int[arr.length];
		for (int i = 0; i < arr.length;i++) {
			retArr[i] = Integer.parseInt(arr[i].trim());
		}
		return retArr;
	}
}

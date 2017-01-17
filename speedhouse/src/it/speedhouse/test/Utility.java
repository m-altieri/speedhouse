package it.speedhouse.test;

import java.util.ArrayList;

abstract class Utility {
	
	public static boolean equals (ArrayList<String> o1, String[] o2) {
		if (o1.size() != o2.length) 
			return false;
		for (int i = 0; i < o1.size(); i++) {
			if (o1.get(i) != o2[i])
				return false;
		}
		return true;
	}

	
	public static boolean equals (ArrayList<String[]> o1, ArrayList<String[]> o2) {
		
		if (o1.size() != o2.size()) {
			return false;
		}
		
		for (int k = 0; k < o1.size(); k++) {
			
			if (!equals(o1.get(k), o2.get(k))) {
				return false;
			}
		}
		return true;
	}
	public static boolean equals (String[] o1, String[] o2) {

		if (o1.length != o2.length)
			return false;
		for (int k = 0; k < o1.length; k++) {
			if (!(o1[k].equals(o2[k])))
				return false;
		}
		return true;
	}

}

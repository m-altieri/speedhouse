package it.speedhouse.test;

import java.util.ArrayList;

abstract class FileUtility {

	
	public static boolean equals (ArrayList<String[]> o1, ArrayList<String[]> o2) {
		if (o1.size() != o2.size())
			return false;
		if (o1.get(0).length != o2.get(0).length)
			return false;
		for (int k = 0; k < o1.size(); k++) {
			for (int p = 0; p < o1.get(k).length; p++) {
				if (!(o1.get(k)[p].equals(o2.get(k)[p])))
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

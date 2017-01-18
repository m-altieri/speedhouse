package it.speedhouse.test;

import java.util.ArrayList;

/**
 * 
 * Classe astratta contenente vari metodi statici di utilità generale.
 *
 */
abstract class Utility {
	/**
	 * Confronta i dati inseriti in un ArrayList con quelli all'interno di un array di stringhe
	 * @param o1 l'ArrayList di stringhe da confrontare.
	 * @param o2 l'array di stringhe da confrontare.
	 * @return true se i dati inseriti sono gli stessi, altrimenti false
	 */
	public static boolean equals (ArrayList<String> o1, String[] o2) {	//confronta un ArrayList di Stringhe con un array di stringhe
		if (o1.size() != o2.length) 	//l'ArrayList ha dimensione diversa rispetto all'array
			return false;				//Quindi essi sono diversi tra loro
		for (int i = 0; i < o1.size(); i++) { //Confronta ogni elemento dell'ArrayList con quello dell'array nella stessa posizione
			if (!o1.get(i).equals(o2[i]))
				return false;
		}
		return true;
	}

	/**
	 * Confronta due ArrayList di array di stringhe.
	 * @param o1 l'ArrayList di array di stringhe da confrontare.
	 * @param o2 l'ArrayList di array di stringhe da confrontare.
	 * @return true se i dati inseriti nei due ArrayList sono uguali, o altrimenti false
	 */
	public static boolean equals (ArrayList<String[]> o1, ArrayList<String[]> o2) {	//confronta due ArrayList di array di stringhe
		
		if (o1.size() != o2.size()) {
			return false;		//La loro dimensione è diversa, dunque non sono uguali
		}
		
		for (int k = 0; k < o1.size(); k++) {
			if (!equals(o1.get(k), o2.get(k))) {	//mediante questa funzione vengono confrontati i due array che si trovano nella medesima posizione nei loro rispettivi ArrayList
				return false;	//i due array non sono uguali tra loro
			}
		}
		return true;	//se fin'ora non è stato trovata alcuna differenza tra i due ArrayList, essi sono uguali
	}
	
	/**
	 * Confronta due array di stringhe.
	 * @param o1 L'array di stringhe da confrontare.
	 * @param o2 L'array di stringhe da confrontare.
	 * @return true se i dati inseriti nei due array sono uguali.
	 */
	public static boolean equals (String[] o1, String[] o2) {	//confronta due array di stringhe

		if (o1.length != o2.length)		
			return false;
		for (int k = 0; k < o1.length; k++) {	//confronta ogni elemento di un array con quello nell'altro array nella stessa posizione
			if (!(o1[k].equals(o2[k])))
				return false;
		}
		return true;
	}

}

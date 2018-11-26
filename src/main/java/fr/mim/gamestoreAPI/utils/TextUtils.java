package fr.mim.gamestoreAPI.utils;

import java.text.Normalizer;

public class TextUtils {

	public static String normaliser(String mot){
		String res = Normalizer.normalize(mot, Normalizer.Form.NFD);
	    res = res.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		res = res.trim().toLowerCase();
		return res;				
	}
}

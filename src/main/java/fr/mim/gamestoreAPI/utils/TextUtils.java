package fr.mim.gamestoreAPI.utils;

import java.text.Normalizer;

public class TextUtils {
	private static final String NORMALIZE = "[\\p{InCombiningDiacriticalMarks}]";

	private TextUtils(){	}
	
	public static String normaliser(String mot){
		String res = Normalizer.normalize(mot, Normalizer.Form.NFD);
	    res = res.replaceAll(NORMALIZE, "");
		res = res.trim().toLowerCase();
		return res;				
	}
}

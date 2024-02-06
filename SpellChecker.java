
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		if (word1.length() == 0)
			return word2.length();
		else{
			if (word2.length() == 0)
			return word1.length();
		    else{
			    if (word1.charAt(0) == word2.charAt(0))
			    return levenshtein(tail(word1),tail(word2));
			    else{
			        return 1 + Math.min(Math.min(levenshtein(tail(word1),word2),levenshtein(word1,tail(word2))),levenshtein(tail(word1),tail(word2)));
		        }   
		    }
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i<3000; i++){
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minLev = levenshtein(word,dictionary[0]);
		int N = 0;
		for (int i=1 ; i<3000; i++){
			if (levenshtein(word,dictionary[i])<minLev){
				minLev = levenshtein(word,dictionary[i]);
				N = i;
			}
		}
		if (levenshtein(word,dictionary[N]) <= threshold){
				return dictionary[N];
			}
			else
				return word;
	}
}



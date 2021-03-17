package de.cas.experts.software.solitaireNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SolitaireInputGenerator {

	public static String generateInput(int size) {
		List<String> numbers = new ArrayList<String>(size);
		for (int i = 0; i <= size / 2; i++) {
			numbers.add(Integer.toString(i));
			numbers.add(Integer.toString(i));
		}
		Collections.shuffle(numbers, new Random(size));
		numbers.remove(numbers.size() - 1);
		return String.join(" ", numbers);
	}

}

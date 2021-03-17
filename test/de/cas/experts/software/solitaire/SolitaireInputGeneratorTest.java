package de.cas.experts.software.solitaire;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import de.cas.experts.software.solitaireNumber.SolitaireInputGenerator;

class SolitaireInputGeneratorTest {

	@Test
	void testSize1() {
		String input = SolitaireInputGenerator.generateInput(1);
		assertTrue(isValidSolitaireInput(input, 1));
	}

	@Test
	void testSize10() {
		String input = SolitaireInputGenerator.generateInput(10);
		assertTrue(isValidSolitaireInput(input, 10));
	}

	@Test
	void testSize100() {
		String input = SolitaireInputGenerator.generateInput(100);
		assertTrue(isValidSolitaireInput(input, 100));
	}

	private boolean isValidSolitaireInput(String input, int size) {
		List<Integer> numbers = Arrays.stream(input.split(" ")).map(x -> Integer.parseInt(x))
				.collect(Collectors.toList());
		boolean sizeMatches = numbers.size() == size || numbers.size() == size + 1;
		Supplier<Stream<Integer>> frequencies = () -> numbers.stream().map(x -> Collections.frequency(numbers, x));
		boolean oneOrTwoTimes = frequencies.get().allMatch(x -> x == 1 || x == 2);
		boolean singleItemOnce = frequencies.get().filter(x -> x == 1).count() == 1;
		return sizeMatches && oneOrTwoTimes && singleItemOnce;
	}

}

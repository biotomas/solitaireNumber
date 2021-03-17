package de.cas.experts.software.solitaire;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.cas.experts.software.solitaireNumber.SolitaireExecutionPlan;
import de.cas.experts.software.solitaireNumber.SolitaireNumberSolver;

class SolitaireNumberTest {
	
	int testMethod(String input) {
		SolitaireNumberSolver sn = new SolitaireNumberSolver();
		SolitaireExecutionPlan ep = new SolitaireExecutionPlan();
		ep.input = input;
		return sn.findSolitaireNumberWithMap(ep);
	}

	@Test
	void testSimpleSolitaire_SHOULD_find() {
		int result = testMethod("1 2 1");
		assertEquals(2, result);
	}

	@Test
	void testTwoDigitSolitaire_SHOULD_find() {
		int result = testMethod("11 2 2 1 1");
		assertEquals(11, result);
	}

	@Test
	void testSolitaire_SHOULD_findAtFirstPos() {
		String input = "1 2 2 3 3";
		int result = testMethod(input);
		assertEquals(1, result);
	}

	@Test
	void testSolitaire_SHOULD_findAtLastPos() {
		String input = "2 2 3 3 1";
		int result = testMethod(input);
		assertEquals(1, result);
	}

	@Test
	void testMaxIntSolitaire_SHOULD_findAtLastPos() {
		String input = "2 2 3 3 2147483647";
		int result = testMethod(input);
		assertEquals(2147483647, result);
	}

}

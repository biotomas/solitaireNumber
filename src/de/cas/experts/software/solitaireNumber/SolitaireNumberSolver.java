package de.cas.experts.software.solitaireNumber;

import static java.util.Collections.frequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

import de.cas.experts.software.Stopwatch;
import de.cas.experts.software.TimeLimitExceededException;

@Warmup(iterations = 2, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1, warmups = 1)
@BenchmarkMode(Mode.AverageTime)
public class SolitaireNumberSolver {

	/**
	 * This is a lazy inefficient solution, but short code and easy to read. Because
	 * of linear time required by the frequency method this algorithm is quadratic.
	 * 
	 * We do not even need to run the sampler on this, just move on the next (linear
	 * time) solution
	 */
	@Benchmark
	public int findSolitaireNumber(SolitaireExecutionPlan ep) {
		Stopwatch watch = new Stopwatch();
		List<Integer> numbers = readIntegersWithScanner(ep.input);
		for (int number : numbers) {
			if (frequency(numbers, number) == 1) {
				return number;
			}
			if (watch.elapsedSeconds(1)) {
				throw new TimeLimitExceededException();
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * This is linear solution and performs much better than the solution above, but still can improved.
	 */
	@Benchmark
	public int findSolitaireNumberWithMap(SolitaireExecutionPlan ep) {
		List<Integer> numbers = readIntegersWithScanner(ep.input);
		Map<Integer, Integer> occurrences = new HashMap<>();
		for (int number : numbers) {
			Integer occurrence = occurrences.get(number);
			if (occurrence != null) {
				occurrences.put(number, occurrence + 1);
			} else {
				occurrences.put(number, 1);
			}
		}
		for (int number : numbers) {
			Integer occurrence = occurrences.get(number);
			if (occurrence == 1) {
				return number;
			}
		}
		throw new IllegalArgumentException();
	}

	@Benchmark
	public int findSolitaireNumberUltimate(SolitaireExecutionPlan ep) {
		int solitaire = 0;
		int value = 0;
		boolean negative = false;
		for (int index = 0; index < ep.input.length(); index++) {
			int character = ep.input.charAt(index);
			if (character == '-') {
				negative = true;
			} else if (character == ' ') {
				solitaire ^= negative ? -value : value;
				value = 0;
				negative = false;
			} else {
				value = (value * 10) + (character - '0');
			}
		}
		solitaire ^= negative ? -value : value;
		return solitaire;
	}

	private List<Integer> readIntegersWithScanner(String input) {
		Scanner scanner = new Scanner(input);
		List<Integer> numbers = new ArrayList<>();
		while (scanner.hasNextInt()) {
			numbers.add(scanner.nextInt());
		}
		scanner.close();
		return numbers;
	}

}

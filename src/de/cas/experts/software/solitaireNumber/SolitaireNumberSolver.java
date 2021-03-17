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

@Warmup(iterations = 3, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1, warmups = 1)
@BenchmarkMode(Mode.AverageTime)
public class SolitaireNumberSolver {

	/**
	 * This is a lazy inefficient solution, but short code and easy to read. Because
	 * of linear time required by the frequency method this algorithm is quadratic.
	 *
	 * This method is just to illustrate a simple solution, it is so slow, that we do
	 * not even want to include it in the benchmarking
	 */
	public int findSolitaireNumber(SolitaireExecutionPlan ep) {
		List<Integer> numbers = readIntegersWithScanner(ep.input);
		for (int number : numbers) {
			if (frequency(numbers, number) == 1) {
				return number;
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * This is linear solution and performs much better than the solution above, but still can improved.
	 * Use this as your staring point. It can be made at least 40 times faster.
	 */
	@Benchmark
	public int findSolitaireNumberLinear(SolitaireExecutionPlan ep) {
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

package de.cas.experts.software;

public class Stopwatch {

	private long startTime;

	public Stopwatch() {
		reset();
	}

	public boolean elapsedSeconds(int seconds) {
		return elapsedMillis() > seconds * 1000;
	}

	public long elapsedMillis() {
		long now = System.currentTimeMillis();
		return now - startTime;
	}

	public void reset() {
		startTime = System.currentTimeMillis();
	}

	public void printElapsed() {
		System.out.println(String.format("elapsed %.3f", elapsedMillis() / 1000f));
	}

	public long elapsedTimeInMs() {
		long now = System.currentTimeMillis();
		return now - startTime;
	}
}

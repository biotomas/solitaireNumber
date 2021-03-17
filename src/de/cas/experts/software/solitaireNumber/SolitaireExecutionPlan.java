package de.cas.experts.software.solitaireNumber;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class SolitaireExecutionPlan {

	@Param({ "100000", "200000", "400000" })
	public int size;

	public String input;

	@Setup(Level.Trial)
	public void setUp() {
		input = SolitaireInputGenerator.generateInput(size);
	}

}

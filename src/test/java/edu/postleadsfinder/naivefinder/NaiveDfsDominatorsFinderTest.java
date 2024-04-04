package edu.postleadsfinder.naivefinder;

import edu.postleadsfinder.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;

public class NaiveDfsDominatorsFinderTest extends AbstractDominatorsFinderTest<DfsPayload> {

    private final AlgorithmHelper<DfsPayload> algorithmHelper = new NaiveDfsHelper();

    @Override
    protected AlgorithmHelper<DfsPayload> getFactory() {
        return algorithmHelper;
    }

    @Override
    @ParameterizedTest(name = "{0}, {1} -> {2}")
    @MethodSource("testCases")
    protected void doTest(AbstractDominatorsFinderTest.TestGraph testGraph, String startVertexKey, String exitVertexKey, List<String> expectedDominators) {
        // NB: intentionally expect failure there for the naive algorithm:
        if (testGraph.name().startsWith("bug_in_naive_algorithm")) {
            thenThrownBy(() -> super.doTest(testGraph, startVertexKey, exitVertexKey, expectedDominators))
                    .isInstanceOf(AssertionError.class);
        } else {
            super.doTest(testGraph, startVertexKey, exitVertexKey, expectedDominators);
        }
    }
}
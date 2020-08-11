package trigonometry;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Test class for the Tangent class.
 *
 * @author Maxi
 * @see Tangent
 */
public class TangentTest
{
    private static final double PI = Math.PI;
    private static final double SMALL_DECIMAL = 0.000000000000001;

    @BeforeAll
    static void setUp ()
    {
        /* TODO Use this in case you want to initialize a variable or object that will be used for all tests
            and not changed (constants, helper objects, etc.)
        */
    }

    /**
     * This method tests Tangents function for a range of inputs of some special angles: 0, 45, 90, 135, 180, 225,
     * 270, 315 and 360, received here with their equivalent values in RADIANS, and their correspondent negative values
     * (-360, -330, etc.), i.e., from -2*pi to 2*pi, which are the chosen limits for the Taylor expansion.
     * There is a helper method to provide the test values: {@link #radiansInputAndOutputProvider()}
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("radiansInputAndOutputProvider")
    void testTangentFunctionFromRadianInputs (double input, double expectedResult)
    {
        double tangentResult = Tangent.calculateTangentFromRadianInput(input);
        Supplier<String> messageSupplier = () -> String.format("Test failed for input %s, expected output %s. The actual result was %s", input, expectedResult, tangentResult);
        assertEquals(expectedResult, tangentResult, messageSupplier);
    }

    /**
     * This method tests the boundaries that are really close to the original input in radians, since there might be
     * some kind of imprecision when the input is actually calculated and used, and the function must guarantee that
     * the correct input is used, to generate the correct result in the end.
     * There is a helper method to provide the test values: {@link #boundaryValuesInputAndOutputProvider()}
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("boundaryValuesInputAndOutputProvider")
    void testTangentFunctionForBoundaryValues (double input, double expectedResult)
    {
        double tangentResult = Tangent.calculateTangentFromRadianInput(input);
        Supplier<String> messageSupplier = () -> String.format("Test failed for input %s, expected output %s. The actual result was %s", input, expectedResult, tangentResult);
        assertEquals(expectedResult, tangentResult, messageSupplier);
    }

    /**
     * This method tests tangent function for a range of inputs of some special angles: 0, 30, 90, 150, 180, 210,
     * 270, 330 and 360, and their correspondent negative values (-360, -330, etc.), i.e., from -2*pi to 2*pi,
     * which are the chosen limits for the Taylor expansion.
     * There is a helper method to provide the test values: {@link #degreesInputAndOutputProvider()}
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("degreesInputAndOutputProvider")
    void testTangentFunctionFromDegreesInputs (double input, double expectedResult)
    {
        double tangentResult = Tangent.calculateTangentFromDegreesInput(input);
        Supplier<String> messageSupplier = () -> String.format("Test failed for input %s, expected output %s. The actual result was %s", input, expectedResult, tangentResult);
        assertEquals(expectedResult, tangentResult, messageSupplier);
    }

    /**
     * This method tests the function when a the provided input is outside the Taylor expansion limits
     * of -2*pi and 2*pi (-2*pi <= input <= 2*pi).
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("inputLimitsProvider")
    void testLimitsForInput (double input, String message)
    {
        assertThrows(IllegalArgumentException.class, () -> Tangent.calculateTangentFromRadianInput(input));
    }

    /**
     * Helper method that provides all combinations of input/output
     * being tested by {@link #testTangentFunctionFromRadianInputs(double, double)}
     *
     * @return all combinations of input/output for the mentioned test class
     */
    static Stream<Arguments> radiansInputAndOutputProvider ()
    {
        return Stream.of(arguments(2 * Math.PI, 0), arguments(7 * Math.PI / 4, -1), arguments(3 * Math.PI / 2, NaN), arguments(5 * Math.PI / 4, 1), arguments(Math.PI, 0), arguments(3 * Math.PI / 4, -1), arguments(Math.PI / 2, NaN), arguments(Math.PI / 4, 1), arguments(0, 0), arguments(-Math.PI / 4, -1), arguments(-Math.PI / 2, NaN), arguments(-3 * Math.PI / 4, 1), arguments(-Math.PI, 0), arguments(-5 * Math.PI / 4, -1), arguments(-3 * Math.PI / 2, NaN), arguments(-7 * Math.PI / 4, 1), arguments(-2 * Math.PI, 0));
    }

    /**
     * Helper method that provides all combinations of input/output
     * being tested by {@link #testTangentFunctionFromDegreesInputs(double, double)}
     *
     * @return all combinations of input/output for the mentioned test class
     */
    static Stream<Arguments> degreesInputAndOutputProvider ()
    {
        return Stream.of(arguments(360, 0), arguments(315, -1), arguments(270, NaN), arguments(225, 1), arguments(180, 0), arguments(135, -1), arguments(90, NaN), arguments(45, 1), arguments(0, 0), arguments(-45, -1), arguments(-90, NaN), arguments(-135, 1), arguments(-180, 0), arguments(-225, -1), arguments(-270, NaN), arguments(-315, 1), arguments(-360, 0));
    }

    /**
     * Helper method that provides all combinations of input/output
     * being tested by {@link #testTangentFunctionForBoundaryValues(double, double)}
     *
     * @return all combinations of input/output for the mentioned test class
     */
    static Stream<Arguments> boundaryValuesInputAndOutputProvider ()
    {
        return Stream.of(arguments(2 * PI - SMALL_DECIMAL, 0), arguments(0 - SMALL_DECIMAL, 0), arguments(0 + SMALL_DECIMAL, 0), arguments(-2 * PI + SMALL_DECIMAL, 0));
    }

    /**
     * Helper method that provides all combinations of input/output
     * being tested by {@link #testLimitsForInput(double, String)}
     *
     * @return all combinations of input/output for the mentioned test class
     */
    static Stream<Arguments> inputLimitsProvider ()
    {
        return Stream.of(arguments(2 * PI + SMALL_DECIMAL, "IllegalArgumentException"), arguments(2 * PI + 1, "IllegalArgumentException"), arguments(-2 * PI - 1, "IllegalArgumentException"), arguments(-2 * PI - SMALL_DECIMAL, "IllegalArgumentException"));
    }

    @AfterAll
    static void tearDown ()
    {
        // TODO Use this in case you need to destroy something after tests were executed
    }

}

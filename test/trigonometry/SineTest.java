package trigonometry;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Test class for the Sine class.
 *
 * @author dlacher
 * @see Sine
 */
public class SineTest
{
    // PI
    private static final double PI = Math.PI;
    // Small decimal to test boder value
    private static final double SMALL_DECIMAL = 0.000000000000001;

    @BeforeAll
    static void setUp ()
    {
        /* TODO Use this in case you want to initialize a variable or object that will be used for all tests
            and not changed (constants, helper objects, etc.)
        */
    }

    /**
     * This method tests sine function for a range of inputs of some special angles: 0, 30, 90, 150, 180, 210,
     * 270, 330 and 360, received here with their equivalent values in RADIANS, and their correspondent negative values
     * (-360, -330, etc.), i.e., from -2*pi to 2*pi, which are the chosen limits for the Taylor expansion.
     * There is a helper method to provide the test values: {@link #radiansInputAndOutputProvider()}
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("radiansInputAndOutputProvider")
    void testSineFunctionFromRadianInputs (double input, double expectedResult)
    {
        double sineResult = Sine.calculateSineFromRadianInput(input);
        Supplier<String> messageSupplier = () -> String.format("Test failed for input %s, expected output %s. The actual result was %s", input, expectedResult, sineResult);
        assertEquals(expectedResult, sineResult, messageSupplier);
    }

    /**
     * This method tests the boundaries that are really close to the original input in radians, since there might be
     * some kind of imprecision when the input is actually calculated and used, and the function must guarantee that
     * the correct input is used, to generate the correct result in the end.
     * There is a helper method to provide the test values: {@link #boundaryValuesInputAndOutputProvider()}
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("boundaryValuesInputAndOutputProvider")
    void testSineFunctionForBoundaryValues (double input, double expectedResult)
    {
        double sineResult = Sine.calculateSineFromRadianInput(input);
        Supplier<String> messageSupplier = () -> String.format("Test failed for input %s, expected output %s. The actual result was %s", input, expectedResult, sineResult);
        assertEquals(expectedResult, sineResult, messageSupplier);
    }

    /**
     * This method tests sine function for a range of inputs of some special angles: 0, 30, 90, 150, 180, 210,
     * 270, 330 and 360, and their correspondent negative values (-360, -330, etc.), i.e., from -2*pi to 2*pi,
     * which are the chosen limits for the Taylor expansion.
     * There is a helper method to provide the test values: {@link #degreesInputAndOutputProvider()}
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("degreesInputAndOutputProvider")
    void testSineFunctionFromDegreesInputs (double input, double expectedResult)
    {
        double sineResult = Sine.calculateSineFromDegreesInput(input);
        Supplier<String> messageSupplier = () -> String.format("Test failed for input %s, expected output %s. The actual result was %s", input, expectedResult, sineResult);
        assertEquals(expectedResult, sineResult, messageSupplier);
    }

    /**
     * This method tests the function when a the provided input is outside the Taylor expansion limits
     * of -2*pi and 2*pi (-2*pi <= input <= 2*pi).
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("inputLimitsProvider")
    void testLimitsForInput (double input, String message)
    {
        assertThrows(IllegalArgumentException.class, () -> Sine.calculateSineFromRadianInput(input));
    }

    /**
     * Helper method that provides all combinations of input/output
     * being tested by {@link #testSineFunctionFromRadianInputs(double, double)}
     *
     * @return all combinations of input/output for the mentioned test class
     */
    static Stream<Arguments> radiansInputAndOutputProvider ()
    {
        return Stream.of(arguments(2 * Math.PI, 0), arguments(11 * Math.PI / 6, -0.5), arguments(3 * Math.PI / 2, -1), arguments(7 * Math.PI / 6, -0.5), arguments(Math.PI, 0), arguments(5 * Math.PI / 6, 0.5), arguments(Math.PI / 2, 1), arguments(Math.PI / 6, 0.5), arguments(0, 0), arguments(-Math.PI / 6, -0.5), arguments(-Math.PI / 2, -1), arguments(-5 * Math.PI / 6, -0.5), arguments(-Math.PI, 0), arguments(-7 * Math.PI / 6, 0.5), arguments(-3 * Math.PI / 2, 1), arguments(-11 * Math.PI / 6, 0.5), arguments(-2 * Math.PI, 0));
    }

    /**
     * Helper method that provides all combinations of input/output
     * being tested by {@link #testSineFunctionFromDegreesInputs(double, double)}
     *
     * @return all combinations of input/output for the mentioned test class
     */
    static Stream<Arguments> degreesInputAndOutputProvider ()
    {
        return Stream.of(arguments(360, 0), arguments(330, -0.5), arguments(270, -1), arguments(210, -0.5), arguments(180, 0), arguments(150, 0.5), arguments(90, 1), arguments(30, 0.5), arguments(0, 0), arguments(-30, -0.5), arguments(-90, -1), arguments(-150, -0.5), arguments(-180, 0), arguments(-210, 0.5), arguments(-270, 1), arguments(-330, 0.5), arguments(-360, 0));
    }

    /**
     * Helper method that provides all combinations of input/output
     * being tested by {@link #testSineFunctionForBoundaryValues(double, double)}
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

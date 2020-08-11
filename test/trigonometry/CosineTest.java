package trigonometry;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.DecimalFormat;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Test class for the Cosine class.
 *
 * @author Anric
 * @see Cosine
 */
public class CosineTest
{
    // Using DecimalFormat to limit the accuracy of out put.
    static DecimalFormat decimalFormat = new DecimalFormat("###.###########");
    // PI
    private static final double PI = Math.PI;
    // Small decimal to test border value
    private static final double SMALL_DECIMAL = 0.000000000000001;

    @BeforeAll
    static void setUp ()
    {
        /* TODO Use this in case you want to initialize a variable or object that will be used for all tests
            and not changed (constants, helper objects, etc.)
        */
    }

    /**
     * This method tests cosine function for a range of inputs of some special angles: 0, 30, 90, 150, 180, 210,
     * 270, 330 and 360, received here with their equivalent values in RADIANS, and their correspondent negative values
     * (-360, -330, etc.), i.e., from -2*pi to 2*pi, which are the chosen limits for the Taylor expansion.
     * There is a helper method to provide the test values: {@link #radiansInputAndOutputProvider()}
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("radiansInputAndOutputProvider")
    void testCosineFunctionFromRadianInputs (double input, double expectedResult)
    {
        double cosineResult = Cosine.calculateCosineFromRadianInput(input);
        Supplier<String> messageSupplier = () -> String.format("Test failed for input %s, expected output %s. The actual result was %s", input, expectedResult, cosineResult);
        assertEquals(expectedResult, cosineResult, messageSupplier);
    }

    /**
     * This method tests the boundaries that are really close to the original input in radians, since there might be
     * some kind of imprecision when the input is actually calculated and used, and the function must guarantee that
     * the correct input is used, to generate the correct result in the end.
     * There is a helper method to provide the test values: {@link #boundaryValuesInputAndOutputProvider()}
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("boundaryValuesInputAndOutputProvider")
    void testCosineFunctionForBoundaryValues (double input, double expectedResult)
    {
        double cosineResult = Cosine.calculateCosineFromRadianInput(input);
        Supplier<String> messageSupplier = () -> String.format("Test failed for input %s, expected output %s. The actual result was %s", input, expectedResult, cosineResult);
        assertEquals(expectedResult, cosineResult, messageSupplier);
    }

    /**
     * This method tests cosine function for a range of inputs of some special angles: 0, 30, 90, 150, 180, 210,
     * 270, 330 and 360, and their correspondent negative values (-360, -330, etc.), i.e., from -2*pi to 2*pi,
     * which are the chosen limits for the Taylor expansion.
     * There is a helper method to provide the test values: {@link #degreesInputAndOutputProvider()}
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("degreesInputAndOutputProvider")
    void testCosineFunctionFromDegreesInputs (double input, double expectedResult)
    {
        double cosineResult = Cosine.calculateCosineFromDegreesInput(input);
        Supplier<String> messageSupplier = () -> String.format("Test failed for input %s, expected output %s. The actual result was %s", input, expectedResult, cosineResult);
        assertEquals(expectedResult, cosineResult, messageSupplier);
    }

    /**
     * This method tests the function when a the provided input is outside the Taylor expansion limits
     * of -2*pi and 2*pi (-2*pi <= input <= 2*pi).
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("inputLimitsProvider")
    void testLimitsForInput (double input, String message)
    {
        assertThrows(IllegalArgumentException.class, () -> Cosine.calculateCosineFromRadianInput(input));
    }

    /**
     * Helper method that provides all combinations of input/output
     * being tested by
     *
     * @return all combinations of input/output for the mentioned test class
     */
    static Stream<Arguments> radiansInputAndOutputProvider ()
    {
        double radianVal[] = {2 * Math.PI, 5 * Math.PI / 3, 3 * Math.PI / 2, 4 * Math.PI / 3, Math.PI, 2 * Math.PI / 3, Math.PI / 2, Math.PI / 3, 0, -Math.PI / 3, -Math.PI / 2, -2 * Math.PI / 3, -Math.PI, -4 * Math.PI / 3, -3 * Math.PI / 2, -5 * Math.PI / 3, -2 * Math.PI};

        // Initialize Arguments list for input
        Arguments[] arg = new Arguments[radianVal.length];
        for (int i = 0; i < radianVal.length; i++)
        {
            // put corespondent value into each argument
            arg[i] = arguments(radianVal[i], actualCosineValRadianCal(radianVal[i]));
        }
        // return the stream of arguments
        return Stream.of(arg);
    }

    /**
     * Helper method that provides all combinations of input/output
     * being tested by {@link #testCosineFunctionFromDegreesInputs(double, double)}
     *
     * @return all combinations of input/output for the mentioned test class
     */
    static Stream<Arguments> degreesInputAndOutputProvider ()
    {
        double[] degreeVal = {360, 300, 270, 240, 180, 120, 90, 60, 0, -60, -90, -120, -180, -240, -270, -300, -360};

        // Initialize Arguments list for input
        Arguments[] arg = new Arguments[degreeVal.length];
        for (int i = 0; i < degreeVal.length; i++)
        {
            // put corespondent value into each argument
            arg[i] = arguments(degreeVal[i], actualCosineValDegreeCal(degreeVal[i]));
        }
        // return the stream of arguments
        return Stream.of(arg);
    }

    /**
     * Helper method that provides all combinations of input/output
     * being tested by
     *
     * @return all combinations of input/output for the mentioned test class
     */
    static Stream<Arguments> boundaryValuesInputAndOutputProvider ()
    {
        return Stream.of(arguments(2 * Math.PI - SMALL_DECIMAL, 1), arguments(0 - SMALL_DECIMAL, 1), arguments(0 + SMALL_DECIMAL, 1), arguments(-2 * Math.PI + SMALL_DECIMAL, 1));
    }

    /**
     * Helper method that provides all combinations of input/output
     * being tested by {@link #testLimitsForInput(double, String)}
     *
     * @return all combinations of input/output for the mentioned test class
     */
    static Stream<Arguments> inputLimitsProvider ()
    {
        return Stream.of(arguments(2 * Math.PI + SMALL_DECIMAL, "IllegalArgumentException"), arguments(2 * Math.PI + 1, "IllegalArgumentException"), arguments(-2 * Math.PI - 1, "IllegalArgumentException"), arguments(-2 * Math.PI - SMALL_DECIMAL, "IllegalArgumentException"));
    }

    @AfterAll
    static void tearDown ()
    {
        // TODO Use this in case you need to destroy something after tests were executed
    }

    /**
     * Actual Value Computation Function for getting actual value of Cosine for Degree Value
     *
     * @param inDegree input value in degree unit
     * @return
     */
    public static double actualCosineValDegreeCal (double inDegree)
    {
        double returnValue = Double.parseDouble(decimalFormat.format(Math.cos(inDegree * Math.PI / 180)));
        // if the value equal to -0, return 0;
        if (returnValue == -0.00)
        {
            return 0.00;
        }
        else
        {
            return returnValue;
        }
    }

    /**
     * Actual Value Computation Function for getting actual value of Cosine for Radian Value
     *
     * @param inRadian input value in radian unit
     * @return
     */
    public static double actualCosineValRadianCal (double inRadian)
    {
        double returnValue = Double.parseDouble(decimalFormat.format(Math.cos((inRadian))));
        // if the value equal to -0, return 0;
        if (returnValue == -0.00)
        {
            return 0.00;
        }
        else
        {
            return returnValue;
        }
    }
}
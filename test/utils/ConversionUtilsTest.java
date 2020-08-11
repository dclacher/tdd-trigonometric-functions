package utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.DecimalFormat;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Test class for the ConversionUtils class
 *
 * @author dlacher
 * @see ConversionUtils
 */
public class ConversionUtilsTest
{
    // Using DecimalFormat to Control the accuracy of the output (keeping 17 digit).
    static DecimalFormat decimalFormat = new DecimalFormat("#.#################");

    /**
     * This method tests the function that converts the input from radians to degrees
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("conversionTestDataProvider")
    void testConvertRadiansToDegrees (double input, double expectedResult)
    {
        double inDegrees = ConversionUtils.convertRadiansToDegrees(input);
        Supplier<String> messageSupplier = () -> String.format("Test failed for input %s, expected output %s. The actual result was %s", input, expectedResult, inDegrees);
        assertEquals(expectedResult, inDegrees, messageSupplier);
    }

    /**
     * This method tests the function that converts the input from degrees to radians
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("conversionTestDataProvider")
    void testConvertDegreesToRadians (double expectedResult, double input)
    {
        double inRadians = ConversionUtils.convertDegreesToRadians(input);
        Supplier<String> messageSupplier = () -> String.format("Test failed for input %s, expected output %s. The actual result was %s", input, expectedResult, inRadians);
        assertEquals(expectedResult, inRadians, messageSupplier);
    }

    /**
     * Helper method that provides all combinations of input/output being tested by
     * {@link #testConvertRadiansToDegrees(double, double)} and {@link #testConvertDegreesToRadians(double, double)}
     *
     * @return all combinations of input/output for the mentioned test methods
     */
    static Stream<Arguments> conversionTestDataProvider ()
    {

        return Stream.of(arguments((Double.parseDouble(decimalFormat.format(Math.PI))), 180), arguments((Double.parseDouble(decimalFormat.format(Math.PI / 6))), 30), arguments(0, 0));
    }
}

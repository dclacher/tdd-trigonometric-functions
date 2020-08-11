package trigonometry;

import java.lang.Math;
import java.text.DecimalFormat;

import static java.lang.Double.NaN;
import static trigonometry.Cosine.calculateCosineFromRadianInput;
import static trigonometry.Sine.calculateSineFromRadianInput;


/**
 * @author Maxi Agrippa
 * Utility class that provides methods to calculate the trigonometric tangent function for radians and degrees input.
 * It also provides functions to convert between both types of values (radians and degrees).
 */
public class Tangent
{
    // Using DecimalFormat to Control the accuracy of the output (keeping 11 digit).
    static DecimalFormat decimalFormat = new DecimalFormat("#.###########");
    // PI
    public static final double PI = 3.14159265358979323846;


    /**
     * This method calculates the tangent function from a radian input. The tangent function is based on the
     * Taylor expansion series, with limits from -2*pi to 2*pi.
     *
     * @param x the radian input
     * @return the value of tangent for that input
     */
    public static double calculateTangentFromRadianInput (double x)
    {
        // Validate -2pi to 2pi
        if (x > 2 * PI || x < -2 * PI)
        {
            throw new IllegalArgumentException();
        }
        double Denominator = calculateCosineFromRadianInput(x);
        // is Denominator equal to 0?
        if (Denominator == 0)
        {
            // return Not a Number as infinity
            return NaN;
        }
        // when Denominator is not 0.
        double result = calculateSineFromRadianInput(x) / calculateCosineFromRadianInput(x);
        double formatedValue = (Double.parseDouble(decimalFormat.format(result)));
        if (formatedValue == -0)
        {
            return 0;
        }
        return formatedValue;
    }

    /**
     * This method calculates the tangent function from a degree input. The tangent function is based on the
     * Taylor expansion series, with limits from -2*pi to 2*pi.
     *
     * @param inDegrees the degree input
     * @return the value of tangent for that input
     */
    public static double calculateTangentFromDegreesInput (double inDegrees)
    {
        // Validate -360 to 360
        double inRadian = (inDegrees * (Math.PI / 180.0));
        return calculateTangentFromRadianInput(inRadian);
    }
}

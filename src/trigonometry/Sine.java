package trigonometry;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Utility class that provides methods to calculate the trigonometric sine function for radians and degrees input.
 * It also provides functions to convert between both types of values (radians and degrees).
 *
 * @author Maxi
 */
public class Sine
{
    // Using DecimalFormat to Control the accuracy of the output (keeping 11 digit).
    static DecimalFormat decimalFormat = new DecimalFormat("#.###########");
    // PI
    public static final double PI = 3.14159265358979323846;


    /**
     * This method calculates the sine function from a radian input. The sine function is based on the
     * Taylor expansion series, with limits from -2*pi to 2*pi.
     *
     * @param x the radian input
     * @return the value of sine for that input
     */
    public static double calculateSineFromRadianInput (double x)
    {
        if (x > 2 * PI || x < -2 * PI)
        {
            throw new IllegalArgumentException();
        }
        // contain radian
        double radians = x;
        // contain result
        double result = 0;
        // contain Factorial number
        double factorial = 1;
        // contain powered number
        double powered = radians;
        // control the sign of the number
        double sign = 1;
        // contain partial results, x, -(x^3)/3!, -(x^5)/5!, etc.
        ArrayList<Double> results = new ArrayList<Double>();
        // temp to calculate each partial result
        double temp = radians;
        // put x into partial results
        results.add(temp);
        // calculate each partial results.
        for (int i = 1; i < 17; i++)
        {
            // update power result
            powered *= radians * radians;
            // update factorial number
            factorial *= ((2 * i) * (2 * i + 1));
            // change sign
            sign *= -1;
            // calculate each term in the equation
            temp = sign * powered / factorial;
            // store partial results.
            results.add(temp);
        }
        // add those partial results together to get the real results.
        for (Double b : results)
        {
            result = result + b;
        }
        double formatedValue = (Double.parseDouble(decimalFormat.format(result)));
        if (formatedValue == -0)
        {
            return 0;
        }
        return formatedValue;
    }

    /**
     * This method calculates the sine function from a degree input. The sine function is based on the
     * Taylor expansion series, with limits from -2*pi to 2*pi.
     *
     * @param inDegrees the degree input
     * @return the value of sine for that input
     */
    public static double calculateSineFromDegreesInput (double inDegrees)
    {
        // Validate -360 to 360
        double inRadian = (inDegrees * (Math.PI / 180.0));
        return calculateSineFromRadianInput(inRadian);
    }
}

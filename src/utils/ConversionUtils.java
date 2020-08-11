package utils;

import java.text.DecimalFormat;

/**
 * Utility class that provides methods to convert between both types of values used in Sine, Cosine and
 * Tangent functions (radians and degrees).
 *
 * @author dlacher
 */
public class ConversionUtils
{
    // Using DecimalFormat to Control the accuracy of the output (keeping 17 digit).
    static DecimalFormat decimalFormat = new DecimalFormat("#.#################");

    /**
     * Method that converts a value in radians to the equivalent value in degrees
     *
     * @param inRadians the value in radians to be converted to degrees
     * @return the value in degrees
     */
    public static double convertRadiansToDegrees (double inRadians)
    {
        double result = inRadians / Math.PI * 180;
        double formatedValue = (Double.parseDouble(decimalFormat.format(result)));
        return formatedValue;
    }

    /**
     * Method that converts a value in degrees to the equivalent value in radians
     *
     * @param inDegrees the value in degrees to be converted to radians
     * @return the value in radians
     */
    public static double convertDegreesToRadians (double inDegrees)
    {
        double result = inDegrees * Math.PI / 180;
        double formatedValue = (Double.parseDouble(decimalFormat.format(result)));
        return formatedValue;
    }
}

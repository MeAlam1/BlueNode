// Copyright (c) BlueNode. Licensed under the MIT License.

package com.mealam.bluenode.utils.conversion;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("unused")
public class MathUtils {

    private MathUtils() {}

    public static double inchesToCentimeters(double pInches) {
        return pInches * 2.54;
    }

    public static double centimetersToInches(double pCentimeters) {
        return pCentimeters / 2.54;
    }

    public static double celsiusToFahrenheit(double pCelsius) {
        return pCelsius * 9 / 5 + 32;
    }

    public static double fahrenheitToCelsius(double pFahrenheit) {
        return (pFahrenheit - 32) * 5 / 9;
    }

    public static double kilometersToMiles(double pKilometers) {
        return pKilometers * 0.621371;
    }

    public static double milesToKilometers(double pMiles) {
        return pMiles / 0.621371;
    }

    public static Date stringToDate(String pDateStr, String pFormat) throws ParseException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pFormat);
            return formatter.parse(pDateStr);
        } catch (ParseException pException) {
            BaseLogger.log(BaseLogLevel.ERROR, "Error parsing date string: " + pDateStr + " with format: " + pFormat, pException);
            throw pException;
        }
    }

    public static String dateToString(Date pDate, String pFormat) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pFormat);
            return formatter.format(pDate);
        } catch (Exception pException) {
            BaseLogger.log(BaseLogLevel.ERROR, "Error formatting date: " + pDate.toString() + " with format: " + pFormat, pException);
            return pException.getMessage();
        }
    }
}

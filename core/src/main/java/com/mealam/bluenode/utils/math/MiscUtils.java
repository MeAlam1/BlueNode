// Copyright (c) BlueNode. Licensed under the MIT License.

package com.mealam.bluenode.utils.math;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

@SuppressWarnings("unused")
public class MiscUtils {

    private MiscUtils() {}

    public static boolean isValidEmail(String pEmail) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return pEmail != null && pEmail.matches(emailRegex);
    }

    public static int stringToIntWithDefault(String pString, int pDefaultValue) {
        try {
            return Integer.parseInt(pString);
        } catch (NumberFormatException pException) {
            BaseLogger.log(BaseLogLevel.ERROR, "Error converting string to integer", pException);
            return pDefaultValue;
        }
    }

    public static int calculateLevenshteinDistance(String pStr1, String pStr2) {
        int[][] dp = new int[pStr1.length() + 1][pStr2.length() + 1];
        for (int i = 0; i <= pStr1.length(); i++) {
            for (int j = 0; j <= pStr2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (pStr1.charAt(i - 1) == pStr2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + cost);
                }
            }
        }
        return dp[pStr1.length()][pStr2.length()];
    }
}

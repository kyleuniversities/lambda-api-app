package com.lambda.lambda.common.helper.number;

import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.string.StringHelper;

/**
 * Helper class for String Operations
 */
public final class IntegerHelper {
    /**
     * Returns the absolute value of an integer
     */
    public static int absoluteValue(int value) {
        return ConditionalHelper.ifReturnElse(value < 0, -value, value);
    }

    /**
     * Operation for greater than
     */
    public static boolean greaterThan(int a, int b) {
        return a > b;
    }

    /**
     * Gets the length of an integer
     */
    public static int length(int value) {
        return StringHelper.toString(value).length();
    }

    /**
     * Operation for less than
     */
    public static boolean lessThan(int a, int b) {
        return a < b;
    }

    /**
     * Returns the max of two numbers
     */
    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * Returns the min of two numbers
     */
    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private IntegerHelper() {
        super();
    }
}

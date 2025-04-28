package Calculations;

/**
 * A class of methods that compare double in a more accurate way
 */
public class DoubleComparing {
    final static double EPSILON = 0.000001;

    /**
     * Returns true if arguments are equal, otherwise false.
     *
     * @param a the first number
     * @param b the second number.
     * @return true if arguments are equal, otherwise false.
     */
    public boolean isAbsEqual(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    /**
     * Returns true if arguments are equal or if both are null, otherwise false.
     *
     * @param a the first number
     * @param b the second number.
     * @return true if arguments are equal, otherwise false.
     */
    public boolean isNullOrAbsEqual(Double a, Double b) {
        if (a == null || b == null){
            return a == null && b == null;
        }
        else {
             return isAbsEqual(a, b);
        }
    }

    /**
     * Returns true if the 1st number is bigger than the 2nd number, otherwise false.
     *
     * @param a the first number
     * @param b the second number.
     * @return true if the 1st number is bigger than the 2nd number, otherwise false.
     */
    public boolean isBigger(double a, double b) {
        return a - b > EPSILON;
    }

    /**
     * Returns true if the string argument is a number, otherwise returns false.
     *
     * @param s the string to check if it's a number.
     * @return true if the string argument is a number, otherwise returns false.
     */
    public boolean isDouble(String s){
        boolean dotUsed = false;
        for (int i = 0; i < s.length(); i++){
            char l = s.charAt(i);
            if (!(Character.isDigit(l))) {
                if ((i == 0 && l == '-')) {
                    continue;
                }
                if (!dotUsed && l == '.' && i != 0) {
                    dotUsed = true;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * If the string is a number - returns the number, else returns 0.
     *
     * @param s the string to change to a double.
     * @return If the string is a number - returns the number, else returns 0.
     */
    public double toDouble(String s) {
        if (isDouble(s)){
            return Double.parseDouble(s);
        }
        else
            return 0;
    }
}

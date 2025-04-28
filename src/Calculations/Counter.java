package Calculations;

/**
 * A class that represents a counter.
 */
public class Counter {
    private int number;

    /**
     * A builder of a counter with default amount (zero).
     */
    public Counter(){
        this.number = 0;
    }

    /**
     * A builder of a counter with inputted amount.
     */
    public Counter(int number) {
        this.number = number;
    }

    /**
     * Method adds number to current count.
     *
     * @param number the number add to current amount.
     */
    public void increase(int number) {
        this.number+= number;
    }

    /**
     * Method subtracts number from current count.
     *
     * @param number the number subtracts from current amount.
     */
    public void decrease(int number) {
        this.number-= number;
    }

    /**
     * Returns current amount counted.
     *
     * @return the current amount.
     */
    public int getValue() {
        return this.number;
    }


    /**
     * Method returns a String value of our count.
     *
     * @return a String value of our count.
     */
    public String toString() {
        return Integer.toString(number);
    }
}

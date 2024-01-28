package utils;

import java.util.Date;

public class FibonacciByDay {

    public int getFibonacciByDay() {
        return getFiboByIndex(getDayOfMonth());
    }

    public int getFiboByIndex(int index) {
        int[] arr = new int[index + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[index];
    }

    public int getDayOfMonth() {
        Date date = new Date();
        return Integer.parseInt(date.toString().substring(8, 10));
    }
}

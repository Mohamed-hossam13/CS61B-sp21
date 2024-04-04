
/** A program to find the max value in an integer array */
public class Exercise_2 {

    public static int whileMax(int [] m) {
        int SIZE = m.length;
        int max = m[0]; //Assume that the first value is the maximum value in the array

        int idx = 1;
        while (idx < SIZE) {
            if (max < m[idx])
                max = m[idx];

            idx += 1;
        }
        return max;
    }


    public static void main(String[] args){
        int [] numbers = new int[] {9, 2, 15, 2, 22, 10, 6};
        int maxValue = whileMax(numbers);

        System.out.println(maxValue);
    }
}

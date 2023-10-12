package edu.hw01;

public class Task02 {
    private Task02(){}

    public static int countDigits(long number){
        int count  = 0;

        do{
            count++;
            number /= 10;
        }while (number != 0);

        return count;
    }
}

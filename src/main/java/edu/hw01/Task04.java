package edu.hw01;

public class Task04 {
    private Task04(){}

    public static String fixString(String str){
        StringBuilder fixStr = new StringBuilder();
        int len = str.length();
        char[] chars = str.toCharArray();

        for (int i = 0; i < len / 2; i++) {
            fixStr.append(chars[i * 2 + 1])
                .append(chars[i * 2]);
        }
        if (len % 2 == 1) fixStr.append(chars[len - 1]);

        return  fixStr.toString();
    }
}

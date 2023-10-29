package edu.hw03;

import java.util.ArrayList;
import java.util.Stack;

public class Task02 {
    public static String[] clusterize(String s) {
        ArrayList<String> ans = new ArrayList<String>();
        Stack<Integer> stack = new Stack<Integer>();
        char c;
        int r = -1;

        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else if (c == ')' && !stack.isEmpty()) {
                r = stack.pop();
            }

            if (stack.isEmpty() && r != -1) {
                ans.add(s.substring(r, i + 1));
            }
        }

        return ans.toArray(new String[0]);
    }
}

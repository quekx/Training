package com.qkx.example.solutions.medium.No6x_7x;

import java.util.Stack;

/**
 * Created by qkx on 16/5/26.
 */
public class No71 {
    public static String simplifyPath(String path) {

        Stack<String> stack = new Stack<>();

        String[] ss = path.split("/");

        for (int i = 0; i < ss.length; i++) {
            String si = ss[i];
            if (si.length() != 0) {
                if (si.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (!si.equals(".")){
                    stack.push(si);
                }
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            builder.append('/');
            builder.append(stack.get(i));
        }

        return builder.toString();
    }
}

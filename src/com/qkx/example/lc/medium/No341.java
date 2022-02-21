package com.qkx.example.lc.medium;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by qkx on 17/6/23.
 */
public class No341 {
    public class NestedIterator implements Iterator<Integer> {

        private Stack<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                NestedInteger nestedInteger = stack.peek();
                if (nestedInteger.isInteger()) return true;

                stack.pop();
                List<NestedInteger> list = nestedInteger.getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.push(list.get(i));
                }
            }

            return false;
        }
    }

    private interface NestedInteger {
        boolean isInteger();
        Integer getInteger();
        List<NestedInteger> getList();
    }
}

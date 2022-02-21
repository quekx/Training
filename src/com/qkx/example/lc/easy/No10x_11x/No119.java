package com.qkx.example.lc.easy.No10x_11x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkx on 16/9/20.
 */
public class No119 {
    public List<Integer> getRow(int rowIndex) {

        List<Integer> res = new ArrayList<>(rowIndex + 1);
        if (rowIndex == 0) {
            res.add(1);
            return res;
        }

        int[] nums = new int[rowIndex + 1];
        int[] temp = new int[rowIndex + 1];
        nums[0] = 1;
        temp[0] = 1;

        for (int row = 1; row <= rowIndex; row++) {
            for (int i = 1; i <= row - 1; i++) {
                temp[i] = nums[i - 1] + nums[i];
            }
            temp[row] = 1;
            int[] t = temp;
            temp = nums;
            nums = t;
        }

        for (int i = 0; i < nums.length; i++) {
            res.add(nums[i]);
        }
        return res;
    }
}

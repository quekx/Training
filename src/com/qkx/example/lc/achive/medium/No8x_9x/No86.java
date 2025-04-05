package com.qkx.example.lc.achive.medium.No8x_9x;

import com.qkx.example.model.ListNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/5/28.
 */
public class No86 {
    public ListNode partition(ListNode head, int x) {

        List<Integer> less = new LinkedList<>();
        List<Integer> more = new LinkedList<>();

        ListNode i = head;
        while (i != null) {
            if (i.val < x) {
                less.add(i.val);
            } else {
                more.add(i.val);
            }
            i = i.next;
        }

        i = head;
        for (int num : less) {
            i.val = num;
            i = i.next;
        }
        for (int num : more) {
            i.val = num;
            i = i.next;
        }

        return head;
    }
}

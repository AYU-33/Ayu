package com.nowcoder.community;/*
    @author AYU
    */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.smallestNumber("DDD");
    }
    public String smallestNumber(String pattern) {
        StringBuilder sb = new StringBuilder();
        TreeSet<String> list = new TreeSet<String>();
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            sb.append(String.valueOf(i));
            set.add(i);
            dfs(pattern, set, 1, sb, list);
            set.remove(i);
            sb.delete(sb.length()- 1, sb.length());
        }
        return list.first();
    }
    public void dfs(String pattern, Set<Integer> set, int index, StringBuilder sb, TreeSet<String> list){
        if (index == pattern.length() + 1) {
            list.add(new String(sb));
            return;
        }
        if (pattern.charAt(index - 1) == 'I') {
            int t = Integer.parseInt(sb.charAt(sb.length() - 1) + "");
            for (int i = t + 1; i <= 9; i++){
                if (set.contains(i)) {continue;}
                sb.append(String.valueOf(i));
                set.add(i);
                dfs(pattern, set, index + 1, sb, list);
                set.remove(i);
                sb.delete(sb.length()- 1, sb.length());
            }
        }else {
            int t = Integer.parseInt(sb.charAt(sb.length() - 1) + "");
            for (int i = t - 1; i >= 1; i--){
                if (set.contains(i)) {continue;}
                sb.append(String.valueOf(i));
                set.add(i);
                dfs(pattern, set, index + 1, sb, list);
                set.remove(i);
                sb.delete(sb.length()- 1, sb.length());
            }
        }
    }
}

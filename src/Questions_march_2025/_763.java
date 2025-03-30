package Questions_march_2025;

import java.util.*;

public class _763 {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        PartitionLabelsSolution solution = new PartitionLabelsSolution();
        System.out.println(solution.partitionLabels(s));
    }
}

class PartitionLabelsSolution {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> count = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            count.put(s.charAt(i), i);
        }

        List<Integer> result = new ArrayList<>();
        int l = 0, r = 0;

        for (int i = 0; i < s.length(); i++) {
            r = Math.max(r, count.get(s.charAt(i)));
            if (i == r) {
                result.add(r - l + 1);
                l = i + 1;
            }
        }
        return result;
    }
}


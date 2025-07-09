package Questions_july_2025;

import java.util.*;

public class _3439 {
    public static void main(String[] args) {
        int eventTime = 24;
        int k = 2;
        int[] startTime = {2, 6, 10};
        int[] endTime   = {4, 8, 12};

        FreeTimeCalculator3439 calculator = new FreeTimeCalculator3439();
        int maxGap = calculator.maxFreeTime(eventTime, k, startTime, endTime);
        System.out.println("Maximum free time: " + maxGap);
    }
    static class FreeTimeCalculator3439 {
        public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
            int n = startTime.length;

            List<Integer> gaps = new ArrayList<>();
            gaps.add(startTime[0]);
            for (int i = 1; i < n; i++) {
                gaps.add(startTime[i] - endTime[i - 1]);
            }
            gaps.add(eventTime - endTime[n - 1]);

            int currentSum = 0, res = 0;
            for (int i = 0; i < gaps.size(); ++i) {
                currentSum += gaps.get(i);
                if (i >= k + 1) {
                    currentSum -= gaps.get(i - (k + 1));
                }
                res = Math.max(res, currentSum);
            }

            return res;
        }
    }
}

package Questions_july_2025;

public class _3440 {
    public static void main(String[] args) {
        int eventTime = 10;
        int[] startTime = {2, 5, 9};
        int[] endTime = {3, 7, 11};

        SolutionInner3440 solver = new SolutionInner3440();
        int maxFree = solver.maxFreeTime(eventTime, startTime, endTime);
        System.out.println("Maximum Free Time: " + maxFree);
    }

    static class SolutionInner3440 {
        public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
            int[] gap = new int[startTime.length + 1];
            gap[0] = startTime[0];
            for (int i = 1; i < startTime.length; i++) {
                gap[i] = startTime[i] - endTime[i - 1];
            }
            gap[startTime.length] = eventTime - endTime[endTime.length - 1];

            int[] largestRight = new int[gap.length];
            for (int i = gap.length - 2; i >= 0; i--) {
                largestRight[i] = Math.max(largestRight[i + 1], gap[i + 1]);
            }

            int ans = 0, largestLeft = 0;
            for (int i = 1; i < gap.length; i++) {
                int curGap = endTime[i - 1] - startTime[i - 1];
                if (curGap <= Math.max(largestLeft, largestRight[i])) {
                    ans = Math.max(ans, gap[i - 1] + gap[i] + curGap);
                }
                ans = Math.max(ans, gap[i - 1] + gap[i]);
                largestLeft = Math.max(largestLeft, gap[i - 1]);
            }
            return ans;
        }
    }
}

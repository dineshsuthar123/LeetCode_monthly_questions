package Questions_july_2025;

import java.util.*;

public class _2402 {
    public static void main(String[] args) {
        int n = 3;
        int[][] meetings = {{1,2},{1,2},{1,2}};

        SolutionInner2402 solver = new SolutionInner2402();
        int busiest = solver.mostBooked(n, meetings);
        System.out.println("Most booked room: " + busiest);
    }

    static class SolutionInner2402 {
        public int mostBooked(int n, int[][] meetings) {
            int[] ans = new int[n];
            long[] times = new long[n];
            Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

            for (int[] meeting : meetings) {
                int start = meeting[0], end = meeting[1];
                boolean flag = false;
                int minind = -1;
                long val = Long.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (times[j] < val) {
                        val = times[j];
                        minind = j;
                    }
                    if (times[j] <= start) {
                        flag = true;
                        ans[j]++;
                        times[j] = end;
                        break;
                    }
                }
                if (!flag) {
                    ans[minind]++;
                    times[minind] += (end - start);
                }
            }

            int maxi = -1, id = -1;
            for (int i = 0; i < n; i++) {
                if (ans[i] > maxi) {
                    maxi = ans[i];
                    id = i;
                }
            }
            return id;
        }
    }
}

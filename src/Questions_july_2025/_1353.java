package Questions_july_2025;

import java.util.*;

public class _1353 {

    static class EventScheduler {
        public int maxEvents(int[][] events) {
            Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                    (a, b) -> Integer.compare(a[1], b[1])
            );

            int res = 0;
            int day = 0;
            int i = 0;
            int n = events.length;

            while (i < n || !pq.isEmpty()) {
                if (pq.isEmpty()) {
                    day = events[i][0];
                }
                while (i < n && events[i][0] <= day) {
                    pq.offer(events[i]);
                    i++;
                }
                pq.poll();
                res++;
                day++;
                while (!pq.isEmpty() && pq.peek()[1] < day) {
                    pq.poll();
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[][] events = {
                {1, 2},
                {2, 3},
                {3, 4},
                {1, 2}
        };

        EventScheduler scheduler = new EventScheduler();
        int result = scheduler.maxEvents(events);
        System.out.println("Maximum number of events attended: " + result);
    }
}

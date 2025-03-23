package Questions_march_2025;

import java.util.*;

public class _1976 {
    public static void main(String[] args) {
        int n = 7;
        int[][] roads = {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {3, 5, 1}, {3, 4, 5}, {5, 4, 1}, {2, 5, 1}, {4, 6, 2}, {5, 6, 2}};

        CountPathsDijkstra solution1 = new CountPathsDijkstra();
        System.out.println(solution1.countPaths(n, roads));

        CountPathsFloydWarshall solution2 = new CountPathsFloydWarshall();
        System.out.println(solution2.countPaths(n, roads));
    }
}

class CountPathsDijkstra {
    public int countPaths(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0], v = road[1], time = road[2];
            graph.get(u).add(new int[]{v, time});
            graph.get(v).add(new int[]{u, time});
        }

        long[] distance = new long[n];
        int[] ways = new int[n];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[0] = 0;
        ways[0] = 1;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0});

        int mod = 1_000_000_007;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int node = (int) curr[1];

            if (d > distance[node]) continue;

            for (int[] neighbor : graph.get(node)) {
                int next = neighbor[0];
                int time = neighbor[1];

                if (distance[node] + time < distance[next]) {
                    distance[next] = distance[node] + time;
                    ways[next] = ways[node];
                    pq.offer(new long[]{distance[next], next});
                } else if (distance[node] + time == distance[next]) {
                    ways[next] = (ways[next] + ways[node]) % mod;
                }
            }
        }

        return ways[n - 1];
    }
}

class CountPathsFloydWarshall {
    long inf = Long.MAX_VALUE / 2;
    int mod = 1_000_000_007;

    public int countPaths(int n, int[][] roads) {
        long[][] graph = new long[n][n];
        long[] dist = new long[n];
        long[] count = new long[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], inf);
        }
        Arrays.fill(dist, inf);

        for (int[] r : roads) {
            int u = r[0], v = r[1], time = r[2];
            graph[u][v] = time;
            graph[v][u] = time;
        }

        graph[0][0] = 0;
        dist[0] = 0;
        count[0] = 1;

        for (int i = 0; i < n; i++) {
            int cur = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (cur == -1 || dist[j] < dist[cur])) {
                    cur = j;
                }
            }
            visited[cur] = true;
            for (int j = 0; j < n; j++) {
                if (j == cur) {
                    continue;
                }

                long newDist = dist[cur] + graph[cur][j];
                if (dist[j] > newDist) {
                    dist[j] = newDist;
                    count[j] = count[cur];
                } else if (dist[j] == newDist) {
                    count[j] += count[cur];
                    count[j] %= mod;
                }
            }
        }
        return (int) count[n - 1];
    }
}


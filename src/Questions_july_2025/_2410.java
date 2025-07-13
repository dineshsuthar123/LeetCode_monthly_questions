package Questions_july_2025;

import java.util.Arrays;
import java.util.Scanner;

public class _2410 {
    static class MatchSolver {
        public int matchPlayersAndTrainers(int[] players, int[] trainers) {
            Arrays.sort(players);
            Arrays.sort(trainers);
            int i = 0, j = 0, matches = 0;
            while (i < players.length && j < trainers.length) {
                if (players[i] <= trainers[j]) {
                    matches++;
                    i++;
                }
                j++;
            }
            return matches;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] players = new int[n];
        for (int i = 0; i < n; i++) {
            players[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] trainers = new int[m];
        for (int j = 0; j < m; j++) {
            trainers[j] = sc.nextInt();
        }

        MatchSolver solver = new MatchSolver();
        int result = solver.matchPlayersAndTrainers(players, trainers);
        System.out.println(result);

        sc.close();
    }
}

package Questions_march_2025;

import java.util.*;

public class _2594 {
    public boolean timeIsSuff(int[] ranks, int cars, long minGiven) {
        long carsDone = 0;
        for (int r : ranks) {
            long c2 = minGiven / r;
            long c = (long) Math.sqrt(c2);
            carsDone += c;
        }
        return carsDone >= cars;
    }

    public long repairCars(int[] ranks, int cars) {
        long l = 1, r = (long) 1e14;
        while (l < r) {
            long mid = (l + r) / 2;
            if (timeIsSuff(ranks, cars, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of mechanics: ");
        int n = sc.nextInt();
        int[] ranks = new int[n];
        System.out.println("Enter ranks of mechanics: ");
        for (int i = 0; i < n; i++) {
            ranks[i] = sc.nextInt();
        }
        System.out.print("Enter number of cars: ");
        int cars = sc.nextInt();

        _2594 obj = new _2594();
        long result = obj.repairCars(ranks, cars);
        System.out.println("Minimum time required: " + result);

        sc.close();
    }
}

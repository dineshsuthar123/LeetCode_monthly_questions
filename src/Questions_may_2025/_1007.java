package Questions_may_2025;

public class _1007 {
    public static void main(String[] args) {
        int[] tops = {2, 1, 2, 4, 2, 2};
        int[] bottoms = {5, 2, 6, 2, 3, 2};
        DominoRotationsSolution solution = new DominoRotationsSolution();
        System.out.println(solution.minDominoRotations(tops, bottoms));
    }
}

class DominoRotationsSolution {
    private int check(int target, int[] tops, int[] bottoms) {
        int rotationsTop = 0, rotationsBottom = 0;
        for (int i = 0; i < tops.length; i++) {
            if (tops[i] != target && bottoms[i] != target) {
                return -1;
            } else if (tops[i] != target) {
                rotationsTop++;
            } else if (bottoms[i] != target) {
                rotationsBottom++;
            }
        }
        return Math.min(rotationsTop, rotationsBottom);
    }

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int rotations = check(tops[0], tops, bottoms);
        if (rotations != -1) {
            return rotations;
        } else {
            return check(bottoms[0], tops, bottoms);
        }
    }
}

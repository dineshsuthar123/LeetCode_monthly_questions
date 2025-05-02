package Questions_may_2025;

public class _838 {
    public static void main(String[] args) {
        String dominoes = ".L.R...LR..L..";
        PushDominoesSolution solution = new PushDominoesSolution();
        System.out.println(solution.pushDominoes(dominoes));
    }
}

class PushDominoesSolution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] arr = dominoes.toCharArray();
        int[] forces = new int[n];

        int force = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'R') {
                force = n;
            } else if (arr[i] == 'L') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0);
            }
            forces[i] += force;
        }

        force = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 'L') {
                force = n;
            } else if (arr[i] == 'R') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0);
            }
            forces[i] -= force;
        }

        StringBuilder s = new StringBuilder();
        for (int f : forces) {
            if (f > 0) {
                s.append('R');
            } else if (f < 0) {
                s.append('L');
            } else {
                s.append('.');
            }
        }
        return s.toString();
    }
}

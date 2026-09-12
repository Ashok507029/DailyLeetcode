import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0])
                return Integer.compare(x[0], y[0]);
            return Integer.compare(x[1], y[1]);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int l = i + 1, r = n;

            while (l < r) {
                int m = (l + r) / 2;

                if (a[m][0] > a[i][1])
                    r = m;
                else
                    l = m + 1;
            }

            next[i] = l;
        }

        long[][] dp = new long[n + 1][5];
        int[][][] best = new int[n + 1][5][];

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {

                // Don't take current interval
                long skipScore = dp[i + 1][k];
                int[] skip = best[i + 1][k];

                // Take current interval
                long takeScore = a[i][2] + dp[next[i]][k - 1];

                int[] take = add(a[i][3], best[next[i]][k - 1]);

                if (takeScore > skipScore ||
                    (takeScore == skipScore && smaller(take, skip))) {

                    dp[i][k] = takeScore;
                    best[i][k] = take;
                } else {
                    dp[i][k] = skipScore;
                    best[i][k] = skip;
                }
            }
        }

        return best[0][4];
    }

    // Add index and keep array sorted
    private int[] add(int x, int[] arr) {
        int n = arr == null ? 0 : arr.length;
        int[] res = new int[n + 1];

        int i = 0, j = 0;

        while (j < n && arr[j] < x)
            res[i++] = arr[j++];

        res[i++] = x;

        while (j < n)
            res[i++] = arr[j++];

        return res;
    }

    // true if a is lexicographically smaller than b
    private boolean smaller(int[] a, int[] b) {
        if (b == null)
            return true;

        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {
            if (a[i] != b[i])
                return a[i] < b[i];
        }

        return a.length < b.length;
    }
}
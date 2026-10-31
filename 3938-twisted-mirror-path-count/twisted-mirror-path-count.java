class Solution {
    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1000000007;

        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                if (i == m - 1 && j == n - 1) {
                    right[i][j] = 1;
                    down[i][j] = 1;
                    continue;
                }

                if (grid[i][j] == 1) {

                    // Entered mirror while moving right
                    if (i + 1 < m) {
                        right[i][j] = down[i + 1][j];
                    }

                    // Entered mirror while moving down
                    if (j + 1 < n) {
                        down[i][j] = right[i][j + 1];
                    }

                } else {

                    long ways = 0;

                    if (j + 1 < n) {
                        ways += right[i][j + 1];
                    }

                    if (i + 1 < m) {
                        ways += down[i + 1][j];
                    }

                    right[i][j] = (int)(ways % MOD);
                    down[i][j] = (int)(ways % MOD);
                }
            }
        }

        return (right[0][1] + down[1][0]) % MOD;
    }
}
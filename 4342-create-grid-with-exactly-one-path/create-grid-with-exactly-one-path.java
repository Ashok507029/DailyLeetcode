
class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];
            java.util.Arrays.fill(row, '#');

            if (i == 0) {
                java.util.Arrays.fill(row, '.');
            } else {
                row[n - 1] = '.';
            }

            grid[i] = new String(row);
        }

        return grid;
    }
}


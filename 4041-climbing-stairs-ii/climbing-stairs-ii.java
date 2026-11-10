
class Solution {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n + 1];

        for (int j = 1; j <= n; j++) {
            dp[j] = Long.MAX_VALUE;

            for (int i = Math.max(0, j - 3); i < j; i++) {
                long jump = j - i;

                long total = dp[i] + costs[j - 1] + jump * jump;

                dp[j] = Math.min(dp[j], total);
            }
        }

        return (int) dp[n];
    }
}

class Solution {
    private static final int MOD = 1000000007;

    public int numberOfSets(int n, int k) {
        long[] dp = new long[n + 1];
        dp[1] = 1;

        for (int i = 0; i < k; i++) {
            long sum = 0;

            for (int j = 1; j <= n; j++) {
                sum = (sum + dp[j]) % MOD;
                dp[j] = (sum + dp[j - 1]) % MOD;
            }
        }

        long res = 0;

        for (int i = 0; i < n - k + 1; i++) res = (res + dp[i]) % MOD;

        return (int) res;
    }
}

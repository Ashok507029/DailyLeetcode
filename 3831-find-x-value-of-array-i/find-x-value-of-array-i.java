class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] temp = new long[k];

            int rem = num % k;
            temp[rem]++;

            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int x = (int)((r * 1L * rem) % k);
                    temp[x] += dp[r];
                }
            }

            for (int r = 0; r < k; r++) {
                ans[r] += temp[r];
            }

            dp = temp;
        }

        return ans;
    }
}
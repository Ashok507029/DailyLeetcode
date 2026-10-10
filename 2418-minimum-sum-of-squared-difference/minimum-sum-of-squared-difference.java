
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long total = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            max = Math.max(max, diff[i]);
        }

        long k = (long) k1 + k2;

        if (k >= total) {
            return 0;
        }

        int left = 0;
        int right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long needed = 0;

            for (int d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long ans = 0;

        for (int d : diff) {
            int value = Math.min(d, left);
            ans += (long) value * value;
        }

        long remaining = k;

        for (int d : diff) {
            if (d > left) {
                remaining -= d - left;
            }
        }

        for (int d : diff) {
            if (remaining > 0 && d >= left && d > 0) {
                ans -= 2L * left - 1;
                remaining--;
            }
        }

        return ans;
    }
}

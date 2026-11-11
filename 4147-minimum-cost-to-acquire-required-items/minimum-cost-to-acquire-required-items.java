
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        long ans1 = (long) need1 * cost1 + (long) need2 * cost2;

        int min = Math.min(need1, need2);
        long ans2 = (long) min * costBoth
                  + (long) (need1 - min) * cost1
                  + (long) (need2 - min) * cost2;

        int max = Math.max(need1, need2);
        long ans3 = (long) max * costBoth;

        return Math.min(ans1, Math.min(ans2, ans3));
    }
}

class Solution {
    public int search(int[] nums, int target) {

        int n = nums.length;
        int start=0;
        int end = n-1;

        for(int i=0; i<nums.length; i++){
            int mid = (start+end)/2;
            if(target==nums[mid]){
                return mid;
            }
            if(target>nums[mid]){
                start = mid+1;
            }else if(target < nums[mid]){
                end = mid -1;
            }
        }
        return -1;
    }
}
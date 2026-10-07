class Solution {
    Set<String> ans = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int left = 0;
        int right = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        solve(s, 0, left, right, 0, "");

        return new ArrayList<>(ans);
    }

    void solve(String s, int index, int left, int right, int balance, String current) {
        if (index == s.length()) {
            if (left == 0 && right == 0 && balance == 0) {
                ans.add(current);
            }
            return;
        }

        char c = s.charAt(index);

        if (c == '(') {
            if (left > 0) {
                solve(s, index + 1, left - 1, right, balance, current);
            }

            solve(s, index + 1, left, right, balance + 1, current + c);
        }

        else if (c == ')') {
            if (right > 0) {
                solve(s, index + 1, left, right - 1, balance, current);
            }

            if (balance > 0) {
                solve(s, index + 1, left, right, balance - 1, current + c);
            }
        }

        else {
            solve(s, index + 1, left, right, balance, current + c);
        }
    }
}

class Solution {
    public String removeOuterParentheses(String s) {
        String ans = "";
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                if (count > 0) {
                    ans += ch;
                }
                count++;
            } else {
                count--;

                if (count > 0) {
                    ans += ch;
                }
            }
        }

        return ans;
    }
}
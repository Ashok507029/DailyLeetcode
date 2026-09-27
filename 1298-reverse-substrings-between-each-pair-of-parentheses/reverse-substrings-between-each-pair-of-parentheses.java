class Solution {
    public String reverseParentheses(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();

        for (char c : s.toCharArray()) {
            if (c == ')') {
                StringBuilder temp = new StringBuilder();

                while (stack.peek() != '(') {
                    temp.append(stack.pop());
                }

                stack.pop();

                for (int i = 0; i < temp.length(); i++) {
                    stack.push(temp.charAt(i));
                }
            } else {
                stack.push(c);
            }
        }

        StringBuilder ans = new StringBuilder();

        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        return ans.reverse().toString();
    }
}
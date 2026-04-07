class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();
        for (String op : operations) {
            if (op.equals("+")) {
                int x = stack.pop();
                int y = stack.peek();
                int result = x + y;
                stack.push(x);
                stack.push(result);
            }
            else if (op.equals("C")) {
                stack.pop();
            }
            else if (op.equals("D")) {
                int x = stack.peek();
                int result = 2 * x;
                stack.push(result);
            }
            else {
                stack.push(Integer.parseInt(op));
            }
        }
        int total = 0;
        for (int score : stack) {
            total += score;
        }
        return total;
    }
}
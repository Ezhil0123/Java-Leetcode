class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        for (String dir : path.split("/")) {
            if (dir.isEmpty() || dir.equals(".")) {
                continue;
            }
            if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            }
            else {
                stack.addLast(dir);
            }
        }
        return "/" + String.join("/", stack);
    }
}
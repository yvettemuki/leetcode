import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LeetCode_2019_3_22 {

    // 1130  叶值的最小代价生成树 （贪心算法）
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int item : arr) {
            while (stack.peek() <= item) {
                System.out.println(stack.peek());
                System.out.println(item);
                int cur = stack.peek();
                stack.pop();
                res += cur * Math.min(stack.peek(), item);
            }
            stack.push(item);
        }
        while (stack.size() > 2) {
            int cur = stack.peek(); stack.pop();
            res += stack.peek() * cur;
        }
        return res;
    }

    // 877 石子游戏
    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        int[][] p = new int[len][len];

        for (int i = 0; i < len; i++) {
            p[i][i] = piles[i];
        }
        for (int i = 0; i < len - 1; i++) {
            p[i][i+1] = Math.abs(piles[i]-piles[i+1]);
        }

        for (int i = len - 3; i >= 0; i--){
            for (int j = i + 2; j < len; j++){
                p[i][j] = Math.max(piles[i]-p[i+1][j], piles[j]-p[i][j-1]);
            }
        }

        return p[0][len-1] > 0;
    }


    // 1351 统计有序矩阵中的负数
    public int countNegatives(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] < 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // 1380 矩阵中的幸运数 (将二维数组拆分成i数组和j数组方法)
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> resList = new ArrayList<>();
        int rLen = matrix.length;
        int cLen = matrix[0].length;
        int[] rows = new int[rLen];
        int[] cols = new int[cLen];
        Arrays.fill(rows, Integer.MAX_VALUE);
        Arrays.fill(cols, Integer.MIN_VALUE);

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                rows[i] = Math.min(rows[i], matrix[i][j]);
                cols[j] = Math.max(cols[j], matrix[i][j]);
            }
        }

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (rows[i] == cols[j]) {
                    resList.add(rows[i]);
                }
            }
        }

        return resList;
    }

    // 1290 二进制链表转整数
    public int getDecimalValue(ListNode head) {
        int res = 0;
        int n = 0;
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        while (!stack.empty()) {
            res += Math.pow(2, n++) * stack.pop();
        }
        return res;
    }
}

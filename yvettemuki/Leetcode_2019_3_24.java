import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Leetcode_2019_3_24 {

    // 1365 有多少小于当前数字的的数字
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] resArr = new int[len];
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] sortArr = Arrays.copyOf(nums, len);
        Arrays.sort(sortArr);
        for (int i = 0; i < len; i++) {
            if (map.get(sortArr[i]) == null) {
                map.put(sortArr[i], i);
            }
        }

        for (int i = 0; i < len; i++) {
            resArr[i] = map.get(nums[i]);
        }

        return resArr;
    }

    // 1389 按既定顺序创建目标数组 （关键是判断当前填充是够已经存在数）
    public int[] createTargetArray(int[] nums, int[] index) {
        int len = nums.length;
        int[] targetArr = new int[len];
        Arrays.fill(targetArr, -1);
        for (int i = 0; i < len; i++) {
            int curIdx = index[i];
            int curNum = nums[i];
            if (targetArr[curIdx] != -1) {
                for (int j = len - 1; j > curIdx; j--) {
                    targetArr[j] = targetArr[j - 1];
                }
            }
            targetArr[curIdx] = curNum;
        }
        return targetArr;
    }

    // 链表的中间结点
    public ListNode middleNode(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while(node != null) {
            list.add(node);
            node = node.next;
        }
        return list.get(list.size() / 2);
    }

    // 931 下降路径最小和 (可优化成只用A数组，minFallPathSum数组可以复用A数组实现)
    public int minFallingPathSum(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        int[][] minFallPathSum = new int[row][col];
        for (int j = 0; j < col; j++) {
            minFallPathSum[row - 1][j] = A[row - 1][j];
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                int curMin = minFallPathSum[i + 1][j];
                if (j > 0) {
                    curMin = Math.min(curMin, minFallPathSum[i + 1][j - 1]);
                }
                if (j + 1 < col) {
                    curMin = Math.min(curMin, minFallPathSum[i + 1][j + 1]);
                }
                minFallPathSum[i][j] = A[i][j] + curMin;
            }
        }
        int minSum = minFallPathSum[0][0];
        for (int j = 1; j < col; j++) {
            if (minFallPathSum[0][j] < minSum) {
                minSum = minFallPathSum[0][j];
            }
        }
        return minSum;
    }

    // 1140 石子游戏II
    public int stoneGameII(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[2 * len][2 * len];
        int[] s = new int[len];
        s[len - 1] = piles[len - 1];
        for(int i = len - 2; i >= 0; i--) {
            s[i] = s[i+1] + piles[i];
        }
        return dfs(0, 1, s, dp, len);
    }
    public int dfs(int index,int M,int[] s, int[][] dp, int len) {
        if (index > len) {
            return 0;
        } else if(index + 2 * M >= len) {
            return s[index];
        } else {
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= 2 * M; i++) {
                if (dp[index + i][Math.max(M, i)] != 0) {
                    min = Math.min(min,dp[index + i][Math.max(M, i)]);
                } else {
                    int temp = dfs(index + i, Math.max(M,i), s, dp, len);
                    min = Math.min(min, temp);
                    dp[index + i][Math.max(M, i)] = temp;
                }

            }
            return s[index] - min;
        }
    }

}

import java.util.Arrays;

public class AboutDP {

    // 1025 除数博弈 （递归）
    public boolean divisorGame(int N) {
        int count = play(N, -1);
        if (count % 2 == 0) {
            return true;
        } else {
            return false;
        }

    }
    public int play(int N, int count) {
        for (int x = 1; x < N; x++) {
            if (N % x == 0) {
                return play(N - x, count + 1);
            }
        }
        return count;
    }

    // 746 使用最小花费爬楼梯 （计算到第i层楼梯的最小花费是多少）
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] minCost = new int[len];
        minCost[0] = 0;
        minCost[1] = 0;
        for (int i = 2; i < len; i++) {
            int add = Math.min(minCost[i - 1] + cost[i - 1], minCost[i - 2] + cost[i - 2]);
            minCost[i] = add;
        }
        return Math.min(minCost[len - 1] + cost[len - 1], minCost[len - 2] + cost[len - 2]);
    }

    // 392 判断子序列
    public boolean isSubsequence(String s, String t) {
        int k = 0;
        if (s.length() <= 0) {
            return true;
        }
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(k) == t.charAt(i)) {
                k++;
                if (k == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    // 121 买卖股票的最佳时机 (记录在第i次卖出股票时最大获利是多少)
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 0) {
            return 0;
        }
        int[] maxProfits = new int[len];
        maxProfits[0] = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
                maxProfits[i] = 0;
            } else {
                maxProfits[i] = prices[i] - minPrice;
            }
        }
        Arrays.sort(maxProfits);
        return maxProfits[len - 1];
    }

    // 70 爬楼梯 (记录第i-1 和 i-2层有多少种方式可以爬到)
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] waysToClimbsArr = new int[n];
        waysToClimbsArr[0] = 1;
        waysToClimbsArr[1] = 2;
        for (int i = 2; i < n; i++) {
            waysToClimbsArr[i] = waysToClimbsArr[i - 1] + waysToClimbsArr[i - 2];
        }
        return waysToClimbsArr[n - 1];
    }

    // 53 最大子序和 (i的最大的子串和)
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len <= 0) {
            return 0;
        }
        int[] maxSubArrays = new int[len];
        maxSubArrays[0] = nums[0];
        for (int i = 1; i < len; i++) {
            maxSubArrays[i] = Math.max(maxSubArrays[i - 1] + nums[i], nums[i]);
        }
        Arrays.sort(maxSubArrays);
        return maxSubArrays[len - 1];
    }
}

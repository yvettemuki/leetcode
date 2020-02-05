import java.util.ArrayList;
import java.util.List;

public class AboutArray {
    
    // 解压数组
    public int[] decompressRLEList(int[] nums) {
        int amount = calcAmount(nums);
        int[] decompressArr = new int[amount];
        int nowIndex = 0;
        for (int i = 0; i < nums.length; i += 2) {
            int a = nums[i];
            int b = nums[i + 1];
            for (int j = nowIndex ; j < nowIndex + a; j++) {
                decompressArr[j] = b;
            }
            nowIndex += a;
        }
        return decompressArr;
    }

    public int calcAmount(int[] nums) {
        int amount = 0;
        for (int i = 0; i < nums.length; i += 2) {
            amount += nums[i];
        }
        return amount;
    }

    //统计位数为偶数的数字
    public int findNumbers(int[] nums) {
        int evenCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            int digitCount = 0;
            int result = temp;
            while (( result /= 10) != 0) {
                digitCount++;
            }
            digitCount++;
            if (digitCount % 2 == 0) {
                evenCount++;
            }
        }
        return evenCount;
    }

    // 访问所有点的最小时间
    public int minTimeToVisitAllPoints(int[][] points) {
        int time = 0;
        for (int i = 0; i < points.length - 1; i++) {
            int dx = Math.abs(points[i+1][0] - points[i][0]);
            int dy = Math.abs(points[i+1][1] - points[i][1]);
            int dxy = dx - dy;
            if (dxy < 0) {
                time += dy;
            } else {
                time += dx;
            }
        }
        return time;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] nums2 = {12,345,2,6,7896};
        int[][] nums3 = {{0,0},{1,1}};
        AboutArray aboutArray = new AboutArray();
        int[] resNums = aboutArray.decompressRLEList(nums);
        int count = aboutArray.findNumbers(nums2);
        System.out.println(count);
        int time = aboutArray.minTimeToVisitAllPoints(nums3);
        System.out.println(time);
    }
}

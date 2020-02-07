import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AboutArray2 {

    // leetcode 1252 - 奇数值单元格的数目 （记录行列次数）
    public int oddCells(int n, int m, int[][] indices) {
        int[] rows = new int[n];
        int[] cols = new int[m];
        int oddCellNums = 0;
        for (int i = 0; i < indices.length; i++) {
            rows[indices[i][0]]++;
            cols[indices[i][1]]++;
        }
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                if ((rows[i] + cols[j]) % 2 != 0) {
                    oddCellNums++;
                }
            }
        }
        return oddCellNums;
    }

    // leetcode 1252 - 奇数值单元格的数目(直接方式 -- 占用内存高)
    public int oddCells2(int n, int m, int[][] indices) {
        int[][] matrix = new int[n][m];
        for (int i = 0; i < indices.length; i++) {
            addLine(matrix, indices[i][0], 0);
            addLine(matrix, indices[i][1], 1);
        }
        return calcMatrixOddNums(matrix);
    }

    public void addLine(int[][] matrix, int index, int sign) {
        if (sign == 0) {
            for (int i = 0; i < matrix[index].length; i++) {
                matrix[index][i]++;
            }
        } else {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][index]++;
            }
        }
    }

    public int calcMatrixOddNums(int[][] matrix) {
        int oddNums = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] % 2 != 0) {
                    oddNums++;
                }
            }
        }
        return oddNums;
    }

    // leetcode 1304 - 和为零的N个唯一整数
    public int[] sumZero(int n) {
        int[] arr = new int[n];
        int begin = - (n / 2);
        for (int i = 0; i < n; i++) {
            if (begin == 0) {
                if (n % 2 == 0) {
                    begin++;
                }
            }
            arr[i] = begin++;
        }
        return arr;
    }

    // leetcode 1299 - 将每个元素替换为右侧最大元素 （关键：逆序）
    public int[] replaceElements(int[] arr) {
        int max = arr[arr.length - 1];
        arr[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > max) {
                int temp = max;
                max = arr[i];
                arr[i] = temp;
            } else {
                arr[i] = max;
            }
        }
        return arr;
    }

    // leetcode 832 - 反转图像 (奇偶实际上一样，双下标法) (加=为了01反转被操作)
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = n - 1;
            while (start <= end) {
                int temp = A[i][start];
                A[i][start] = reverse01(A[i][end]);
                A[i][end] = reverse01(temp);
                start++;
                end--;
            }
        }
        return A;
    }

    public int reverse01(int num) {
        return num == 0 ? 1 : 0;
    }

    // leetcode - 905 按奇偶排序数组
    public int[] sortArrayByParity(int[] A) {
        int[] resArr = new int[A.length];
        int index = 0;
        int start = 0;
        int end = A.length - 1;
        while (start <= end) {
            if (A[index] % 2 == 0) {
                resArr[start++] = A[index++];
            } else {
                resArr[end--] = A[index++];
            }
        }
        return resArr;
    }

}

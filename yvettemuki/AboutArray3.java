import java.util.*;

public class AboutArray3 {

    // 1337 方阵中战斗力最弱的 K 行 （Sort列排序）
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] weakestRows = new int[k];
        int[][] map = new int[mat.length][2];
        for (int i = 0; i < mat.length; i++) {
            int rowOneNumber = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    break;
                }
                rowOneNumber++;
            }
            map[i][0] = i;
            map[i][1] = rowOneNumber;
        }
        Arrays.sort(map, (num1, num2) -> num1[1] - num2[1]); // 二维数组列排序
        for (int i = 0; i < k; i++) {
            weakestRows[i] = map[i][0];
        }
        return weakestRows;
    }

    // 1051 高度检查器 (直接排序，检查不同的个数)
    public int heightChecker(int[] heights) {
        int count = 0;
        int[] arr = Arrays.copyOf(heights, heights.length);
        Arrays.sort(arr);
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != arr[i]) {
                count++;
            }
        }
        return count;
    }
    
    // 992 按奇偶排序数组 (直接方式)
    public int[] sortArrayByParityII2(int[] A) {
        Queue<Integer> evenQueue = new LinkedList<Integer>();
        Queue<Integer> oddQueue = new LinkedList<Integer>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                evenQueue.add(A[i]);
            } else {
                oddQueue.add(A[i]);
            }
        }
        int[] resArr = new int[A.length];
        for (int i = 0; i < resArr.length; i++) {
            if (i % 2 == 0) {
                resArr[i] = evenQueue.poll();
            } else {
                resArr[i] = oddQueue.poll();
            }
        }
        return resArr;
    }

    // 992 按奇偶排序数组 (双坐标法，关键：偶数排好就等于奇数排好)
    public int[] sortArrayByParityII(int[] A) {
        int evenIndex = 0;
        int oddIndex = 1;
        while(evenIndex < A.length) {
            if (A[evenIndex] % 2 != 0) {
                int temp = A[oddIndex];
                A[oddIndex] = A[evenIndex];
                A[evenIndex] = temp;
                oddIndex += 2;
            } else {
                evenIndex += 2;
            }
        }
        return A;
    }

    // 1329 将矩阵按对角线排序
    public int[][] diagonalSort(int[][] mat) {
        Map<Integer, List<Integer>> slashMap = new HashMap<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (slashMap.get(i - j) == null) {
                    List<Integer> list = new ArrayList<>();
                    list.add(mat[i][j]);
                    slashMap.put(i - j, list);
                } else {
                    slashMap.get(i - j).add(mat[i][j]);
                }
            }
        }

        int[] indexMarks = new int[mat.length + mat[0].length - 1];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                Collections.sort(slashMap.get(i - j));
            }
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                int index = indexMarks[i - j + mat[0].length - 1]++;
                if (index < slashMap.get(i - j).size()) {
                    mat[i][j] = slashMap.get(i - j).get(index);
                }
            }
        }

        return mat;
    }


}

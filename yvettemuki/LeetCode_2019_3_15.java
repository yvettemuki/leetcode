public class LeetCode_2019_3_15 {
    // 1314 矩阵区域和 （计算前缀矩阵）
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int rLens = mat.length;
        int cLens = mat[0].length;
        int[][] preMat = new int[rLens + 1][cLens + 1];
        int[][] resMat = new int[rLens][cLens];

        for (int i = 0; i < rLens; i++) {
            for (int j = 0; j < cLens; j++) {
                preMat[i + 1][j + 1] = preMat[i + 1][j] + preMat[i][j + 1] - preMat[i][j] + mat[i][j];
            }
        }

        for (int i = 0; i < rLens; i++) {
            for (int j = 0; j < cLens; j++) {
                int x1 = Math.max(i - K, 0);
                int y1 = Math.max(j - K, 0);
                int x2 = Math.min(i + K + 1, rLens);
                int y2 = Math.min(j + K + 1, cLens);
                resMat[i][j] = preMat[x2][y2] - preMat[x2][y1] - preMat[x1][y2] + preMat[x1][y1];
            }
        }

        return resMat;
    }

    // 338 比特位计数
    public int[] countBits(int num) {
        int[] resArr = new int[num + 1];
        resArr[0] = 0;
        for (int i = 1; i <= num; i++) {
            if (i % 2 == 1) {
                resArr[i] = resArr[i - 1] + 1;
            } else {
                resArr[i] = resArr[i / 2];
            }
        }
        return resArr;
    }

    // 1185  一周中的第几天 (基姆拉尔森公式)
    public String dayOfTheWeek(int day, int month, int year) {
        String[] weeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (month < 3) {
            month += 12;
            year--;
        }
        return weeks[(day + 2 * month + 3 * (month + 1) / 5 + year + year / 4- year / 100 + year / 400 + 1) % 7];
    }

    // 1217 玩筹码 （判读奇数多还是偶数多）
    public int minCostToMoveChips(int[] chips) {
        int evenCount = 0;
        int oddCount = 0;
        for (int i = 0; i < chips.length; i++) {
            if (chips[i] % 2 == 0) {
                evenCount ++;
            } else {
                oddCount ++;
            }
        }
        if (evenCount > oddCount) {
            return oddCount;
        } else {
            return evenCount;
        }
    }

    // 766 托普利茨矩阵 (利用对角线两个下标的特征)
    public boolean isToeplitzMatrix(int[][] matrix) {
        int rLens = matrix.length;
        int cLens = matrix[0].length;
        int[] subIndexs = new int[rLens + cLens - 1];
        for (int i = 0; i < subIndexs.length; i++) {
            subIndexs[i] = -1;
        }
        for (int i = 0; i < rLens; i++) {
            for (int j = 0; j < cLens; j++) {
                int temp = subIndexs[i - j + cLens - 1];
                if (temp == -1) {
                    subIndexs[i - j + cLens - 1] = matrix[i][j];
                } else {
                    if (temp != matrix[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}

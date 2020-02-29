import java.util.*;

public class AboutArray5 {

    // 1200 最小绝对差 (HashMap方法，一次循环)
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> resList = new ArrayList<>();
        Map<Integer, List<List<Integer>>> subMap = new HashMap<>();

        Arrays.sort(arr);
        List<List<Integer>> tempList = new ArrayList<>();
        List<Integer> tempItemList = new ArrayList<>();
        Integer minSub = Math.abs(arr[0] - arr[1]);
        tempItemList.add(arr[0]);
        tempItemList.add(arr[1]);
        tempList.add(tempItemList);
        subMap.put(minSub, tempList);
        for (int i = 1; i < arr.length - 1; i++) {
            int temp = Math.abs(arr[i] - arr[i + 1]);
            if (temp < minSub) {
                minSub = temp;
                tempList = new ArrayList<>();
                tempItemList = new ArrayList<>();
                tempItemList.add(arr[i]);
                tempItemList.add(arr[i + 1]);
                tempList.add(tempItemList);
                subMap.put(minSub, tempList);
            } else if (temp == minSub) {
                tempList = subMap.get(minSub);
                tempItemList = new ArrayList<>();
                tempItemList.add(arr[i]);
                tempItemList.add(arr[i + 1]);
                tempList.add(tempItemList);
                subMap.put(minSub, tempList);
            }
        }
        return subMap.get(minSub);
    }

    // 1200 最小绝对差（方法二）（直接方式，两次循环法）
    public List<List<Integer>> minimumAbsDifference2(int[] arr) {
        List<List<Integer>> resList = new ArrayList<>();

        Arrays.sort(arr);
        int minSub = Math.abs(arr[0] - arr[1]);

        for (int i = 1; i < arr.length - 1; i++) {
            int temp = Math.abs(arr[i] - arr[i + 1]);
            if ( temp < minSub) {
                minSub = temp;
            }
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (Math.abs(arr[i] - arr[i + 1]) == minSub) {
                List<Integer> tempList = new ArrayList<>();
                tempList.add(arr[i]);
                tempList.add(arr[i + 1]);
                resList.add(tempList);
            }
        }

        return resList;
    }


    // 999 车的可用捕获量
    public int numRookCaptures(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'R') {
                    int col = j - 1;
                    for (int idx = col; idx >= 0; idx--) {
                        if (board[i][idx] == 'p') {
                            count++;
                            break;
                        }
                        if (board[i][idx] == 'B') {
                            break;
                        }
                    }

                    col = j + 1;
                    for (int idx = col; idx < board[i].length; idx++) {
                        if (board[i][idx] == 'p') {
                            count++;
                            break;
                        }
                        if (board[i][idx] == 'B') {
                            break;
                        }
                    }

                    int row = i - 1;
                    for (int idx = row; idx >= 0; idx--) {
                        if (board[idx][j] == 'p') {
                            count++;
                            break;
                        }
                        if (board[idx][j] == 'B') {
                            break;
                        }
                    }

                    row = i + 1;
                    for (int idx = row; idx < board.length; idx++) {
                        if (board[idx][j] == 'p') {
                            count++;
                            break;
                        }
                        if (board[idx][j] == 'B') {
                            break;
                        }
                    }

                }
            }
        }
        return count;
    }

    // 1277 统计全为1的正方形子矩阵
    public int countSquares(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] up = new int[rows][cols];
        int[][] left = new int[rows][cols];
        int[][] arr = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 1;
                    } else {
                        int upIdx = i - 1;
                        for (int idx = upIdx; idx >= 0; idx--) {
                            if (matrix[idx][j] != 1) {
                                break;
                            }
                            up[i][j]++;
                        }
                        int leftIdx = j - 1;
                        for (int idx = leftIdx; idx >= 0; idx--) {
                            if (matrix[i][idx] != 1) {
                                break;
                            }
                            left[i][j]++;
                        }
                        int temp = arr[i - 1][j - 1] < up[i][j] ? arr[i - 1][j - 1] : up[i][j];
                        arr[i][j] = (temp < left[i][j] ? temp : left[i][j]) + 1;
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                count += arr[i][j];
            }
        }
        return count;
    }

    // 1222 可以攻击国王的皇后
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> resList = new ArrayList<>();
        int[][] board = new int[8][8];

        board[king[0]][king[1]] = 1; // 1 is king
        for (int i = 0; i < queens.length; i++) {
            board[queens[i][0]][queens[i][1]] = 2; // 2 is queen
        }

        int[] addX = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] addY = {-1, 0, 1, 1, 1, 0, -1, -1};
        for (int i = 0; i < queens.length; i++) {
            int x = queens[i][0];
            int y = queens[i][1];
            for (int j = 0; j < 8; j++) {
                if (search(board, x, y, addX[j], addY[j])) {
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(x);
                    tempList.add(y);
                    resList.add(tempList);
                }
            }
        }
        return resList;

    }

    public boolean search(int[][] board, int x, int y, int addX, int addY) {
        x = x + addX;
        y = y + addY;
        if (x < 0 || y < 0 || x > 7 || y > 7) {
            return false;
        }
        if (board[x][y] == 2) {
            return false;
        }
        if (board[x][y] == 1) {
            return true;
        }
        return search(board, x, y, addX, addY);

    }


}

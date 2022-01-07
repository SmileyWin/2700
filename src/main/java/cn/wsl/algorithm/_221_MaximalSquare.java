package cn.wsl.algorithm;

public class _221_MaximalSquare {
    public static void main(String[] args) {

    }

    /**
     * 1    0   1   1   0
     * 0    0   1   1   1
     * 0    1   1   1   1
     * 0    1   1   1   1
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare_2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int maxSide = 0;
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] dp = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                    }
                }
                maxSide = Math.max(dp[i][j], maxSide);
            }
        }
        return maxSide * maxSide;
    }

    public static int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int r = matrix.length;
        int c = matrix[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == '1') {
                    int len = Math.min(r - i, c - j);
                    maxSide = Math.max(maxSide, 1);
                    for (int k = 1; k < len; k++) {
                        boolean flag = true;
                        if (j + k > c || i + k > r) {
                            break;
                        }
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }
                        for (int l = 0; l < k; l++) {
                            if (matrix[i + l][j + k] == '0' || matrix[i + k][j + l] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            maxSide = Math.max(maxSide, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return maxSide * maxSide;
    }

    public static int maximalSquare_failed(char[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] dp = new int[r][c];
        if (matrix[0][0] == 1) {
            dp[0][0] = 1;
        }

        for (int i = 1; i < c; i++) {
            if (matrix[0][i] == 1) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        for (int i = 0; i < r; i++) {
            if (matrix[i][0] == 1) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (dp[i][j] == 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                } else {
                    if (dp[i - 1][j] == 1 && dp[i][j - 1] == 1 && dp[i - 1][j - 1] == 1) {
                        dp[i][j] = Double.valueOf(Math.pow(Math.sqrt(dp[i - 1][j - 1]) + 1, 2)).intValue();
                    }
                }

            }
        }

        return dp[r - 1][c - 1];
    }
}

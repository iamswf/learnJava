package com.video.hanshunping.algorithm.sparsearray;

/**
 * @author sunwenfei@baidu.com
 * @date 2020-11-22 11:29
 */
public class SparseArray {
    public static void main(String[] args) {
        // 构造源二维数组
        int[][] sourceArr = new int[11][11];
        sourceArr[0][1] = 1;
        sourceArr[2][3] = 2;
        sourceArr[4][6] = 1;

        // 打印出源二维数组
        System.out.println("源二维数组:");
        for (int[] row : sourceArr) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }

        // 构造目标稀疏数组 cnt*3，cnt为源数组有效元素数+1
        // 第一行散列：源数组行数、源数组列数、cnt为源数组有效元素数
        int cnt = 0;
        for (int[] row : sourceArr) {
            for (int item : row) {
                if (item != 0) {
                    cnt++;
                }
            }
        }
        // 第一行
        int [][] sparseArr = new int[cnt + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = cnt;
        // 后面行
        int count = 1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (sourceArr[i][j] != 0) {
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = sourceArr[i][j];
                    count++;
                }
            }
        }

        // 打印出稀疏二维数组
        System.out.println("稀疏二维数组:");
        for (int[] row : sparseArr) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }

        // 恢复二维数组
        int[][] recoverArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int lineNum = 1; lineNum <= sparseArr[0][2]; lineNum++) {
            recoverArr[sparseArr[lineNum][0]][sparseArr[lineNum][1]] = sparseArr[lineNum][2];
        }

        // 打印出恢复的二维数组
        System.out.println("恢复的二维数组:");
        for (int[] row : recoverArr) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }
    }
}

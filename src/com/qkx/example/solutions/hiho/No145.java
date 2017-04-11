package com.qkx.example.solutions.hiho;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by qkx on 17/4/9.
 */
public class No145 {
    /**
     描述
     小Hi、小Ho还有被小Hi强拉来的小Z，准备组队参加一个智力竞赛。竞赛采用过关制，共计N个关卡。在第i个关卡中，
     小Hi他们需要获得Ai点分数才能够进入下一关。每一关的分数都是独立计算的，即使在一关当中获得超过需要的分数，也不会对后面的关卡产生影响。
     小Hi他们可以通过答题获得分数。答对一道题获得S点分数，答错一道题获得T点分数。
     在所有的N个关卡中，小Hi他们一共有M次答题机会。在每个关卡中，都可以在累计答题次数不超过M的情况下使用任意次的答题机会。
     那么现在问题来了，对于给定的N、M、S、T和A，小Hi他们至少需要答对多少道题目才能够完成所有的关卡呢？

     输入
     每个输入文件包含多组测试数据，在每个输入文件的第一行为一个整数Q，表示测试数据的组数。
     每组测试数据的第一行为四个正整数N、M、S和T，意义如前文所述。
     第二行为N个正整数，分别表示A1~AN。
     对于40%的数据，满足1<=N,M<=100
     对于100%的数据，满足1<=N,M<=1000,1<=T<S<=10,1<=Ai<=50
     对于100%的数据，满足1<=Q<=100

     输出
     对于每组测试数据，如果小Hi他们能够顺利完成关卡，则输出一个整数Ans，表示小Hi他们至少需要答对的题目数量，否则输出No。

     样例输入
     1
     2 10 9 1
     12 35
     样例输出
     5
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int Q = sc.nextInt();
            for (int k = 0; k < Q; k++) {
                int N = sc.nextInt();
                int M = sc.nextInt();
                int S = sc.nextInt();
                int T = sc.nextInt();

                int[] A = new int[N + 1];
                for (int i = 1; i <= N; i++) {
                    A[i] = sc.nextInt();
                }

                // 第i关答对j题剩余最多次数
                int[][] dp = new int[N + 1][M + 1];
//                for (int j = 0; j <= M; j++) {
//                    dp[0][j] = M;
//                }
                for (int i = 0; i <= N; i++) {
                    for (int j = 0; j <= M; j++) {
                        dp[i][j] = -1;
                    }
                }
                dp[0][0] = M;

                for (int i = 1; i <= N; i++) {
                    for (int j = 0; j <= M; j++) {
                        int x = 0;
                        for (; x <= j && x * S < A[i]; x++) {
                            int remain = A[i] - x * S;
                            int consume = ceil(remain, T) + x;
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - x] - consume);
                        }
                        if (x <= j) {
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - x] - x);
                        }
                    }
                }

                int result = -1;
                for (int j = 0; j <= M; j++) {
                    if (dp[N][j] >= 0) {
                        result = j;
                        break;
                    }
                }

                if (result >= 0) {
                    System.out.println(result);
                } else {
                    System.out.println("No");
                }
//                for (int[] array : dp) {
//                    System.out.println(Arrays.toString(array));
//                }

            }

        }
    }

    private static int ceil(int a, int b) {
        int result = a / b;
        return a % b == 0 ? result : result + 1;
    }
}

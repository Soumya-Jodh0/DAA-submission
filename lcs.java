import java.util.Scanner;
import java.util.Arrays;

public class lcs
{
    public static String computeLCS(String X, String Y) 
    {
        int m = X.length();
        int n = Y.length();
        int[][] dp = new int[m + 1][n + 1];
        char[][] dir = new char[m + 1][n + 1];

        for (int i = 1; i <= m; i++) 
        {
            for (int j = 1; j <= n; j++)
            {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) 
                {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    dir[i][j] = 'D';
                } 
                else if (dp[i - 1][j] >= dp[i][j - 1]) 
                {
                    dp[i][j] = dp[i - 1][j];
                    dir[i][j] = 'U';
                } 
                else 
                {
                    dp[i][j] = dp[i][j - 1];
                    dir[i][j] = 'L';
                }
            }
        }

        System.out.println("Cost matrix : ");
        System.out.print("     ");
        for (int j = 0; j < n; j++) System.out.printf(" %3c", Y.charAt(j));
        System.out.println();
        for (int i = 0; i <= m; i++) 
        {
            if (i == 0) System.out.print("  "); else System.out.printf("%2c", X.charAt(i - 1));
            for (int j = 0; j <= n; j++) 
            {
                String s = Integer.toString(dp[i][j]);
                char d = dir[i][j] == '\u0000' ? ' ' : dir[i][j];
                System.out.printf(" %2s%c", s, d);
            }
            System.out.println();
        }

        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (dir[i][j] == 'D') 
            {
                sb.append(X.charAt(i - 1));
                i--; j--;
            } 
            else if (dir[i][j] == 'U') 
            {
                i--;
            } 
            else 
            {
                j--;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String X = "AGCCCTAAGGCTACCTAGCTT";
        String Y = "GACAGCCTACAAGCGTTAGCTTG";
        String lcs = computeLCS(X, Y);
        System.out.println("Length of LCS: " + lcs.length());
        System.out.println("LCS: " + lcs);
        System.out.println();
        sc.close();
    }
}
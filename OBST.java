import java.util.Scanner;

public class OBST
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of book IDs : ");
        int n = sc.nextInt();

        System.out.print("Enter the sorted book IDs : ");
        int[] keys = new int[n];
        for (int i = 0; i < n; i++)
        {
            keys[i] = sc.nextInt();
        }

        System.out.print("Enter the probabilities of successful searches : ");
        double[] p = new double[n];
        for (int i = 0; i < n; i++)
        {
            p[i] = sc.nextDouble();
        }

        System.out.print("Enter the probabilities of unsuccessful searches : ");
        double[] q = new double[n + 1];
        for (int i = 0; i <= n; i++)
        {
            q[i] = sc.nextDouble();
        }

        double[][] e = new double[n + 1][n + 1];
        double[][] w = new double[n + 1][n + 1];

        for (int i = 0; i <= n; i++)
        {
            e[i][i] = q[i];
            w[i][i] = q[i];
        }

        for (int len = 1; len <= n; len++)
        {
            for (int i = 0; i <= n - len; i++)
            {
                int j = i + len;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j - 1] + q[j];

                for (int r = i + 1; r <= j; r++)
                {
                    double cost = e[i][r - 1] + e[r][j] + w[i][j];
                    if (cost < e[i][j])
                    {
                        e[i][j] = cost;
                    }
                }
            }
        }

        System.out.printf("Minimum expected cost of the Optimal Binary Search Tree: %.4f\n", e[0][n]);

        sc.close();
    }
}

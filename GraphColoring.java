import java.util.*;
public class GraphColoring
{
    int V;
    int[] color;

    boolean isSafe(int v, int[][] graph, int[] color, int c)
    {
        for (int i = 0; i < V; i++)
            if (graph[v][i] == 1 && c == color[i])
                return false;
        return true;
    }

    boolean graphColoringUtil(int[][] graph, int m, int[] color, int v)
    {
        if (v == V)
            return true;
        for (int c = 1; c <= m; c++)
        {
            if (isSafe(v, graph, color, c))
            {
                color[v] = c;
                if (graphColoringUtil(graph, m, color, v + 1))
                    return true;
                color[v] = 0;
            }
        }
        return false;
    }

    boolean graphColoring(int[][] graph, int m)
    {
        color = new int[V];
        Arrays.fill(color, 0);
        if (!graphColoringUtil(graph, m, color, 0))
        {
            System.out.println("Solution does not exist");
            return false;
        }
        printSolution(color);
        return true;
    }

    void printSolution(int[] color)
    {
        System.out.print("Solution Exists: Following are the assigned colors: ");
        for (int i = 0; i < V; i++)
            System.out.print(color[i] + " ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        int[][] graph = new int[V][V];
        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                graph[i][j] = sc.nextInt();
        System.out.print("Enter number of colors: ");
        int m = sc.nextInt();
        GraphColoring g = new GraphColoring();
        g.V = V;
        g.graphColoring(graph, m);
        sc.close();
    }
}

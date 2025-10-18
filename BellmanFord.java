import java.util.*;

public class BellmanFord
{
    static class Edge
    {
        int src, dest;
        double weight;

        Edge(int src, int dest, double weight)
        {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Location
    {
        String name;
        double lat, lon;

        Location(String name, double lat, double lon)
        {
            this.name = name;
            this.lat = lat;
            this.lon = lon;
        }
    }
                          
    static double haversine(double lat1, double lon1, double lat2, double lon2)
    {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    static void bellmanFord(List<Edge> edges, int V, int src, Location[] locations)
    {
        double[] dist = new double[V];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[src] = 0;

        for (int i = 1; i < V; i++)
        {
            for (Edge edge : edges)
            {
                if (dist[edge.src] != Double.MAX_VALUE &&
                    dist[edge.src] + edge.weight < dist[edge.dest])
                {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        for (Edge edge : edges)
        {
            if (dist[edge.src] != Double.MAX_VALUE &&
                dist[edge.src] + edge.weight < dist[edge.dest])
            {
                System.out.println("Graph contains a negative-weight cycle");
                return;
            }
        }
        
        System.out.println("Shortest distances from Zero Mile (" + locations[src].name + "):");
        for (int i = 0; i < V; i++)
        {
            System.out.printf("To %s: %.2f km\n", locations[i].name, dist[i]);
        }
    }

    public static void main(String[] args)
    {
        Location[] locations = new Location[]
        {
            new Location("Zero Mile", 21.1498, 79.0827),
            new Location("Deekshabhoomi", 21.1280, 79.0615),
            new Location("Futala Lake", 21.1545, 79.0371),
            new Location("Ambazari Lake and Garden", 21.1458, 79.0526),
            new Location("Raman Science Centre", 21.1450, 79.0880),
            new Location("Sitabuldi Fort", 21.1432, 79.0884),
            new Location("Dragon Palace Temple", 21.0892, 79.1258)

        };

        int V = locations.length;
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < V; i++)
        {
            for (int j = 0; j < V; j++)
            {
                if (i != j)
                {
                    double dist = haversine(locations[i].lat, locations[i].lon,
                                            locations[j].lat, locations[j].lon);
                    edges.add(new Edge(i, j, dist));
                }
            }
        }

        int src = 0;
        bellmanFord(edges, V, src, locations);
    }
}
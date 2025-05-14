import java.util.*;
public class RouteCalculator {

    private final double alpha;
    private final double beta;
    private final double gamma;
    private final Map<String, List<Edge>> graph = new HashMap<>();

    public RouteCalculator(double alpha, double beta, double gamma) {
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
    }

    public void addRoad(String from, String to, double distance, double time, double traffic) {
        graph.computeIfAbsent(from, k -> new ArrayList<>())
                .add(new Edge(to, computeWeight(distance, time, traffic)));
        graph.computeIfAbsent(to, k -> new ArrayList<>())
                .add(new Edge(from, computeWeight(distance, time, traffic)));
    }

    private double computeWeight(double distance, double time, double traffic) {
        return alpha * distance + beta * time + gamma * traffic;
    }

    public RouteResult findOptimalRoute(String source, String target) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.cost));
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();

        for (String loc : graph.keySet()) {
            dist.put(loc, Double.POSITIVE_INFINITY);
        }
        dist.put(source, 0.0);
        pq.add(new Node(source, 0.0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.name.equals(target)) break;
            if (curr.cost > dist.get(curr.name)) continue;
            for (Edge e : graph.getOrDefault(curr.name, Collections.emptyList())) {
                double newCost = curr.cost + e.weight;
                if (newCost < dist.getOrDefault(e.to, Double.POSITIVE_INFINITY)) {
                    dist.put(e.to, newCost);
                    prev.put(e.to, curr.name);
                    pq.add(new Node(e.to, newCost));
                }
            }
        }

        List<String> path = new ArrayList<>();
        String step = target;
        if (!prev.containsKey(step) && !source.equals(target)) {
            return new RouteResult(Double.POSITIVE_INFINITY, Collections.emptyList());
        }
        while (step != null) {
            path.add(step);
            step = prev.get(step);
        }
        Collections.reverse(path);
        return new RouteResult(dist.getOrDefault(target, Double.POSITIVE_INFINITY), path);
    }

    private static class Edge {
        final String to;
        final double weight;
        Edge(String to, double weight) { this.to = to; this.weight = weight; }
    }

    private static class Node {
        final String name;
        final double cost;
        Node(String name, double cost) { this.name = name; this.cost = cost; }
    }

    public static class RouteResult {
        public final double totalCost;
        public final List<String> path;
        public RouteResult(double totalCost, List<String> path) {
            this.totalCost = totalCost;
            this.path = path;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter alpha (distance weight), beta (time weight), gamma (traffic weight):");
        double alpha = sc.nextDouble();
        double beta = sc.nextDouble();
        double gamma = sc.nextDouble();
        RouteCalculator rc = new RouteCalculator(alpha, beta, gamma);

        System.out.println("Enter number of roads:");
        int n = sc.nextInt();
        System.out.println("Enter roads in format: from to distance time traffic");
        for (int i = 0; i < n; i++) {
            String from = sc.next();
            String to = sc.next();
            double dist = sc.nextDouble();
            double time = sc.nextDouble();
            double traffic = sc.nextDouble();
            rc.addRoad(from, to, dist, time, traffic);
        }

        System.out.println("Enter source and target locations:");
        String source = sc.next();
        String target = sc.next();

        RouteResult result = rc.findOptimalRoute(source, target);
        if (result.path.isEmpty()) {
            System.out.println("No path found from " + source + " to " + target);
        } else {
            System.out.println("Optimal cost: " + result.totalCost);
            System.out.println("Route: " + String.join(" -> ", result.path));
        }
        sc.close();
    }
}

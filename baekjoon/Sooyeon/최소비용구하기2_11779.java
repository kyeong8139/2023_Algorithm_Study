import java.io.*;
import java.util.*;

public class 최소비용구하기2_11779 {
  static class Node implements Comparable<Node> {
    int e, cost;

    public Node(int e, int cost) {
      this.e = e;
      this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
      return Integer.compare(this.cost, o.cost);
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();

    int n, m;
    n = Integer.parseInt(bf.readLine());
    m = Integer.parseInt(bf.readLine());

    List<Node>[] buses = new ArrayList[n+1];

    for (int i = 1; i <= n; i++) {
      buses[i] = new ArrayList<>();
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(bf.readLine());
      int a = Integer.parseInt(st.nextToken()),
      b = Integer.parseInt(st.nextToken()),
      c = Integer.parseInt(st.nextToken());
      buses[a].add(new Node(b, c));
    }

    int s, e;
    st = new StringTokenizer(bf.readLine());
    s = Integer.parseInt(st.nextToken());
    e = Integer.parseInt(st.nextToken());

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(s, 0));

    int[] dist = new int[n + 1];
    int[] course = new int[n + 1];
    boolean[] visited = new boolean[n + 1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[s] = 0;
    course[s] = 0;

    while (!pq.isEmpty()) {
      Node now = pq.poll();

      if (visited[now.e])
        continue;
      visited[now.e] = true;

      for (Node node : buses[now.e]) {
        if (dist[now.e] + node.cost < dist[node.e]) {
          dist[node.e] = dist[now.e] + node.cost;
          course[node.e] = now.e;
          pq.offer(new Node(node.e, dist[now.e] + node.cost));
        }
      }
    }

    sb.append(dist[e] + "\n");
    List<Integer> res = new ArrayList<>();
    int cur = e;
    while (cur != 0) {
      res.add(cur);
      cur = course[cur];
    }
    sb.append(res.size() + "\n");
    for (int i = res.size() - 1; i >= 0; i--) {
      sb.append(res.get(i) + " ");
    }

    System.out.print(sb);
  }
}
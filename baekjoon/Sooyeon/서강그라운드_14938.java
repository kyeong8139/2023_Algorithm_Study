import java.io.*;
import java.util.*;

public class 서강그라운드_14938 {
    static class Node implements Comparable<Node> {
        int to;
        int value;
    
        Node(int to, int value) {
            this.to = to;
            this.value = value;
        }
        // 오름차순
        @Override
        public int compareTo(Node o) {
            if(this.value < o.value) {
                return -1;	
            }
            return 1;
        }
    }
	static int n, m, r;
	static int[] items, results;
	static List<Node>[] graph;
	
	public static void main(String args[]) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		r = Integer.valueOf(st.nextToken());
	
		graph = new ArrayList[n];
		items = new int[n + 1];	// index : 1 ~ n
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
			items[i] = Integer.valueOf(st.nextToken());
		}
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.valueOf(st.nextToken())-1;
			int to = Integer.valueOf(st.nextToken())-1;
			int value = Integer.valueOf(st.nextToken());
			// 양방향 그래프
			graph[from].add(new Node(to, value));
			graph[to].add(new Node(from, value));
		}
		
		results = new int[n];
		int result = 0;
		for(int i = 0; i < n; i++) {
			Arrays.fill(results, Integer.MAX_VALUE);
			result = Math.max(result, dijkstra(i));
		}
		
		System.out.println(result);
	}
	
	static int dijkstra(int start) {
		boolean[] visited = new boolean[n];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int result = 0;
		pq.offer(new Node(start, 0));
		results[start] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(visited[node.to]) {
				continue;
			}
			
			if(node.value > results[node.to]) {
				continue;
			}
			
			visited[node.to] = true;
			
			for(Node nextNode : graph[node.to]) {

				if(!visited[nextNode.to] && nextNode.value + results[node.to] < results[nextNode.to]) {
					results[nextNode.to] = nextNode.value + results[node.to];
					pq.offer(new Node(nextNode.to, results[nextNode.to]));
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(results[i] <= m) {
				result += items[i];
			}
		}
		
		return result;
	}
}


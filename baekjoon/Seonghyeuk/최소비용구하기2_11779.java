package node2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

// Node 정의
class Node implements Comparable<Node> {
	int idx;
	int cost;

	// 생성자
	Node(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node other) {
		return Integer.compare(this.cost, other.cost);
	}
}

public class 최소비용구하기2_11779 {
	static int[] route;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 도시의 개수
		int M = sc.nextInt(); // 버스의 개수

		// 1. 인접리스트를 이용한 그래프 초기화
		ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
		// 노드의 번호가 1부터 시작하므로, 0번 인덱스 부분을 임의로 만들어 놓기만 한다.
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}
		// 그래프에 값을 넣는다.
		for (int i = 0; i < M; i++) {
			// a로 부터 b로 가는 값은 cost이다.
			int a = sc.nextInt();
			int b = sc.nextInt();
			int cost = sc.nextInt();

			graph.get(a).add(new Node(b, cost));
		}
		int start = sc.nextInt();
		int end = sc.nextInt();
		int[] dist = dijkstra(graph, N, start, end);
		System.out.println(dist[end]);
		// 경로 역추적
		int cnt = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(end);
		while (route[end] != 0) {
			cnt += 1;
			stack.push(route[end]);
			end = route[end];
		}
		System.out.println(cnt + 1);
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	static int[] dijkstra(ArrayList<ArrayList<Node>> graph, int N, int start, int end) {
		int[] dist = new int[N + 1];
		route = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int idx = current.idx;
			int cost = current.cost;

			if (dist[idx] < cost)
				continue;

			for (Node next : graph.get(idx)) {
				int nextIdx = next.idx;
				int nextCost = cost + next.cost;

				if (nextCost < dist[nextIdx]) {
					dist[nextIdx] = nextCost;
					pq.offer(new Node(nextIdx, nextCost));
					route[nextIdx] = current.idx;
				}
			}
		}
		return dist;
	}
}

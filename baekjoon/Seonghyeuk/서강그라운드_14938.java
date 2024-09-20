package node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

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

public class 서강그라운드_14938 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 지역의 개수
        int M = sc.nextInt(); // 수색 범위
        int R = sc.nextInt(); // 길의 개수

        int[] arr = new int[N + 1]; // 물품 개수 입력 받기
        for (int i = 1; i <= N; ++i) {
            arr[i] = sc.nextInt(); // 물품의 갯수
        }

        // 1. 인접리스트를 이용한 그래프 초기화
        ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
        // 노드의 번호가 1부터 시작하므로, 0번 인덱스 부분을 임의로 만들어 놓기만 한다.
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        // 그래프에 값을 넣는다.
        for (int i = 0; i < R; i++) {
            // a로 부터 b로 가는 값은 cost이다.
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost)); // 양방향 그래프이므로 반대 방향도 추가
        }
        int max = 0;
        for (int i = 1; i <= N; ++i) {
            int[] dist = dijkstra(graph, N, i);

            int cnt = 0;
            for (int j = 1; j <= N; ++j) {
                if (dist[j] <= M) {
                    cnt += arr[j];
                }
            }
            if (cnt > max) {
                max = cnt;
            }
        }
        System.out.println(max);
    }

    static int[] dijkstra(ArrayList<ArrayList<Node>> graph, int N, int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int idx = current.idx;
            int cost = current.cost;

            if (dist[idx] < cost) continue;

            for (Node next : graph.get(idx)) {
                int nextIdx = next.idx;
                int nextCost = cost + next.cost;

                if (nextCost < dist[nextIdx]) {
                    dist[nextIdx] = nextCost;
                    pq.offer(new Node(nextIdx, nextCost));
                }
            }
        }
        return dist;
    }
}

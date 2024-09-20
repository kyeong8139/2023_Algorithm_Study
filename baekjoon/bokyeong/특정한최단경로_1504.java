import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] adjMatrix = new int[N+1][N+1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 무향 그래프
			adjMatrix[from][to] = weight;
			adjMatrix[to][from] = weight;
		}
		
		st = new StringTokenizer(br.readLine());
		int goalV1 = Integer.parseInt(st.nextToken());
		int goalV2 = Integer.parseInt(st.nextToken());
		
		// 다익스트라를 위해 거리를 저장할 배열
    // 정석적인 방법은 dist배열을 Integer.MAX_VALUE로 초기화해주는거지만 시간절약을 위해 생략
		int[][] dist = new int[N+1][N+1];
		
		// 반드시 두 개의 정점을 거쳐야함 -> 경로가 3개이므로 세번의 다익스트라를 수행함
		for (int start : new int[]{1, goalV1, goalV2}) {
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			pq.offer(new Vertex(start, 0));
			dist[start][start] = 0;	
			
			while (!pq.isEmpty()) {
				Vertex cur = pq.poll();
				
				for (int i = 1; i <= N; i++) {
					int curWeight = adjMatrix[cur.id][i];
					if (curWeight == 0) continue;
					
					int curDist = cur.dist + curWeight;
					if (dist[start][i] == 0 || dist[start][i] > curDist) {
						pq.offer(new Vertex(i, curDist));
						dist[start][i] = curDist;
					}
				}
			}
		}
		
		int ans = Integer.MAX_VALUE;
		if (dist[goalV1][goalV2] != 0) {
			// 1 -> v1 -> v2 -> N
			if ((goalV1 == 1 || dist[1][goalV1] != 0) && (goalV2 == N || dist[goalV2][N] != 0)) {
        // dist 배열을 초기화해주지 않았기때문에, goalV1==1이거나 goalV2==N일때 사이클이 있으면 이상한 값으로 지정되어 있으므로 삼항연산자 활용
				ans = Math.min(ans, (goalV1 == 1 ? 0 : dist[1][goalV1]) + dist[goalV1][goalV2] + (goalV2 == N ? 0 : dist[goalV2][N]));
			}
			
			// 1 -> v2 -> v1 -> N
			if (dist[1][goalV2] != 0 && dist[goalV1][N] != 0) {
				ans = Math.min(ans, dist[1][goalV2] + dist[goalV2][goalV1] + dist[goalV1][N]);
			}
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static class Vertex implements Comparable<Vertex> {
		int id;
		int dist;
		
		public Vertex(int id, int dist) {
			this.id = id;
			this.dist = dist;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.dist - o.dist;
		}
	}
}

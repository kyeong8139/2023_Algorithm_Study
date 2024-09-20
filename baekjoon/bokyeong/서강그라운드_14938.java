import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	// 지역의 갯수
		int m = Integer.parseInt(st.nextToken());	// 수색 범위
		int r = Integer.parseInt(st.nextToken());	// 길의 갯수
		
		// 아이템의 갯수 입력
		st = new StringTokenizer(br.readLine());
		int[] items = new int[n+1];
		for (int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		// 그래프 생성
		ArrayList<int[]>[] graph = new ArrayList[n+1];
		for	(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 길 정보
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 양방향 그래프
			graph[from].add(new int[] {to, weight});
			graph[to].add(new int[] {from, weight});
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
		int max = 0;
		for (int start = 1; start <= n; start++) {
			pq.clear();
			pq.offer(new int[] {start, 0}); // 현재위치, 탐색비용 배열
			
			// visited 대신 items 배열 활용
			for (int i = 1; i <=n; i++) {
				if (items[i] < 0) items[i] *= -1;
			}
			
			int itemCnt = 0;
			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				
				// 이미 방문한 곳이면 넘어감
				// 가장 가까운 노드가 탐색범위를 벗어나면 넘어감 
				if (items[cur[0]] < 0 || cur[1] > m) continue;
				
				itemCnt += items[cur[0]];
				items[cur[0]] *= -1;
				
				for (int i = 0; i < graph[cur[0]].size(); i++) {
					int next = graph[cur[0]].get(i)[0];
					
					// 방문한 곳이면 추가하지 않음
					if (items[next] < 0) continue;
					
					int weight = graph[cur[0]].get(i)[1];
					pq.offer(new int[] {next, cur[1] + weight});
				}
			}
			max = Math.max(max, itemCnt);
		}
		System.out.println(max);
	}
}

package study2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class pg_도넛과막대그래프 {
	public static void main(String[] args) {
//		int[][] edges = new int[][] 
//				{ 
//				  {2, 3},
//				  {4, 3},
//				  {1, 1},
//				  {2, 1}
//				};

//		int[][] edges = new int[][] { { 4, 11 }, { 1, 12 }, { 8, 3 }, { 12, 7 }, { 4, 2 }, { 7, 11 }, { 4, 8 },
//				{ 9, 6 }, { 10, 11 }, { 6, 10 }, { 3, 5 }, { 11, 1 }, { 5, 3 }, { 11, 9 }, { 3, 8 }, {2, 13} };

//		int[][] edges = new int[][] {{1, 2}, {2, 3}, {3, 4}, {4, 1}, {5, 6}, {6, 8}, {8, 6}, {6, 5}, {7, 1}, {7, 5}};
		
		int[][] edges = new int[][] { {51, 1}, {2, 2}, {3, 1}, {3, 2}};
		
		System.out.println(Arrays.toString(solution(edges)));
	}

	static int[] answer;
	static int[] result;
	static boolean flag;

	static int[] solution(int[][] edges) {
		answer = new int[4];

		int N = edges.length;

		// 정점 개수 구하기
		int V = 0;
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			V = Math.max(V, Math.max(edges[i][0], edges[i][1]));
			set.add(edges[i][0]);
			set.add(edges[i][1]);
		}
		
		Object[] arr = set.toArray();
		
		System.out.println(Arrays.toString(arr));

		// 추가 정점 구하기
		boolean[] nums = new boolean[V + 1];
		for (int i = 0; i < N; i++) {
			nums[edges[i][1]] = true;
		}

		int add = 0;
		for (int i = 1; i <= V; i++) {
			if (!nums[i]) {
				add = i;
				answer[0] = add;
				break;
			}
		}

		// 인접리스트 생성
		List<Integer>[] adj = new List[V + 1];

		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		// 자신에게 들어오는 간선이 가장 적은 정점 구할 배열 생성
		int[][] find = new int[V + 1][2];
		for (int i = 1; i <= V; i++)
			find[i][0] = i;

		// 간선 연결하기 -> 추가 정점에서 시작하는 것은 체크 X
		for (int i = 0; i < edges.length; i++) {
			int from = edges[i][0];
			int to = edges[i][1];

			if (from == add)
				continue;

			find[to][1]++;
			adj[from].add(to);
		}

		Arrays.sort(find, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		for(int[] i : find) System.out.println(Arrays.toString(i));
		
		boolean[] visited = new boolean[V + 1];
		int idx;
		for (int i = 1; i <= V; i++) {
			idx = find[i][0];
			if (idx == add || visited[idx])
				continue;
			
			result = new int[3];
			visited[idx] = true;
			dfs(idx, idx, visited, adj);
			
			// 도넛형
			if(result[0] == 1) answer[1]++;
			// 8자형
			else if(result[1] == 1) answer[3]++;
			// 막대형
			else if(result[2] == 1) answer[2]++;

		}

		return answer;
	}
	
	// result 배열의 0번 : 도넛형 여부, 1번 : 8자형 여부, 2번 : 막대형 여부
	static void dfs(int start, int now, boolean[] visited, List<Integer>[] adj) {
		if(adj[now].size() == 0) {
			result[2] = 0;
			return;
		}
		
		int num = adj[now].get(0);
		// 두 갈래 이상 나오면 8자형
		if(adj[now].size() == 2) {
			result[1] = 1;
			for(int i=0; i<2; i++) {
				if(!visited[adj[now].get(i)]) {
					visited[adj[now].get(i)] = true;
					dfs(start, adj[now].get(i), visited, adj);
				}
			}
		}
		// 8자형 외 방문한 적이 있으면
		else if(result[1] != 1 && visited[num]) result[0] = 1;
		else if(!visited[num]) {
			visited[num] = true;
			dfs(start, num, visited, adj);
		}
	}

}

// 문제1 : 내가 고른 정점이 교차하는 정점이면 중복 될 위험이 생김
// 해결 : 추가된 노드가 가리키는 것을 제외하고, 자신에게 들어오는 정점 개수가 가장 적은 정점부터 체크

// 이미 방문한 곳인데 그 곳이 시작 지점 == 도넛
// 이미 방문한 곳인데 그 곳이 시작 지점이 아님 == 8자형
// 반복 방문한 곳 없이 종료 == 막대형

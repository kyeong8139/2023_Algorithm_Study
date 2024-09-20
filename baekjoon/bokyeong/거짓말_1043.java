import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[N+1];
		
		// 진실을 아는 사람 입력
		Queue<Integer> knowReality = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i < cnt; i++) {
			int cur = Integer.parseInt(st.nextToken());
			
			knowReality.add(cur);
			visited[cur] = true;
		}
		
		// 파티 처리
		Queue<ArrayList<Integer>> party = new ArrayDeque<>();
		for (int i = 0; i < M; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			
			int size = Integer.parseInt(st.nextToken());
			while (--size >= 0) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			
			party.add(temp);
		}
		
		while (!knowReality.isEmpty()) {
			int curPerson = knowReality.poll();
			
			int size = party.size();
			while (--size >= 0) {
				ArrayList<Integer> temp = party.poll();
				
				// 진실을 말해야 하는 파티
				if (temp.contains(curPerson)) {
					for (int i = 0; i < temp.size(); i++) {
						int cur = temp.get(i);
						if (!visited[cur]) {
							visited[cur] = true;
							knowReality.add(cur);
						}
					}
				} else {
					party.add(temp);
				}
			}
		}
		
		System.out.println(party.size());
	}
}

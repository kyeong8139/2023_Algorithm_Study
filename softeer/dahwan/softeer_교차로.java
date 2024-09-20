package study2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 오른쪽에 차가 있으면 출발 안 하고 1초 기다림
// A(0)의 오른쪽 = D(3)
// B(1)의 오른쪽 = A(0)
// C(2)의 오른쪽 = B(1)
// D(3)의 오른쪽 = C(2)
public class softeer_교차로 {
	
	static class Node {
		int idx;
		int time;
		String car;
		
		public Node() {};
		
		public Node(int idx, int time, String car) {
			this.idx = idx;
			this.time = time;
			this.car = car;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", time=" + time + ", car=" + car + "]";
		}	
	}
	static int[] ans;
	static boolean flag;
	static int N, seconds, nextSeconds, cnt, pollCnt;
	static Queue<Node>[] roads;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		N = Integer.parseInt(br.readLine());
		roads = new Queue[4];
		ans = new int[N];
		
		// 0 : A, 1 : B, 2 : C, 3 : D
		for(int i = 0; i < 4; i++) {
			roads[i] = new ArrayDeque<>();
		}
		
		// 큐에 집어넣기
		int time;
		String car;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			time = Integer.parseInt(st.nextToken());
			car = st.nextToken();
			
			if(car.equals("A")) {
				roads[0].add(new Node(i, time, car));
			} else if(car.equals("B")) {
				roads[1].add(new Node(i, time, car));
			} else if(car.equals("C")) {
				roads[2].add(new Node(i, time, car));
			} else {
				roads[3].add(new Node(i, time, car));
			}
		}
		
		// 점프해서 도달할 시간
		// 현재 시간
		seconds = 0;
		while(!(roads[0].isEmpty() && roads[1].isEmpty() && roads[2].isEmpty() && roads[3].isEmpty())) {
			nextSeconds = Integer.MAX_VALUE;
			// 현재 시간에 직진 할 수 있는 도로
			boolean[] isPoll = new boolean[4];
			// 가장 앞에 있는 노드의 time이 현재 시간보다 큰 큐의 개수
			cnt = 0;
			// 직진할 수 있는 차의 개수
			pollCnt = 0;
			for(int i = 0; i < 4; i++) {
				// 비어 있으면 현재 도로가 비었음
				if(roads[i].isEmpty()) {
					isPoll[(i+1) % 4] = true;
					pollCnt++;
					cnt++;
					continue;
				}
				
				// 현재 시간에 차가 안 서 있으면 현재 도로가 비었음
				if(roads[i].peek().time > seconds){
					isPoll[(i+1) % 4] = true;
					pollCnt++;
					cnt++;
					nextSeconds = Math.min(nextSeconds, roads[i].peek().time);
				}
				
			} 

			for(int i = 0; i < 4; i++) {
				if(isPoll[i] && !roads[i].isEmpty()) {
					// 현재 도로에 있는 차면 보내주기
					if(roads[i].peek().time <= seconds) ans[roads[i].poll().idx] = seconds;
				}
			}
			
			if(cnt != 4 && pollCnt == 0) {
				flag = true;
				break;
			}
			
			// 큐 1개라도 현재 시간에 차가 대기하는 경우
			if(cnt != 4) {
				seconds++;
			// 큐 4개 전부 현재 시간에 차가 대기하지 않는 경우
			} else {
				seconds = Math.max(nextSeconds, seconds + 1);
			}
		}
		
		if(flag) {
			for(int i = 0; i < 4; i++) {
				ans[roads[i].poll().idx] = -1;
			}
		}
		
		for(int i = 0; i < N; i++) {
			sb.append(ans[i] + "\n");
		}
		
		System.out.println(sb);
	}
	
}





















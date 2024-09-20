import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 연구소_14502 {
	static int N;
	static int M;
	static int[][] map;
	static List<int[]> list;
	static int result;
	static int[][] delta = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 세로
		M = sc.nextInt(); // 가로

		list = new ArrayList<>();
		
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();
				if(map[r][c]==2) {
					list.add(new int[] {r, c});
				}
			}
		}

		result = 0;
		build(3, 0, 0);
		
		System.out.println(result);

	}

	public static void build(int cnt, int n, int m) {
		if (cnt == 0) {
			find();
			return;
		}

		for (int r = n; r < N; r++) {
			for (int c = 0; c < M; c++) {
//				System.out.println(r + " " + c);
				if (map[r][c] == 0) {
					map[r][c]++;
					build(cnt - 1, r, c);
					map[r][c]--;
				}
			}
		}
	}

	public static void find() {
		
		boolean[][] visited = new boolean[N][M];
		
		Queue<int[]> templist = new LinkedList<>();
		for(int i = 0; i<list.size(); i++) {
			templist.add(new int[] {list.get(i)[0], list.get(i)[1]});
		}
		
		
		while(!templist.isEmpty()) {
			int[] temp = templist.poll();
			
			for(int d = 0; d<4; d++) {
				int nr = temp[0]+delta[d][0];
				int nc = temp[1]+delta[d][1];
				
				if(nr<N && nr>=0 && nc<M && nc>=0 && map[nr][nc]==0 && !visited[nr][nc]) {
					visited[nr][nc]=true;
					templist.add(new int[] {nr, nc});
				}
			}
		}
//		System.out.println("확인중임");
//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < M; c++) {
//				System.out.print(map[r][c]+ " ");
//			}
//			System.out.println();
//		}
		
		int sum = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c]==0 && !visited[r][c]) {
//					System.out.println(" " + r + " " + c);
					sum++;
				}
			}
		}
		
		result = Math.max(sum, result);

	}

}


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 아기상어_16236 {
	static int n;
	static int[][] map;
	
	static int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	static int result;
	static int eatcnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		map = new int[n][n];
		
		int starti = 0;
		int startj = 0;
		int startsize = 2;
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				map[i][j]=sc.nextInt();
				if(map[i][j]>0) {
					if(map[i][j]==9) {
						starti = i;
						startj = j;
						map[i][j]=0;
					}
				}
			}
		}
		
		result = 0;
		eatcnt = 0;
		
		bfs(starti, startj, startsize);
		
		System.out.println(result);
		
//		for(int i = 0; i<n; i++) {
//			for(int j = 0; j<n; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		
	}
	public static void bfs(int i, int j, int size) {
		Queue<int[]> list = new LinkedList<>();
		
		boolean eat = false;
		
		int[] next = {n, n, 0};
		
		boolean[][] visited = new boolean[n][n];
		list.add(new int[] {i, j, 0});
		visited[i][j]=true;
		
		while(!list.isEmpty()) {
			int[] temp = list.poll();
			int r = temp[0];
			int c = temp[1];
			int cnt = temp[2];
			
			for(int d = 0; d<4; d++) {
				int nr = r+delta[d][0];
				int nc = c+delta[d][1];
				
				if(nr<n && nr>=0 && nc<n && nc>=0 && !visited[nr][nc] && map[nr][nc]<=size) {
					if(map[nr][nc]==size || map[nr][nc]==0) {
						list.add(new int[] {nr, nc, cnt+1});
					}else {
						eat = true;
						if(nr<=next[0]) {
							if(nr<next[0]) {
								next[0] = nr;
								next[1] = nc;
								next[2] = cnt+1;
							}else {
								if(nc<next[1]) {
									next[0] = nr;
									next[1] = nc;
									next[2] = cnt+1;
								}
							}
							
						}
					}
					visited[nr][nc]=true;
				}
			}
			if(eat && (list.isEmpty() || list.peek()[2]==cnt+1)) {
				list.clear();
				list.add(new int[] {next[0], next[1], next[2]});
				map[next[0]][next[1]]=0;
				eatcnt++;
				if(eatcnt == size) {
					size++;
					eatcnt = 0;
				}
				result = next[2];
				visited = new boolean[n][n];
				next[0] = n;
				next[1] = n;
				next[2] = 0;
				eat = false;
			}
			
			
		}
		
		
	}

}

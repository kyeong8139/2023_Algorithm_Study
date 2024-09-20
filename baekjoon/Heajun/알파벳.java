import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 알파벳{
	static HashSet<Character> set = new HashSet<>();
	static char map[][];
	static boolean v[][];
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		Point start = new Point(1,1);
		
;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		v = new boolean[N+1][M+1];
		map= new char[N+1][M+1];
		for(int i = 1; i <= N; i++) {
			String str = br.readLine();
			for(int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j-1);
			}
		}
		dfs(start);
		System.out.println(max);
	}
	static int max = 1;
	public static void dfs(Point p) {
		if(set.contains(map[p.x][p.y])) {
			max = Math.max(max, set.size());
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = p.x + dr[i];
			int ny = p.y + dc[i];
			if(nx >= 1 && nx <= N && ny >= 1 && ny <= M) {
				set.add(map[p.x][p.y]);
				dfs(new Point(nx,ny));
				set.remove(map[p.x][p.y]);
			}
		}
	}
}

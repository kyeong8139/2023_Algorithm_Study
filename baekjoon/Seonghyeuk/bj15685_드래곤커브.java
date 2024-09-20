import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj15685_드래곤커브 {
	static int[][] map = new int[101][101];
	static int[][] delta = {{0,-1,0,1},{1,0,-1,0}};
	static List<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N;++i) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			list.add(d);
			fill(x,y,g,0,i+1);
		}
		int cnt = 0;
		for(int r=0; r<100;++r) {
			for(int c=0; c<100;++c) {
				if((map[r][c]!=0)&&(map[r][c+1]!=0)&&(map[r+1][c]!=0)&&(map[r+1][c+1]!=0)) {
					++cnt;
				}
			}
		}
		System.out.println(cnt);
	}
	public static void fill(int x, int y, int g, int cnt, int num) {
		map[y][x] = num;
		if(g==cnt-1) {
			return;
		}
		if(cnt==0) {
			int dir = list.get(0);
			int ny = y + delta[0][dir];
			int nx = x + delta[1][dir];
			if((ny>=0)&&(ny<101)&&(nx>=0)&&(nx<101)) {
				fill(nx, ny, g,cnt+1, num);
			}
		} else {
			List<Integer> list2 = new ArrayList<>();
			int ny = y;
			int nx = x;
			for(int i=list.size()-1; i>=0;--i) {
				int dir = (list.get(i)+1)%4;
				ny += delta[0][dir];
				nx += delta[1][dir];
				if((ny>=0)&&(ny<101)&&(nx>=0)&&(nx<101)) {
					map[ny][nx] = num;
				}
				list2.add(dir);
			}
			list.addAll(list2);
			fill(nx, ny, g,cnt+1, num);
		}
	}
}

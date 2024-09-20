import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] dirs = {{0, 1}, {-1, 0} , {0, -1}, {1, 0}};
	static int[] dirsLength = new int[11];
	static boolean[][] board = new boolean[101][101];	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dirsLength[0] = 1;
		dirsLength[1] = 1;
		int dirChangeSize = 2;
		for (int i = 2; i < dirsLength.length; i++) {
			dirsLength[i] = dirsLength[i-1] * 2;
			dirChangeSize += dirsLength[i];
		}
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		int[] dirChanges = new int[dirChangeSize];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			board[y][x] = true;
			dirChanges[0] = (d + 3) % 4;
			makeCurve(dirChanges, y, x, g, 0);
		}
		
		int answer = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1]) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}

	private static void makeCurve(int[] dirChanges, int r, int c, int g, int cur) {
		if (cur > g) {
			return;
		}
		
		int row = r;
		int col = c;
		for (int i = 1; i <= dirsLength[cur]; i++) {	
			int dir = (dirChanges[dirsLength[cur] - i] + 1) % 4;
			
			row += dirs[dir][0];
			col += dirs[dir][1];
			
			if (cur != 0) {
				dirChanges[dirsLength[cur] + i - 1] = dir;				
			} else {
				dirChanges[0] = dir;
			}
			
			board[row][col] = true;
		}
		
		makeCurve(dirChanges, row, col, g, cur + 1);
	}
	
}

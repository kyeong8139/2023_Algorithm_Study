import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int side;
	static boolean[][] isVisited;
	static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		side = (int) Math.pow(2, n);
		map = new int[side][side];
		
		for (int r = 0; r < side; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < side; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			int cur = Integer.parseInt(st.nextToken());
			
			if (cur > 0) {
				split((int) Math.pow(2, cur), side, 0, 0); 				
			}
			fire(side);
		}
		
		int sum = 0;
		int size = 0;
		isVisited = new boolean[side][side];
		for (int r = 0; r < side; r++) {
			for (int c = 0; c < side; c++) {
				sum += map[r][c];
				
				if (!isVisited[r][c] && map[r][c] > 0) {
					int result = getSize(r, c);
					size = Math.max(size, result);
				}
			}
		}
		
		System.out.println(sum);
		System.out.println(size);
	}
	
	private static int getSize(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		isVisited[r][c] = true;
		
		int size = 0;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			size++;
			
			for (int[] dir : dirs) {
				int nr = cur[0] + dir[0];
				int nc = cur[1] + dir[1];
				
				if (nr < 0 || nc < 0 || nr >= side || nc >= side || isVisited[nr][nc]) continue;
				
				if (map[nr][nc] > 0) {
					queue.offer(new int[] {nr, nc});
					isVisited[nr][nc] = true;
				}
			}
		}
		
		return size;
	}

	private static void fire(int length) {
		
		boolean[][] isFired = new boolean[length][length];
		
		for (int r = 0; r < length; r++) {
			for (int c = 0; c < length; c++) {
				
				if (map[r][c] == 0) continue;
				
				int iceCnt = 0;
				for (int[] dir : dirs) {
					int nr = r + dir[0];
					int nc = c + dir[1];
					
					if (nr < 0 || nc < 0 || nr >= length || nc >= length) continue;
					
					if (map[nr][nc] > 0) iceCnt++;
				}
				
				if (iceCnt < 3) isFired[r][c] = true;
			}
		}
		
		for (int r = 0; r < length; r++) {
			for (int c = 0; c < length; c++) {
				if (isFired[r][c]) {
					map[r][c] = Math.max(0, map[r][c] - 1);
				}
			}
		}
		
	}

	public static void split(int target, int side, int row, int col) {
		if (side == target) {
			rotate(row, col, side);
			return;
		}
		
		int newSide = side / 2;
		split(target, newSide, row, col);
		split(target, newSide, row+newSide, col);
		split(target, newSide, row+newSide, col+newSide);
		split(target, newSide, row, col+newSide);
	}
	
	private static void rotate(int row, int col, int length) {
	    for (int r = 0; r < length / 2; r++) {
	        for (int c = r; c < length - r - 1; c++) {
	            int top = map[row + r][col + c]; // 상단 저장

	            // 좌측 -> 상단
	            map[row + r][col + c] = map[row + length - 1 - c][col + r];

	            // 하단 -> 좌측
	            map[row + length - 1 - c][col + r] = map[row + length - 1 - r][col + length - 1 - c];

	            // 우측 -> 하단
	            map[row + length - 1 - r][col + length - 1 - c] = map[row + c][col + length - 1 - r];

	            // 상단 -> 우측
	            map[row + c][col + length - 1 - r] = top;
	        }
	    }
	}


//	private static void rotate(int row, int col, int length) {
//		int[][] temp = new int[side][side];
//		
//		int minRow = row;
//		int maxRow = row + length - 1;
//		int minCol = col;
//		int maxCol = col + length - 1;
//		
//		for (int i = 0; i < length / 2; i++) {
//			
//			// 상단
//			for (int j = i; j < length - i; j++) {
//				temp[minRow+j][maxCol-i] = map[minRow+i][minCol+j];
//			}
//			
//			// 우측
//			for (int j = i+1; j < length - i; j++) {
//				temp[maxRow-i][maxCol-j] = map[minRow+j][maxCol-i];
//			}
//			
//			// 하단
//			for (int j = i; j < length - i - 1; j++) {
//				temp[minRow+j][minCol+i] = map[maxRow-i][minCol+j];
//			}
//			
//			// 좌측
//			for (int j = i+1; j < length - i - 1; j++) {
//				temp[minRow+i][maxCol-j] = map[minRow + j][minCol+i];
//			}
//		}
//		
//		for (int r = row; r < row + length; r++) {
//			for (int c = col; c < col + length; c++) {
//				map[r][c] = temp[r][c];
//			}
//		}
//	}
}

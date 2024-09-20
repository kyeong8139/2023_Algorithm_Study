import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 테트로미노_14500 {

	static int n, m, arr[][], dx[] = {1, 0, -1, 0}, dy[] = {0, 1, 0, -1}, ans;
	static boolean[][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visit = new boolean[n][m];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

        ans = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				visit[i][j] = true;
				getSum(i, j, arr[i][j], 0);
				visit[i][j] = false;
			}
		}

		System.out.println(ans);
	}

	static void getSum(int i, int j, int sum, int count) {
		if(count == 3) {
			ans = Math.max(ans, sum);
			return;
		}

		for(int idx=0;idx<4;idx++) {
			int x = i + dx[idx];
			int y = j + dy[idx];

			if(isInRange(x, y) && !visit[x][y]) {
				if(count == 1) {
					visit[x][y] = true;
					getSum(i, j, sum + arr[x][y], count + 1);
					visit[x][y] = false;
				}

				visit[x][y] = true;
				getSum(x, y, sum + arr[x][y], count + 1);
				visit[x][y] = false;
			}
		}
	}

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}
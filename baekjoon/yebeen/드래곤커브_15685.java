import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 드래곤커브_15685 {
	static int[][] delta = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] map = new int[101][101];

		for (int tc = 0; tc < n; tc++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			int originx = Integer.parseInt(st.nextToken());
			int originy = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			int x = originx;
			int y = originy;

			List<Integer> list = new ArrayList<>();

			list.add(d);

			for (int r = 0; r < g; r++) {
				int size = list.size() - 1;

				for (int i = size; i >= 0; i--) {
					int temp = list.get(i);
					list.add((temp + 1) % 4);

				}
			}

			x = originx;
			y = originy;

			map[x][y]++;

			for (int i = 0; i < list.size(); i++) {
				int temp = list.get(i);
				x += delta[temp][0];
				y += delta[temp][1];
				if (x <= 100 && y <= 100 && x >= 0 && y >= 0) {
					map[x][y]++;
				}
			}

		}

		int result = 0;

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] > 0 && map[i + 1][j] > 0 && map[i][j + 1] > 0 && map[i + 1][j + 1] > 0) {
					result++;
				}
			}
		}

		System.out.println(result);
	}

}

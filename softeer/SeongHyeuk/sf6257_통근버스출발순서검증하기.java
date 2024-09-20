import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sf6257_통근버스출발순서검증하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int N = Integer.parseInt(str);
		int[] arr = new int[N];
		str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		long cnt = 0;
		for (int i = 0; i < N - 2; ++i) {
			int num1 = arr[i];
			int small = 0;
			for (int j = i + 1; j < N ; ++j) {
				if(num1>arr[j]) {
					++small;
				}
			}
			for (int j = i + 1; j < N - 1; ++j) {
				int num2 = arr[j];
				if (num1 < num2) {
					cnt += small;
				} else {
					--small;
				}
			}
		}
		System.out.println(cnt);
	}
}
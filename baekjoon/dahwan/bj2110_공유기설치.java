package study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj2110_공유기설치 {

	static int N, C;
	static int[] houses;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st;
//		Scanner sc = new Scanner(System.in);
		
//		N = sc.nextInt();
//		C = sc.nextInt();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		houses = new int[N];


		for (int i = 0; i < N; i++) {
//			houses[i] = sc.nextInt();
			houses[i] = Integer.parseInt(br.readLine());
		}

		int L = 0;
		int R = houses[N-1];
		
		Arrays.sort(houses);

		int mid = 0;
		int ans = 0;
		while (L <= R) {
			mid = (L + R) / 2;

			if (put(mid)) {
				L = mid + 1;
				ans = mid;
			} else {
				if(R == mid) break;
				R = mid;
			}
		}

		System.out.println(ans);
	}

	public static boolean put(int gap) {

		int before = houses[0];
		int cnt = 1;
		for (int i = 1; i < N; i++) {
			if (houses[i] - before >= gap) {
				cnt++;
				before = houses[i];
			}
			
			if(cnt == C) {
				return true;
			}
		}
		
		return false;
	}

}

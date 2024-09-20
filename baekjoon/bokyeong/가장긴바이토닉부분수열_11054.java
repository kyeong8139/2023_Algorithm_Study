import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] left = new int[n];
		left[0] = 1;
		for (int i = 1; i < n; i++) {
			left[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					left[i] = Math.max(left[i], left[j] + 1);
				}
			}
		}
		
		int[] right = new int[n];
		right[n-1]= 1;
		for (int i = n-2; i >= 0; i--) {
			right[i] = 1;
			for (int j = n-1; j > i; j--) {
				if (arr[j] < arr[i]) {
					right[i] = Math.max(right[i], right[j] + 1);
				}
			}
		}
		
		int max = 1;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, left[i] + right[i] -1);
		}
		
		System.out.println(max);
	}
}

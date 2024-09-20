import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 공유기설치_2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] list = new int[n];
		
		for(int i = 0; i<n; i++) {
			list[i]=Integer.parseInt(br.readLine());	
		}

		Arrays.sort(list);
		
		int min = 1;
		int max = list[n-1]-list[0]+1;
		
		
		while(min<max) {
			int mid = (min+max)/2;
			
			int cnt = 1;
			int ex = list[0];
			for(int i = 1; i<n; i++) {
				if(list[i]-ex >= mid) {
					cnt++;
					ex = list[i];
				}
			}
			
			if(cnt<c) {
				max = mid;
			}else {
				min = mid+1;
			}
			
		}
		
		System.out.println(min-1);
	}

}

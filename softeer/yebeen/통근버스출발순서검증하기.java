import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 통근버스출발순서검증하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] list = new int[n];
		for(int i = 0; i<n; i++) {
			list[i]=Integer.parseInt(st.nextToken());
		}
		long result = 0;
		for(int i = 0; i<n-2; i++) {
			
			int up = n-list[i];
			int down = list[i]-1;
			
			for(int j = i-1; j>=0; j--) {
				if(list[j]>list[i]) {
					up--;
				}else {
					down--;
				}
			}
			
			for(int j = i+1; j<n; j++) {
				if(list[j]>list[i]) {
					up--;
					result+=down;
				}else {
					down--;
				}
				
				if(down == 0 || up == 0) {
					break;
				}
			}
			
		}
		System.out.println(result);
		
	}
	

}

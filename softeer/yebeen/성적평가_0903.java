import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)-> b[1] - a[1]);

		int[] result = new int[n];

		for(int tc = 0; tc<3; tc++){
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int i = 0; i<n; i++){
				int temp = Integer.parseInt(st.nextToken());
				pq.add(new int[] {i, temp});
				result[i]+=temp;
			}

			int[] tempresult = new int[n];
			for(int i = 1; i<=n; i++){
				int[] temp = pq.poll();
				tempresult[temp[0]]=i;
				int t = 0;
				while(!pq.isEmpty() && pq.peek()[1]==temp[1]){
					temp = pq.poll();
					tempresult[temp[0]]=i;
					t++;
				}
				i+=t;
			}

			for(int i = 0; i<n; i++){
				sb.append(tempresult[i]).append(" ");
			}
			sb.append("\n");
		}

		for(int i = 0; i<n; i++){
			pq.add(new int[] {i, result[i]});
		}

		int[] tempresult = new int[n];
		for(int i = 1; i<=n; i++){
			int[] temp = pq.poll();
			tempresult[temp[0]]=i;
			int t = 0;
			while(!pq.isEmpty() && pq.peek()[1]==temp[1]){
				temp = pq.poll();
				tempresult[temp[0]]=i;
				t++;
			}
			i+=t;
		}

		for(int i = 0; i<n; i++){
			sb.append(tempresult[i]).append(" ");
		}

		System.out.print(sb);


	}
}

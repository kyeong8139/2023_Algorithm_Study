import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class sf6250_성적평가 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int N = Integer.parseInt(str);
		StringBuilder sb = new StringBuilder();
		int total[] = new int[N];
		for(int i=0; i<3;++i) {
			str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			Map<Integer,Integer> map = new HashMap<>();
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			int arr[] = new int[N];
			for(int j = 0; j<N;++j) {
				arr[j] = Integer.parseInt(st.nextToken());
				total[j] += arr[j];
				pq.add(arr[j]);
			}
			int idx = 1;
			while(!pq.isEmpty()) {
				Integer num = pq.poll();
				if(!map.containsKey(num)) {
					map.put(num, idx);
				}
				++idx;
			}
			for(int j = 0; j<N;++j) {
				sb.append(map.get(arr[j])).append(" ");
			}
			sb.append("\n");
		}
		Map<Integer,Integer> map = new HashMap<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int j = 0; j<N;++j) {
			pq.add(total[j]);
		}
		int idx = 1;
		while(!pq.isEmpty()) {
			Integer num = pq.poll();
			if(!map.containsKey(num)) {
				map.put(num, idx);
			}
			++idx;
		}
		for(int j = 0; j<N;++j) {
			sb.append(map.get(total[j])).append(" ");
		}
		System.out.println(sb);
	}
}

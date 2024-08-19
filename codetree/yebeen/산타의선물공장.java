import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int q = Integer.parseInt(st.nextToken());
		int n = 0;
		int m = 0;
		int result = m;

		Deque<Integer>[] list = new Deque[q];
		boolean[] isWork = new boolean[q];
		HashMap<Integer, Integer> map = new HashMap<>();
		HashMap<Integer, Integer> belt = new HashMap<>();

		for(int i = 0; i < q; i++){
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken()){
				case "100":
					n = Integer.parseInt(st.nextToken());
					m = Integer.parseInt(st.nextToken());
					isWork = new boolean[m];
					list = new Deque[m];
					for(int k = 0; k<m; k++){
						list[k] = new ArrayDeque<>();
						for(int j = 0; j<n/m; j++){
							list[k].addLast(Integer.parseInt(st.nextToken()));
						}
					}
					for(int k = 0; k<m; k++){
						for(int j = 0; j<(n/m); j++){
							int temp = list[k].pollFirst();
							belt.put(temp, k);
							map.put(temp, Integer.parseInt(st.nextToken()));
							list[k].addLast(temp);
						}
					}
					break;
				case "200":
					int w_max = Integer.parseInt(st.nextToken());
					result = 0;
					for(int k = 0; k<m; k++){
						if(!isWork[k] && list[k].size()>0){
							int temp = list[k].pollFirst();
							int tempw = map.get(temp);
							if(tempw<=w_max){
								result += tempw;
								belt.remove(temp);
								map.remove(temp);
							}else{
								list[k].addLast(temp);
							}
						}
					}
					System.out.println(result);
					break;
				case "300":
					int r_id = Integer.parseInt(st.nextToken());
					result = -1;
					if(belt.containsKey(r_id)){
						int idx = belt.get(r_id);
						result = r_id;
						int l = list[idx].size();
						for(int j = 0; j<l; j++){
							int temp = list[idx].pollFirst();
							if(temp!=r_id){
								list[idx].addLast(temp);
							}else{
								belt.remove(temp);
								map.remove(temp);
							}
						}
					}
					System.out.println(result);
					break;
				case "400":
					int f_id = Integer.parseInt(st.nextToken());
					result = -1;
					if(belt.containsKey(f_id)){
						int idx = belt.get(f_id);
						result = idx+1;
						for(int j = 0; j<list[idx].size(); j++){
							int temp = list[idx].pollFirst();
							if(temp!=f_id){
								list[idx].addLast(temp);
							}else{
								list[idx].addFirst(temp);
								break;
							}
						}
					}
					System.out.println(result);
					break;
				case "500":
					int b_num = Integer.parseInt(st.nextToken());
					result = b_num;
					if(!isWork[b_num-1]){
						int add = 0;
						while(true){
							if(!isWork[(b_num+add)%m]){
								int idx = (b_num+add)%m;
								int l = list[b_num-1].size();
								for(int j = 0; j<l; j++){
									int temp = list[b_num-1].pollFirst();
									list[idx].addLast(temp);
									belt.put(temp, idx);
								}
								isWork[b_num-1] = true;
								break;
							}else{
								add++;
							}
						}
					}else{
						result = -1;
					}
					System.out.println(result);
					break;

			}
		}

	}
}

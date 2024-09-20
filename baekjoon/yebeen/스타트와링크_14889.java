import java.io.IOException;
import java.util.Scanner;

public class new_14889_스타트와링크 {
	static int[] visited;
	static int[][] state;
	
	static int min;
	static int T;
	static int totalcnt;
	static int total;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		state = new int[T][T];
		for(int tc=0; tc<T; tc++) {
			for(int tc1=0; tc1<T; tc1++) {
				state[tc][tc1]=sc.nextInt();
			}
		}
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		T = Integer.parseInt(br.readLine());
//		state = new int[T][T];
//		for (int i = 0; i < T; i++) {
//			StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
//			for (int j = 0; j < T; j++) {
//				state[i][j] = Integer.parseInt(stringTokenizer.nextToken());
//			}
//		}
		
		min = 10000;
		visited = new int[T];
		
		find(0, 0);
		System.out.println(min);
		
	}
	
	public static void find(int cnt, int now) {
		
//		System.out.println(cnt +" "+ Arrays.toString(visited));
		if (cnt<(T/2)){
			for (int i = now; i<T; i++) { // now부터 시작하면서 조합처럼 숫자가 겹치지 않게 확인 가능하도록 함
				 if(visited[i]==0) {
					visited[i]=1;
					find(cnt+1, i+1);
					visited[i]=0;
				}
			}
		}else if (cnt == T/2){
			int sum1 = 0;
			int sum2 = 0;
			
			for (int i = 0; i<T; i++) {
				for (int j = 0; j<T; j++) {
					if(i!=j && visited[i]==1 && visited[j]==1 ) {
						sum1+=state[i][j];
					}
					if(i!=j && visited[i]==0 && visited[j]==0) {
						sum2 += state[i][j];
					}
				}
			}
//			System.out.println(min + " " + cnt + " " + sum1 + " " + sum2);
			min = Math.min(min, Math.abs(sum2-sum1));
			if(min == 0) {
				System.out.println(min);
				System.exit(0);
			}
		}
		
	}

}

/*
6
0 1 2 3 4 5
1 0 2 3 4 5
1 2 0 3 4 5
1 2 3 0 4 5
1 2 3 4 0 5
1 2 3 4 5 0
*/
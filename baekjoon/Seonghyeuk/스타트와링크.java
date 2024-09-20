import java.util.Scanner;
import java.util.Stack;

public class 스타트와링크 {
	static int[][] arr;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		arr = new int[N][N];
		for(int i =0 ; i<N ;++i) {
			for(int j =0 ; j<N ;++j) {
				arr[i][j] = sc.nextInt();
			}
		}
		Stack<Integer> start = new Stack<>();
		Stack<Integer> link = new Stack<>();
		divide(start,link,0,N);
		System.out.println(min);
	}
	
	
	public static void divide(Stack<Integer> st, Stack<Integer> li,int cnt, int num) {
		if (cnt>num) {
			return;
		}
		if((st.size()==num/2)&&(li.size()==num/2)) {
			int totalstart = 0;
			int totallink = 0;
			Object[] start = st.toArray();
			for(int i =0; i<start.length-1;++i) {
				int num1  = (int) start[i];
				for(int j =i+1; j<start.length;++j) {
					int num2  = (int) start[j];
					totalstart = totalstart+arr[num1][num2]+arr[num2][num1];
				}
			}
			Object[] link = li.toArray();
			for(int i =0; i<link.length-1;++i) {
				int num1  = (int) link[i];
				for(int j =i+1; j<link.length;++j) {
					int num2  = (int) link[j];
					totallink = totallink+arr[num1][num2]+arr[num2][num1];
				}
			}
			if(min>Math.abs(totalstart-totallink)) {
				min = Math.abs(totalstart-totallink);
			}
			return;
		}
		int player = cnt;
		if(st.size()<(num/2)) {
			st.add(player);
			divide(st,li,++cnt,num);
			st.pop();
			--cnt;
		}
		if(li.size()<(num/2)) {
			li.add(player);
			divide(st,li,++cnt,num);
			li.pop();
			--cnt;
		}
	}
}
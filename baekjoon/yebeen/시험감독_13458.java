import java.util.ArrayList;
import java.util.Scanner;

public class 시험감독_13458 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> student = new ArrayList<>();
		
		int T = sc.nextInt();
		
		for (int tc = 0; tc<T; tc++) {
			student.add(sc.nextInt());
		}
		
		long main = sc.nextInt();
		long sub = sc.nextInt();
		
		long result = T;
		
		for (int tc = 0; tc<T; tc++) {
			
			long cnt = student.get(tc)-main;
			
			if(cnt<0) {
				continue;
			}
			
			result += (long)cnt/sub;
			if (cnt%sub!=0){
				result++;
			}
			
		}
		System.out.println(result);
		
	}

}

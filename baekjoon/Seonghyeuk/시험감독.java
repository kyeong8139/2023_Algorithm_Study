import java.util.Scanner;

public class 시험감독 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] student = new int[N];
		for (int i = 0; i<N ; ++i) {
			student[i] = sc.nextInt();
		}
		int B = sc.nextInt();
		int C = sc.nextInt();
		long answer = N;
		for (int i = 0; i<N ; ++i) {
			if(student[i]>B) {
				if((student[i]-B)%C!=0) { // 나눈 나머지가 있다면 
					answer+= ((student[i]-B)/C)+1; // 몫에다가 1더한 값을 answer에 저장
				} else{ // 나머지가 없다면
					answer+= ((student[i]-B)/C); // 몫만큼만 저장
				}
			}
		}
		System.out.println(answer);
	}
}

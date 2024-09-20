package study_A;

import java.util.Scanner;

public class bj13485_시험감독 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 시험장 개수
		int[] people = new int[N]; // 응시자 수
		
		long sum = N;
		
		for(int i=0; i<N; i++) {
			people[i] = sc.nextInt();
		}
		
		int B = sc.nextInt(); // 총감독관 감시 가능 수
		int C = sc.nextInt(); // 부감독관 감시 가능 수
		
		for(int i=0; i<N; i++) {
			int temp = people[i]-B;
			
			if(temp <= 0) {
				continue;
			}
			
			if(temp % C == 0) {
				sum += temp / C;
			} else {
				sum += (temp / C) + 1;
			}
		}
		
		System.out.println(sum);
		
		
	}
}

package study_A;

import java.util.Arrays;
import java.util.Scanner;

public class bj14891_톱니바퀴 {
	
	static boolean[] wheel1 = new boolean[8]; // 바퀴 배열
	static boolean[] wheel2 = new boolean[8];
	static boolean[] wheel3 = new boolean[8];
	static boolean[] wheel4 = new boolean[8];
	static boolean[] canwheel = new boolean[5]; // 돌리기 여부 결정 배열
	static boolean[] direction = new boolean[5]; // 방향 여부 결정 배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 바퀴 배열에 원소 받기
		String s = sc.next();
		for(int i=0; i<8; i++) {
			if(s.charAt(i) == '1') {
				wheel1[i] = true;
			} else {
				wheel1[i] = false;
			}
		}
		
		String s2 = sc.next();
		for(int i=0; i<8; i++) {
			if(s2.charAt(i) == '1') {
				wheel2[i] = true;
			} else {
				wheel2[i] = false;
			}
		}
		
		String s3 = sc.next();
		for(int i=0; i<8; i++) {
			if(s3.charAt(i) == '1') {
				wheel3[i] = true;
			} else {
				wheel3[i] = false;
			}
		}
		
		String s4 = sc.next();
		for(int i=0; i<8; i++) {
			if(s4.charAt(i) == '1') {
				wheel4[i] = true;
			} else {
				wheel4[i] = false;
			}
		}
		
		// N이면 0, S면 1
		// N이면 false, S면 true
		
		int N = sc.nextInt();
		boolean dir = false;
		
		for(int i=0; i<N; i++) {
			int wheel = sc.nextInt();
			int direction = sc.nextInt();
			if(direction == 1) {
				dir = true; // 처음 방향 저장
			} else {
				dir = false;
			}
			check(wheel, dir);
		}
		
		// score의 12시 방향 확인
		int score = 0;
		if(wheel1[0]) {
			score += 1;
		}
		
		if(wheel2[0]) {
			score += 2;
		}
		
		if(wheel3[0]) {
			score += 4;
		}
		
		if(wheel4[0]) {
			score += 8;
		}
		
		System.out.println(score);
	}

	static void rolling(int wheel) {
		// 1 시계, -1 반시계
		// true 시계 방향, false 반시계
		if(direction[wheel]) {
			boolean temp = false;
			if(wheel == 1) { // 바퀴가 1이면
				temp = wheel1[7]; // 마지막 거 빼놓고
				for(int i=7; i>=1; i--) { // 다 앞으로 밀기
					wheel1[i] = wheel1[i-1];
				}
				wheel1[0] = temp; // 마지막 거 처음에 저장 -> 아래도 다 같음
			} else if(wheel == 2) {
				temp = wheel2[7];
				for(int i=7; i>=1; i--) {
					wheel2[i] = wheel2[i-1];
				}
				wheel2[0] = temp;
			} else if(wheel == 3) {
				temp = wheel3[7];
				for(int i=7; i>=1; i--) {
					wheel3[i] = wheel3[i-1];
				}
				wheel3[0] = temp;
			} else {
				temp = wheel4[7];
				for(int i=7; i>=1; i--) {
					wheel4[i] = wheel4[i-1];
				}
				wheel4[0] = temp;
			}
		} else {
			boolean temp = false;
			if(wheel == 1) { // 바퀴가 1이면
				temp = wheel1[0]; // 처음 것을 빼놓고
				for(int i=1; i<8; i++) { // 뒤로 당겨 온다
					wheel1[i-1] = wheel1[i];
				}
				wheel1[7] = temp; // 마지막 거에 처음 거 넣기 -> 아래도 다 같음
			} else if(wheel == 2) {
				temp = wheel2[0];
				for(int i=1; i<8; i++) {
					wheel2[i-1] = wheel2[i];
				}
				wheel2[7] = temp;
			} else if(wheel == 3) {
				temp = wheel3[0];
				for(int i=1; i<8; i++) {
					wheel3[i-1] = wheel3[i];
				}
				wheel3[7] = temp;
			} else {
				temp = wheel4[0];
				for(int i=1; i<8; i++) {
					wheel4[i-1] = wheel4[i];
				}
				wheel4[7] = temp;
			}
		}
	}
	
	static void check(int wheel, boolean dir) { // 모든 경우의 수 다 본 것
		// 1 시계, -1 반시계
		// 1 시계 방향, -1 반시계
		if(wheel == 1) { // 1이 돌 때
			if(wheel1[2] != wheel2[6]) { // 마주보는 바퀴 비교해서 다르면
				canwheel[2] = true; // 돌려야한다 표시
				direction[2] = !dir; // 돌리는 방향 표시
			}
			if(canwheel[2]) { // 2가 돌면
				if(wheel2[2] != wheel3[6]) { // 마주보는 바퀴 비교해서 다를 때
					canwheel[3] = true; // 돌린다 표시
					direction[3] = dir; // 돌리는 방향 표시
				}
			}
			if(canwheel[3]) { // 3이 돌면
				if(wheel3[2] != wheel4[6]) { // 마주보는 바퀴 비교해서 다를 때
					canwheel[4] = true; // 돌린다 표시
					direction[4] = !dir; // 돌리는 방향 표시 -> 아래도 다 같음
				}
			}
		} else if(wheel == 2) {
			if(wheel1[2] != wheel2[6]) {
				canwheel[1] = true;
				direction[1] = !dir;
			}
			if(wheel2[2] != wheel3[6]) {
				canwheel[3] = true;
				direction[3] = !dir;
			}
			if(canwheel[3]) {
				if(wheel3[2] != wheel4[6]) {
					canwheel[4] = true;
					direction[4] = dir;
				}
			}
		} else if(wheel == 3) {
			if(wheel3[2] != wheel4[6]) {
				canwheel[4] = true;
				direction[4] = !dir;
			}
			if(wheel2[2] != wheel3[6]) {
				canwheel[2] = true;
				direction[2] = !dir;
			}
			if(canwheel[2]) {
				if(wheel1[2] != wheel2[6]) {
					canwheel[1] = true;
					direction[1] = dir;
				}
			}
		} else {
			if(wheel4[6] != wheel3[2]) {
				canwheel[3] = true;
				direction[3] = !dir;
			}
			if(canwheel[3]) {
				if(wheel2[2] != wheel3[6]) {
					canwheel[2] = true;
					direction[2] = dir;
				}
			}
			if(canwheel[2]) {
				if(wheel1[2] != wheel2[6]) {
					canwheel[1] = true;
					direction[1] = !dir;
				}
			}
		}
		
		canwheel[wheel] = true; // 처음 돌리는 바퀴 돌리기 표시
		direction[wheel] = dir; // 방향 표시
		rolling(wheel); // 돌리기
		canwheel[wheel] = false; // 돌렸음을 표시
		
		if(canwheel[1]) { // 돌려야되면
			rolling(1); // 돌리고
			canwheel[1] = false; // 돌렸음을 표시
		}
		
		if(canwheel[2]) {
			rolling(2);
			canwheel[2] = false;
		}
		
		if(canwheel[3]) {
			rolling(3);
			canwheel[3] = false;
		}
		
		if(canwheel[4]) {
			rolling(4);
			canwheel[4] = false;
		}
		
	}
}

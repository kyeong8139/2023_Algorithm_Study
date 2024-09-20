package study;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bj14501_study {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 퇴사까지 남은 날짜
		
		List<Integer> days = new ArrayList<>();
		List<Integer> wages = new ArrayList<>();
		
		for(int i=0; i<N; i++) { // 걸리는 일수와 임금 저장
			int day = sc.nextInt();
			int wage = sc.nextInt();
			days.add(day);
			wages.add(wage);
		}
		
		List<Integer> totals = new ArrayList<>(); // 임금 합 저장할 리스트
					
		for(int i=0;i<(1<<N);i++) { // N개의 원소를 가진 집합의 모든 부분집합 뽑기
			int[] temp = new int[days.size()]; // 임시로 days리스트를 대신할 temp 배열
			for(int tp=0; tp<days.size(); tp++) {
				if(days.get(tp)+tp > days.size()) { // 퇴사일 이후로도 일해야 하는 날 -1로 만들기
					temp[tp] = -1;
				}
			}
		    for(int j=0;j<N;j++) {
		        if(((1<<j)&i)>0) {
		        	if(temp[j] != -1) { // 부분집합 j중에서 temp[j]가 -1이 아닌 곳이라면
		        		temp[j]=1; // 1로 만들기
		        	}
		        }
		    }
		    
		    start : for(int j=0; j<days.size(); j++) { // 가능한 날짜를 탐색하는 배열
		    	if(temp[j] != 1) { // 위에서 1로 바꾼 날이 아니거나, 퇴사일 이후로도 일해야 하는 날이라면
		    		continue start; // start로 이동
		    	} else { // 1로 바꾼 날이라면
		    		for(int k=j+1; k<j+days.get(j); k++) { // j일 이후부터 일해야 하는 일 수-1만큼 -1로 만들기
			    		temp[k] = -1;
			    	}
		    	}
		    }
		    
		    int total = 0;
		    for(int j=0; j<temp.length; j++) { // 합계 구하는 배열
		    	if(temp[j] == 1) { // 일할 수 있는 날이라면
		    		total = total + wages.get(j); // 임금 합하기
		    	}
		    }
		    totals.add(total);
		} // 가장 큰 for끝
		
		int max = Integer.MIN_VALUE; // 최대값 구하기
		for(int i=0; i<totals.size(); i++) {
			if(max < totals.get(i)) {
				max = totals.get(i);
			}
		}
		
		System.out.println(max);
	} // main
}

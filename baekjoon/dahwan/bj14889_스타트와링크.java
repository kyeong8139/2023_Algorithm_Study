package study_A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class bj14889_스타트와링크 {
	
	static int[][] arr;
	static List<List<Integer>> start;
	static List<List<Integer>> link;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] idx = new int[N];
		arr = new int[N][N];
		start = new ArrayList<>();
		link = new ArrayList<>();
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				arr[r][c] = sc.nextInt();
			}
		}
		
//		for(int r=0; r<N; r++) {
//			for(int c=0; c<N; c++) {
//				System.out.print(arr[r][c] + " ");
//			} System.out.println();
//		}
		
		for(int i=0; i<(1<<N); i++) { // 팀 나누기
			if(Integer.bitCount(i) == N/2) { // 인원 수의 반만 뽑는 부분집합
				int temp[] = new int[N]; // 임시 배열 만들어서
				for(int j=0; j<N; j++) {
					if(((1<<j) & i) > 0) {
						temp[j] = 1; // 겹치는 곳 1로 만들기
					}
				}
				List<Integer> teamstart = new ArrayList<>(); // 스타트팀 인원 저장할 임시 배열
				List<Integer> teamlink = new ArrayList<>(); // 링크팀 인원 저장할 임시 배열
				for(int k=0; k<N; k++) {
					if(temp[k] == 1) { // 1로 된 곳이면
						teamstart.add(k); // 스타트팀으로
					} else { // 0으로 된 곳이면
						teamlink.add(k); // 링크팀으로
					}
				}
				start.add(teamstart); // 스타트팀에 추가
				link.add(teamlink); // 링크팀에 추가
			}
		}
		
//		for(int i=0; i<start.size(); i++) {
//			for(int j=0; j<start.get(i).size(); j++) {
//				System.out.print(start.get(i).get(j) + " ");
//			} System.out.print(" / ");
//		}
//		
//		System.out.println();
//		
//		for(int i=0; i<link.size(); i++) {
//			for(int j=0; j<link.get(i).size(); j++) {
//				System.out.print(link.get(i).get(j) + " ");
//			} System.out.print(" / ");
//		}
//		
//		System.out.println();
		
		int min = Integer.MAX_VALUE;
		for(int i=0; i<start.size(); i++) {
			int startSynergy = 0;
			int linkSynergy = 0;
			for(int j=0; j<N/2; j++) {
				for(int k=j+1; k<N/2; k++) {
					startSynergy += arr[start.get(i).get(j)][start.get(i).get(k)];
					startSynergy += arr[start.get(i).get(k)][start.get(i).get(j)];
					linkSynergy += arr[link.get(i).get(j)][link.get(i).get(k)];
					linkSynergy += arr[link.get(i).get(k)][link.get(i).get(j)];
				}
			}
			
			int gap = startSynergy - linkSynergy;
			
			if(gap >= 0) {
				min = Math.min(min, gap);
			} else {
				min = Math.min(min, -gap);
			}
			
		}
		
		System.out.println(min);
		
	}
}

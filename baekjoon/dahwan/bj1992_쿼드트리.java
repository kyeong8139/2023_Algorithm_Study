package study;

import java.util.Scanner;

public class bj1992_study2 {
	
	public static int N;
	public static String[][] quadTree;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		quadTree = new String[N][N];
		
		for(int r=0; r<quadTree.length; r++) {
			String temp[] = sc.next().split("");
			for(int c=0; c<quadTree[r].length; c++) {
				quadTree[r][c] = temp[c];
			}
		}
		
		boolean isSame = true; // 전체가 다 같은지 확인
		for(int r=1; r<N; r++) {
			for(int c=1; c<N; c++) {
				if(!quadTree[r][c].equals(quadTree[r][c-1]) || !(quadTree[r][c].equals(quadTree[r-1][c])) || !(quadTree[r-1][c-1].equals(quadTree[r-1][c])) ) {
					isSame = false; // 다르다면 false로 바꾸기
				}
			}
		}
		
		if(isSame) { // 같다면 한 개의 숫자로 출력
			System.out.println(quadTree[0][0]);
		} else { // 다를 때 사분면으로 나눠서 체크
			String a = quadcheck1(quadTree, N/2, 0, 0);
			String b = quadcheck2(quadTree, N/2, 0, N/2);
			String c = quadcheck3(quadTree, N/2, N/2, 0);
			String d = quadcheck4(quadTree, N/2, N/2, N/2);
			
			System.out.println("("+ a + b + c + d +")");
		}
		
		
	}

	public static String quadcheck1(String[][] arr, int N, int row, int col) { // 1사분면 확인
		if(N == 1) { // 1일 때는 자기 자신 리턴
			return (arr[row][col]);
		}
		
		boolean isOk = true; // 전부 다 같은지 확인하는 변수
		start : for(int r=row+1; r<row+N; r++) { // 1사분면의 범위
			for(int c=col+1; c<col+N; c++) { 
				if(!arr[r][c].equals(arr[r][c-1]) || !(arr[r][c].equals(arr[r-1][c])) || !(arr[r-1][c-1].equals(arr[r-1][c])) ) { // 전부 다 같은 것이 아니면
					isOk = false; // false로 바꾸기
					break start; // for문 끝내기
				}
			}
		}
		
		if(isOk) { // 다 같으면 그대로 같은 한 개만 출력
			return (arr[row][col]);
		} 
		// 다르다면 다시 사분면 체크
		return "(" + quadcheck1(arr, N/2, row, col) + quadcheck2(arr, N/2, row, col+(N/2)) + quadcheck3(arr, N/2, row+(N/2), col) + quadcheck4(arr, N/2, row+(N/2), col+(N/2)) + ")";
	}
	
	// 아래부터 동일 범위 설정만 다름
	
	public static String quadcheck2(String[][] arr, int N, int row, int col) { // 2사분면 확인
		if(N == 1) {
			return (arr[row][col]);
		}
		boolean isOk = true;
		start : for(int r=row+1; r<row+N; r++) {
			for(int c=col+1; c<col+N; c++) {
				if(!arr[r][c].equals(arr[r][c-1]) || !(arr[r][c].equals(arr[r-1][c])) || !(arr[r-1][c-1].equals(arr[r-1][c])) ) {
					isOk = false;
					break start;
				}
			}
		}
		
		if(isOk) {
			return (arr[row][col]);
		} 
		
		return "(" + quadcheck1(arr, N/2, row, col) + quadcheck2(arr, N/2, row, col+(N/2)) + quadcheck3(arr, N/2, row+(N/2), col) + quadcheck4(arr, N/2, row+(N/2), col+(N/2)) + ")";		
	}
	
	public static String quadcheck3(String[][] arr, int N, int row, int col) { // 3사분면 확인
		if(N == 1) {
			return (arr[row][col]);
		}
		boolean isOk = true;
		start : for(int r=row+1; r<row+N; r++) {
			for(int c=col+1; c<col+N; c++) {
				if(!arr[r][c].equals(arr[r][c-1]) || !(arr[r][c].equals(arr[r-1][c])) || !(arr[r-1][c-1].equals(arr[r-1][c])) ) {
					isOk = false;
					break start;
				}
			}
		}
		
		if(isOk) {
			return (arr[row][col]);
		} 
		
		return "(" + quadcheck1(arr, N/2, row, col) + quadcheck2(arr, N/2, row, col+(N/2)) + quadcheck3(arr, N/2, row+(N/2), col) + quadcheck4(arr, N/2, row+(N/2), col+(N/2)) + ")";
	}
	
	public static String quadcheck4(String[][] arr, int N, int row, int col) { // 4사분면 확인
		if(N == 1) {
			return (arr[row][col]);
		}
		boolean isOk = true;
		start : for(int r=row+1; r<row+N; r++) {
			for(int c=col+1; c<col+N; c++) {
				if(!arr[r][c].equals(arr[r][c-1]) || !(arr[r][c].equals(arr[r-1][c])) || !(arr[r-1][c-1].equals(arr[r-1][c])) ) {
					isOk = false;
					break start;
				}
			}
		}
		
		if(isOk) {
			return (arr[row][col]);
		} 
		
		return "(" + quadcheck1(arr, N/2, row, col) + quadcheck2(arr, N/2, row, col+(N/2)) + quadcheck3(arr, N/2, row+(N/2), col) + quadcheck4(arr, N/2, row+(N/2), col+(N/2)) + ")";	}
	
}

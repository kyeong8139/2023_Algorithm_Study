import java.util.Scanner;
//주석
public class 쿼드트리_1992 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			String str = sc.next();
			for(int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0'; //영상의 요소를 입력받음
			}
		}
		Divide(map,0,0,N);
		System.out.println();
	}
	
	public static void Divide(int[][] map,int x, int y, int N) {
		int value = map[x][y];
		loop : for(int i = x; i < x+N; i++) {
			for(int j = y; j < y+N; j++) {
				if(value != map[i][j]) {
					value = -1; //일정 크기만큼 돌다가 값이 다를 경우 반복 종료
					break loop;
				}
			}
		}
		
		
		if(value==-1) {
			N /= 2; //N의 크기를 반으로 줄임
			System.out.print('(');
			Divide(map,x,y,N); //상좌
			Divide(map,x,y+N,N); //상우
			Divide(map,x+N,y,N); //하좌
			Divide(map,x+N,y+N,N); //하우
			System.out.print(')');
		}
		else if(value == 0) {
			System.out.print(0); //크기만큼 돌면서 다 같은 숫자를 출력
		}
		else if(value==1) {
			System.out.print(1);
		}
		
		
		
	}
}

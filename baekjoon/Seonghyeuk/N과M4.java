import java.util.Scanner;

public class N과M4 {
	static int N;
	static int M;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[M]; 
		permutation(0);
	}
	
	public static void permutation(int num){
		if(num == M) {
			for(int i = 0; i<M;++i) {
				System.out.print(arr[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i = 1; i<N+1;++i) {
			if(num == 0) {
				arr[num] = i;
				permutation(num+1);
 			} else {
 				if(arr[num-1]<= i) {
 					arr[num] = i;
 					permutation(num+1);
 				}
 			}
		}
	} 
}

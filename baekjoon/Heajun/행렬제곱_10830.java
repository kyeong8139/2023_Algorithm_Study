import java.util.*;
public class 행렬제곱_10830 {
    static int[][] arr;
    static int N;
    static long M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = Long.parseLong(sc.next());
        arr = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                arr[i][j] = sc.nextInt() % 1000;
            }
        }
        int[][] answer = divide(arr,M);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static int[][] divide(int[][] arr1,long m){
        if(m==1L){
            return arr1;
        }
        int tmp[][] = divide(arr1,m/2);
        tmp = mularr(tmp,tmp);
        if(m%2 == 1L){
            tmp = mularr(tmp,arr);
        }
        return tmp;
    }

    public static int[][] mularr(int[][] arr1, int[][] arr2) {
        int[][] ans = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    ans[i][j] += arr1[i][k] * arr2[k][j];
                    ans[i][j] %= 1000;
                }

            }
        }


        return ans;
    }

}

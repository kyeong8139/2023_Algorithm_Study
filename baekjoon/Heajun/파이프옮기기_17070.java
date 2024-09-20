import java.util.Scanner;

public class 파이프옮기기_17070 {
    static int[][] map;
    static int N;
    static int cnt;
    static int[] di = {0,1,1};
    static int[] dj = {1,0,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        dfs(0,1,0);
        System.out.println(cnt);
    }

    public static boolean check(int i, int j) {
        if(i >= 0 && i < N && j >= 0 && j < N) //다음으로 향할 곳이 범위 내일 경우
            return true;
        return false;
    }

    public static void dfs(int i, int j, int idx) {
        if(i==N-1 && j == N-1) {
            cnt++;
            return;
        }
        for(int k = 0; k < 3;k++) {

            int ni = i+di[k];
            int nj = j+dj[k];
            //범위 안이고, 벽이 아니면서 , 대각선일 때 벽이 아닌 경우 
            if(check(ni,nj)&&map[ni][nj]!= 1) {
                if( k==2 && (map[i+1][j]== 1 || map[i][j+1] == 1))
                    continue;
                if((idx==0 && k==1) || (idx==1 &&k==0))
                    continue; //가로일 떄 세로, 세로일 때 가로 금지
                dfs(ni,nj,k);
            }

        }


    }

}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 배열돌리기4_17406 {
	static int cnt = 0;
    static int[][] map;
    static int[] perm;
    static int N;
    static int M;
    static int K;
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[][] rotate;
    static int answer = Integer.MAX_VALUE;
    static int[][] clear;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();


        map = new int[N+1][M+1];
        clear = new int[N+1][M+1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                map[i][j] = sc.nextInt();
                clear[i][j] = map[i][j];
            }
        }
        rotate = new int[K][3];
        perm = new int[K];
        visited = new boolean[K];
        for(int i =0 ; i<K; i++){
            rotate[i][0] = sc.nextInt();
            rotate[i][1] = sc.nextInt();
            rotate[i][2] = sc.nextInt();
        }
        Perm(0);
        System.out.println(answer);
        //System.out.println(cnt);
    }
    public static void init(){
        for(int i =1;i <= N; i++){
            for(int j = 1; j <= M; j++){
                map[i][j] = clear[i][j];
            }
        }
    }
    public static void Perm(int n){
        if(n==K){
            init();
            cnt++;
            for(int i = 0; i < K; i++){
                Rotate(rotate[perm[i]][0],rotate[perm[i]][1],rotate[perm[i]][2]);
            }
            int sum = searchMin();
            answer = Math.min(answer,sum);
        }
        for(int i = 0; i <K;i++){
            if(visited[i])
                continue;
            perm[n] = i;
            visited[i] = true;
            Perm(n+1);
            visited[i] = false;

        }
    }
    public static int searchMin(){
        min = Integer.MAX_VALUE;
        for(int i = 1; i <= N; i++) {
            int sum = 0;
            for(int j = 1; j <= M; j++) {
                sum += map[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }
    public static void Rotate(int r, int c, int s){
        int x1 = r-s;
        int y1 = c- s;
        int x2 = r+s;
        int y2 = c + s;
        int [][] tmp = new int [N+1][M+1];
        while(s-- > 0){
            for(int j = y1; j < y2; j++){
                tmp[x1][j+1] = map[x1][j];
            }
            for(int j = x1; j < x2; j++){
                tmp[j+1][y2] = map[j][y2];
            }
            for(int j = y2; j > y1; j--){
                tmp[x2][j-1] = map[x2][j];
            }
            for(int j = x2; j > x1; j--){
                tmp[j-1][y1] = map[j][y1];
            }
            x1 += 1;
            x2 -= 1;
            y1 += 1;
            y2 -= 1;

        }for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(tmp[i][j]!= 0){
                    map[i][j] = tmp[i][j];
                }
            }
        }

    }
}

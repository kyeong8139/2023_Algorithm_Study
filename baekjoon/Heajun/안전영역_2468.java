import java.util.Scanner;

public class 안전영역_2468 {
    static int max = 0; //최종 많은 안전지역을 저장
    static int cnt = 0; //한번 dfs에 진입했을 때 안전횟수가 있는지 판단여부

    static int N =0; //배열의 크기
    static int visited[][]; //방문 여부를 확인
    static int[][] map; //맵 정보 저장
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        map = new int[N][N];

        int max_num = 2; //입력값이 2부터이기 때문에 시작값은 2
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                max_num = Math.max(max_num, map[i][j]); //가장 높은 높이가 어디인지 확인
            }
        }

        for(int i = 0; i <= max_num; i++){
            check(i); //방문 여부를 초기화 및 비 높이 이하일 경우 방문으로 변경
            int count = 0;
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    dfs(j,k,i); //탐색 시작
                    count += cnt > 0 ? 1 : 0; //만약 dfs의 조건문을 통과한 경우 한 칸이라도 공간이 있음을 의미
                    cnt = 0;//초기화
                }

            }
            max = Math.max(max,count); //비가 온 높이의 사이클이 종료하면 최대값 갱신

        }
        System.out.println(max);
    }
    public static void check(int n){
        visited = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] <= n)
                    visited[i][j] = 1; //높이 이하일 경우 방문 처리
            }
        }
    }
    public static void dfs(int x, int y, int n){

        if(x >= 0 && y >= 0 && x < N && y < N && (visited[x][y]!= 1)){
            visited[x][y] = 1;
            cnt++;
            dfs(x,y+1,n);
            dfs(x+1,y,n);
            dfs(x,y-1,n);
            dfs(x-1,y,n);
        }


    }

}

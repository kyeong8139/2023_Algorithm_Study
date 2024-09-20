import java.util.Scanner;

public class 스타트와링크_14889 {
    static boolean[] visited;
    static int[][] map;
    static int min = Integer.MAX_VALUE;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visited = new boolean[N+1];
        map = new int[N+1][N+1];
        for(int i = 1; i <=N; i++){
            for(int j = 1; j <= N; j++){
                map[i][j] = sc.nextInt();
            }
        }

        dfs(1,0);
        System.out.println(min);
    }

    static void dfs(int idx, int cnt){
        if(cnt==N/2){ //절반으로 팀의 모든 경우의 수 계산
            team();
            return;
        }
        for(int i = idx; i <= N; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i+1,cnt+1);
                visited[i] = false;
            }
        }
    }

    static void team(){
        int start = 0;
        int link = 0;
        for(int i = 1; i <= N; i++){
            for(int j = i; j <= N; j++){
                if(i!=j&visited[i] && visited[j]){ //true의 경우 합하고
                    start += map[i][j] + map[j][i];
                }
                else if(i!=j&!visited[i]&&!visited[j]){ //false의 경우 합하는 것으로 반반을 맞춤
                    link += map[i][j] + map[j][i];
                }
            }
        } //각 팀별로 점수 완료
        int diff = Math.abs(start - link);
        if(diff==0){
            System.out.println(0);
            System.exit(0);
        }
        min = Math.min(min, diff); //절대값함수를 이용하면서 차가 가장 작은 경우를 출력

    }
}

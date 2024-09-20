
import java.util.Scanner;

public class 경로찾기_11403 {
    static int[][] v; //경로의 방향을 저장하는 배열
    static int[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N =sc.nextInt();
        v = new int[N][N]; //각 점 별로 갈 수 있는 경로 확인
        visited = new int[N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int value = sc.nextInt();
                if(value==1){
                    v[i][j] = j; //값이 1인 경우 경로가 존재하는 것이기 때문에 시작하는 값은 i이고 방향은 j로 저장
                }else{
                    v[i][j] = -1; //0방향도 있을 수 있기 때문에 차별점을 위해 없는 경우는 -1로 표현
                }
            }
        }//반복문 종료
        for(int i = 0; i < N; i++){
            visited = new int[N];
            dfs(i);
            for(int j = 0; j < N; j++){ //한줄 이 종료하면 출력
                System.out.print(visited[j] + " ");
            }
            System.out.println();

        }
    }

    public static void dfs(int n){ //한 줄마다 경로에 대한 정보를 dfs로 찾는다.
        for(int i = 0; i < v[n].length; i++){
            if(v[n][i]!=-1 && visited[v[n][i]] == 0){ //-1이 아닌 즉 경로가 있으며 아직 방문하지 않은 경우
                visited[v[n][i]] = 1; //갱신
                dfs(v[n][i]); // 다음 경로로 이동
            }

        }
    }
}

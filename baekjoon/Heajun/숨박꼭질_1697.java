import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class point{ //큐를 생성할 때 두 값을 저장하고 싶기 때문
    int pos;
    int time;
    point(int pos,int time){
        this.pos = pos;
        this.time = time;
    }
}
public class 숨박꼭질_1697 {
    static int N;
    static int K;
    static int min;
    static int[] visited = new int[100001];
    static Queue<point> q = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N =sc.nextInt();
        K = sc.nextInt();
        visited[N] = 1;
        bfs(N);
        System.out.println(min);
    }

    public static void bfs(int x){
        q.add(new point(x,0));

        while(!q.isEmpty()){
            int X = q.peek().pos;
            int second = q.peek().time;
            q.poll();
            if(X == K){ //위치가 같아지면 종료
                min = second; //최소값 비교를 안하는 이유는 너비우선이기 때문에 시간열이 같기 때문
                break;
            }
            if(X+1 < visited.length && X+1 >= 0&& visited[X+1]==0){ //다음 위치가 범위를 벗어나지 않을 경우 방문처리하고 큐에 추가한다.
                visited[X+1] = 1;
                q.add(new point(X+1,second+1));
            }

            if(X-1 < visited.length && X-1 >= 0&& visited[X-1]==0){
                visited[X-1] = 1;
                q.add(new point(X-1,second+1));
            }

            if(2*X < visited.length && 2*X >= 0 && visited[2*X]==0){
                visited[2*X] = 1;
                q.add(new point(2*X,second+1));
            }


        }


    }
}

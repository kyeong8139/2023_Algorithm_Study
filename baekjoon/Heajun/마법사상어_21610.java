import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pot{
    int water;
    boolean crowded = false; //직전에 구림이 있었는지 확인
    Pot(int n){
        this.water = n;
    }

}

class Crowd{
    int x;
    int y;
    Crowd(){

    }
    Crowd(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class 마법사상어_21610 {
    //바구니 칸, 물의 양, 구름의 위치 저장
    static Pot[][] pots;
    static int[] di = {0,-1,-1,-1,0,1,1,1};
    static int[] dj = {-1,-1,0,1,1,1,0,-1};
    static int N;
    static List<Crowd> crowds;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N =sc.nextInt();
        int M = sc.nextInt();
        pots = new Pot[N+1][N+1];
        crowds = new ArrayList<>();
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <=N;j++){
                pots[i][j] = new Pot(sc.nextInt());
            }
        }

        start(); // 초기 구름 위치 생성
        for(int i = 0; i < M; i++){
            move(sc.nextInt()-1,sc.nextInt());
            waterbug();
            crowds = new ArrayList<>(); // 초기화
            makeCrowd(); // 구름 생성
            reset();
        }
        int sum = 0;
        for(int i = 1; i<= N; i++){
            for(int j =1; j <= N; j++){
                sum += pots[i][j].water;
            }
        }
        System.out.println(sum);
    }

    public static void start(){
        crowds.add(new Crowd(N,1));
        crowds.add(new Crowd(N,2));
        crowds.add(new Crowd(N-1,1));
        crowds.add(new Crowd(N-1,2));
    }

    public static void move(int d, int n){
        for(int i = 0; i < crowds.size(); i++){
            crowds.get(i).x += di[d] * n;
            crowds.get(i).x = crowds.get(i).x > N ? crowds.get(i).x%N:crowds.get(i).x;
            crowds.get(i).x = crowds.get(i).x < 1 ? N + crowds.get(i).x%N:crowds.get(i).x;

            crowds.get(i).y += dj[d] * n; //주어진 만큼 이동
            crowds.get(i).y = crowds.get(i).y > N ? crowds.get(i).y%N:crowds.get(i).y;
            crowds.get(i).y = crowds.get(i).y < 1 ? N + crowds.get(i).y%N:crowds.get(i).y;
        }//이동 종료
        for(int i = 0; i < crowds.size(); i++){
            pots[crowds.get(i).x][crowds.get(i).y].water++;
            pots[crowds.get(i).x][crowds.get(i).y].crowded = true;
        }//이동 종료
    }

    public static void waterbug(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                int cnt = 0;
                for(int k = 1; k <= 7; k+=2){
                    if(pots[i][j].crowded){
                        int ni = i + di[k];
                        int nj = j + dj[k];
                        if(ni >= 1 && ni <= N && nj>=1&&nj<=N){
                            if(pots[ni][nj].water>0)
                                cnt++;//칸을 벗어나지 않는 대각선의 바구니에 물이 있을 경우
                        }
                    }
                }
                pots[i][j].water+=cnt;

            }
        }

    }

    public static void makeCrowd(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(!pots[i][j].crowded && pots[i][j].water >= 2){
                    pots[i][j].water -= 2;
                    crowds.add(new Crowd(i,j));
                }
            }
        }
    }
    public static void reset(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                pots[i][j].crowded = false;
            }
        }

    }



}

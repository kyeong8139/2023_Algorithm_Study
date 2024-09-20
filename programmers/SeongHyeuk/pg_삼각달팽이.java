import java.util.Arrays;
import java.util.Scanner;

public class 삼각달팽이 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] answer = solution(N);
        System.out.println(Arrays.toString(answer));
    }

    public static int[] solution(int n) {
        int N = (int) (n*(n+1)/2);
        int[][] snail = new int[n][];
        for(int i = 0; i<n;++i){
            int[] arr = new int[i+1];
            snail[i] = arr;
        }
        int num = 1;
        int dir = 0;
        int row = 0;
        int col = 0;
        while(num<=N){
            snail[row][col] = num++;
            if(dir==0){
                if((row+1<n)&&(snail[row+1][col]==0)){
                    row+=1;
                } else {
                    dir+=1;
                    if((col+1<row+1)&&(snail[row][col+1]==0)){
                        col+=1;
                    }
                }
            } else if(dir==1){
                if((col+1<row+1)&&(snail[row][col+1]==0)){
                    col+=1;
                } else {
                    dir+=1;
                    if((col-1>=0)&&(row-1>=0)&&(snail[row-1][col-1]==0)){
                        row-=1;
                        col-=1;
                    }
                }
            } else if(dir==2){
                if((col-1>=0)&&(row-1>=0)&&(snail[row-1][col-1]==0)){
                    row-=1;
                    col-=1;
                } else {
                    dir=0;
                    if((row+1<n)&&(snail[row+1][col]==0)){
                        row+=1;
                    }
                }
            }
        }

        int[] answer = new int[N];
        int idx = 0;
        for(int i =0; i<n;++i){
            for(int j=0; j<i+1;++j){
                answer[idx++] = snail[i][j];
            }
        }
        return answer;
    }
}

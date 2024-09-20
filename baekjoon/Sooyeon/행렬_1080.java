import java.util.Arrays;
import java.util.Scanner;

public class 행렬_1080 {

    static char[][] firstArr = null;
    static char[][] secondArr = null;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().split(" ")[0]);
        firstArr = new char[n][];
        secondArr = new char[n][];

        for(int i=0;i<n;i++) {
            firstArr[i] = sc.nextLine().toCharArray();
        }

        for(int i=0;i<n;i++) {
            secondArr[i] = sc.nextLine().toCharArray();
        }

        if(Arrays.deepEquals(firstArr, secondArr)) { //처음부터 두 행렬이 같다면 0 출력
            System.out.println("0");
        } else {
            check(0, 0);
            if(!Arrays.deepEquals(firstArr, secondArr)) { //두 행렬이 다르다면 -1출력
                System.out.println("-1");
            } else {
                System.out.println(ans);
            }
        }

    }
    static void check(int x, int y) {
        if(x>=0 && x<firstArr.length-2 && y >= 0 && y < firstArr[0].length-2) { //3*3 이 되는 값만
            if(firstArr[x][y] != secondArr[x][y]) { //x,y에서 두 행렬 값이 다르다면
                for(int i=x;i<x+3;i++) {
                    for(int j=y;j<y+3;j++) { //x, y기준 3*3 만큼 0은 1로, 1은 0으로 변경
                        firstArr[i][j] = firstArr[i][j]== '0' ? '1' : '0';
                    }
                }
                ans++; //횟수 올리기
            }
            if(y==firstArr[x].length-3) { //y+2가 열 값을 넘어간다면 다음 행 탐색
                check(x+1, 0);
            } else {
                check(x, y+1); //다음 열 탐색
            }
        }
    }
}

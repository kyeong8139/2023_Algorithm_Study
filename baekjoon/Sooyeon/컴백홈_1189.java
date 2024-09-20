import java.util.Scanner;

public class 컴백홈_1189 {
    static int routeCnt = 0;
    static int ansCnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] temp = sc.nextLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);

        routeCnt= Integer.parseInt(temp[2]);

        char[][] routeSave = new char[n][m];
        // int[][] routeSave = new int[n][m];


        for(int i=0;i<n;i++) {
            routeSave[i] = sc.nextLine().toCharArray();
        }

        getRoute(n-1, 0, routeSave, 1);
        System.out.println(ansCnt);
    }

    //(n-1, 0)에서 (0, m-1)까지 cnt로 가는 가지수
    static void getRoute(int x, int y, char[][] routeSave, int cnt) {

        if (x == 0 && y == routeSave[0].length - 1 && cnt == routeCnt) { // 목적지 도달 및 거리가 맞는지 확인
            ++ansCnt;
            return; // 경로가 완성되었으므로 종료
        }

        // 온 좌표 T로 바꾸기
        routeSave[x][y] = 'T';

        // 상
        if (x - 1 >= 0 && routeSave[x - 1][y] != 'T') {
            getRoute(x - 1, y, routeSave, cnt + 1);
        }

        // 하
        if (x + 1 < routeSave.length && routeSave[x + 1][y] != 'T') {
            getRoute(x + 1, y, routeSave, cnt + 1);
        }

        // 좌
        if (y - 1 >= 0 && routeSave[x][y - 1] != 'T') {
            getRoute(x, y - 1, routeSave, cnt + 1);
        }

        // 우
        if (y + 1 < routeSave[x].length && routeSave[x][y + 1] != 'T') {
            getRoute(x, y + 1, routeSave, cnt + 1);
        }

        // 다시 원래 상태로 되돌리기.
        routeSave[x][y] = '.';
    }
}

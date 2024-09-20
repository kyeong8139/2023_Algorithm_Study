import java.io.*;
import java.util.*;

public class 미세먼지안녕느낌표_17144 {

    static class Location {
        int x, y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Location airCon;

    static int R, C, T, arr[][], temp[][];
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[R][C];

        for(int i=0;i<R;i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0;j<C;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == -1 && airCon == null) {
                    airCon = new Location(i, j);
                }
            }
        }

        for(int time = 1; time <= T; time++) {
            temp = new int[R][C];

            //미세먼지 확산
            for(int i=0;i<R;i++) {
                for(int j=0;j<C;j++) {
                    if(arr[i][j] != 0) {
                        //확산하기
                        int amount = arr[i][j] / 5;
                        int cnt = 0;
                        for(int c=0;c<4;c++) {
                            try {
                                if(arr[i+dx[c]][j+dy[c]] == -1) continue;
                                temp[i+dx[c]][j+dy[c]] += amount;
                                cnt++;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                // TODO: handle exception
                            }
                        }
                        arr[i][j] -= amount * cnt;
                    }
                }
            }

            for(int i=0;i<R;i++) {
                for(int j=0;j<C;j++) {
                    arr[i][j] += temp[i][j];
                }
            }

            //공기청정기 작동
            int x1 = airCon.x, x2 = airCon.x+1;

            //공기청정기 1
            for(int i=x1-1; i > 0 ;i--) {
                arr[i][0] = arr[i-1][0];
            }
            for(int j=0;j<C-1;j++) {
                arr[0][j] = arr[0][j+1];
            }
            for(int i=0;i<x1;i++) {
                arr[i][C-1] = arr[i+1][C-1];
            }
            for(int j=C-1;j>1;j--) {
                arr[x1][j] = arr[x1][j-1];
            }
            arr[x1][1] = 0;

            

            //공기청정기 2
            for(int i=x2+1; i < R-1 ;i++) {
                arr[i][0] = arr[i+1][0];
            }
            for(int j=0;j<C-1;j++) {
                arr[R-1][j] = arr[R-1][j+1];
            }
            for(int i=R-1;i>x2;i--) {
                arr[i][C-1] = arr[i-1][C-1];
            }
            for(int j=C-1;j>1;j--) {
                arr[x2][j] = arr[x2][j-1];
            }
            arr[x2][1] = 0;
        }

        int ans = 2;
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                ans += arr[i][j];
            }
        }
        System.out.println(ans);
    }
}

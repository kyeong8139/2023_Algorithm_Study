import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마법사상어와토네이도_20057 {
    static int N, arr[][], idx, ans, dx[] = {0, 1, 0, -1}, dy[] = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader bf =  new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0;j<N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = N/2, y = N/2;
        idx = -1;
        ans = 0;

        int time = 0;
        while(time < N-1) {
            time++;
            for(int i=0;i<2;i++) {
                idx = ++idx > 3 ? idx%4 : idx;
                for(int j=0;j<time;j++) {
                    int more = arr[x+dx[idx]][y+dy[idx]];
                    if(more != 0) {
                        //모래 날리는 로직
                        sendsand(x, y);
                    }

                    x += dx[idx];
                    y += dy[idx];
                }
            }

            if(time == N-1) {
                idx = ++idx > 3 ? idx%4 : idx;
                for(int j=0;j<time;j++) {
                    int more = arr[x+dx[idx]][y+dy[idx]];
                    if(more != 0) {
                        //모래 날리는 로직
                        sendsand(x, y);
                    }

                    x += dx[idx];
                    y += dy[idx];
                }
            }

        }
        System.out.println(ans);

    }
    private static void sendsand(int x, int y) {
        int yMore = arr[x+dx[idx]][y+dy[idx]];
        int chongMore = yMore;

        //x 에서 +1, +3
        try {
            arr[x+dx[(idx+1)%4]][y+dy[(idx+1)%4]] += yMore/100;
        } catch (Exception e) {
            ans += yMore/100;
        } finally {
            chongMore -= yMore/100;
        }

        try {
            arr[x+dx[(idx+3)%4]][y+dy[(idx+3)%4]] += yMore/100;
        } catch (Exception e) {
            ans += yMore/100;
        } finally {
            chongMore -= yMore/100;
        }

        //y 에서 +1, +3
        try {
            arr[x+dx[idx]+dx[(idx+1)%4]][y+dy[idx]+dy[(idx+1)%4]] += yMore*7/100;
        } catch (Exception e) {
            ans += yMore*7/100;
        } finally {
            chongMore -= yMore*7/100;
        }
        try {
            arr[x+dx[idx]+dx[(idx+3)%4]][y+dy[idx]+dy[(idx+3)%4]] += yMore*7/100;
        } catch (Exception e) {
            ans += yMore*7/100;
        } finally {
            chongMore -= yMore*7/100;
        }

        try {
            arr[x+dx[idx]+dx[(idx+1)%4]+dx[(idx+1)%4]][y+dy[idx]+dy[(idx+1)%4]+dy[(idx+1)%4]] += yMore*2/100;
        } catch (Exception e) {
            ans += yMore*2/100;
        } finally {
            chongMore -= yMore*2/100;
        }
        try {
            arr[x+dx[idx]+dx[(idx+3)%4]+dx[(idx+3)%4]][y+dy[idx]+dy[(idx+3)%4]+dy[(idx+3)%4]] += yMore*2/100;
        } catch (Exception e) {
            ans += yMore*2/100;
        } finally {
            chongMore -= yMore*2/100;
        }
        
        // 알파에서 10
        try {
            arr[x+dx[idx]+dx[idx]+dx[(idx+1)%4]][y+dy[idx]+dy[idx]+dy[(idx+1)%4]] += yMore/10;
        } catch (Exception e) {
            ans += yMore/10;
        } finally {
            chongMore -= yMore/10;
        }
        try {
            arr[x+dx[idx]+dx[idx]+dx[(idx+3)%4]][y+dy[idx]+dy[idx]+dy[(idx+3)%4]] += yMore/10;
        } catch (Exception e) {
            ans += yMore/10;
        } finally {
            chongMore -= yMore/10;
        }

        try {
            arr[x+dx[idx]+dx[idx]+dx[idx]][y+dy[idx]+dy[idx]+dy[idx]] += yMore*5/100;
        } catch (Exception e) {
            ans += yMore*5/100;
        } finally {
            chongMore -= yMore*5/100;
        }

        try {
            arr[x+dx[idx]+dx[idx]][y+dy[idx]+dy[idx]] += chongMore;
        } catch (Exception e) {
            ans += chongMore;
        }

    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 드래곤커브_15685 {
    static class Sun {
        Loc startLoc, endLoc;
        Sun() {}

        Sun(Loc startLoc, Loc endLoc) {
            this.startLoc = startLoc;
            this.endLoc = endLoc;
        }
    }

    static class Loc {
        int x, y;

        Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[][] arr = new int[101][101];
        int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};

        for(int t=0;t<N;t++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken()),
            y = Integer.parseInt(st.nextToken()),
            dir = Integer.parseInt(st.nextToken()),
            gen = Integer.parseInt(st.nextToken());
            
            Deque<Sun> deque = new ArrayDeque<>();
            Sun line = new Sun(new Loc(x, y), new Loc(x+dx[dir], y+dy[dir]));
            deque.offer(line);

            arr[line.startLoc.x][line.startLoc.y] = 1;
            arr[line.endLoc.x][line.endLoc.y] = 1;

            for(int i=1;i<gen;i++) {
                //세대 별
                Deque<Sun> originDeque = new ArrayDeque<>();
                Deque<Sun> newDeque = new ArrayDeque<>();

                dir = (dir+1)%4;
                
                while(!deque.isEmpty()) {
                    line = deque.pollLast();
                    Sun newLine = new Sun();
                    newLine.startLoc = line.endLoc;
                    newLine.endLoc = new Loc(line.endLoc.x+dx[dir], line.endLoc.y+dy[dir]);
                    originDeque.offerFirst(line);
                    newDeque.offerLast(newLine);
                }
                deque = originDeque;
                while(!newDeque.isEmpty()) {
                    deque.offer(newDeque.poll());
                }

                // for(Sun sun : newDeque) {
                //     arr[sun.startLoc.x][sun.startLoc.y] = 1;
                //     arr[sun.endLoc.x][sun.endLoc.y] = 1;
                // }

            }
            
            while(!deque.isEmpty()) {
                line = deque.poll();
                arr[line.startLoc.x][line.startLoc.y] = 1;
                arr[line.endLoc.x][line.endLoc.y] = 1;
            }
        }
        int ans = 0;
        for(int i=0;i<101;i++) {
            for(int j=0;j<101;j++) {
                try {
                    if(arr[i][j] == 1 && arr[i+1][j] == 1 && arr[i][j+1] == 1 && arr[i+1][j+1] == 1) {
                        ans++;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
        System.out.println(ans);
    }
}
import java.util.*;

public class 미세먼지 {
    static class Node{
        int r,c,v;

        Node(int r, int c, int v){
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
    static int[][] map;
    static int R, C;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        int T = sc.nextInt();

        map = new int[R][C];
        int robot = -1;

        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++) {
                int n = sc.nextInt();
                map[i][j] = n;

                if(n == -1 && robot == -1)
                    robot = i;
            }
        }

        while(T-- > 0){
            dust();
            move(robot);
        }

        int sum = 0;
        for(int i = 0; i<R; i++)
            for (int j = 0; j < C; j++)
                sum += map[i][j];

        System.out.println(sum+2);
    }

    static void dust(){
        List<Node> l = new ArrayList<>();

        for(int i = 0; i<R; i++)
            for(int j = 0; j<C; j++)
                if(map[i][j]>=5)
                    l.add(new Node(i, j, map[i][j]));

        for (Node node : l) {
            int count = 0;

            for (int j = 0; j < 4; j++) {
                int dr = node.r + dx[j];
                int dc = node.c + dy[j];

                if (dr < 0 || dc < 0 || dr >= R || dc >= C)
                    continue;
                if (map[dr][dc] == -1)
                    continue;

                map[dr][dc] += node.v / 5;
                count++;
            }

            map[node.r][node.c] -= node.v / 5 * count;
        }
    }
    //반시계 로봇 순환
    static void move(int robot){
        for(int i = robot-2; i>=0; i--)
            map[i+1][0] = map[i][0];

        for(int i = 0; i<C-1; i++)
            map[0][i] = map[0][i+1];

        for(int i = 0; i<robot; i++)
            map[i][C-1] = map[i+1][C-1];

        for(int i = C-1; i>1; i--)
            map[robot][i] = map[robot][i-1];

        map[robot][1] = 0;

        for(int i = robot+2; i<R-1; i++)
            map[i][0] = map[i+1][0];

        for(int i = 0; i<C-1; i++)
            map[R-1][i] = map[R-1][i+1];

        for(int i = R-1; i>robot+1; i--)
            map[i][C-1] = map[i-1][C-1];

        for(int i = C-1; i>1; i--)
            map[robot+1][i] = map[robot+1][i-1];

        map[robot+1][1] = 0;
    }
}


